package com.test.profile_service.service.payment;

import com.test.profile_service.domain.CouponCode;
import com.test.profile_service.domain.coupon.Coupon;
import com.test.profile_service.domain.memberProfile.MemberProfile;
import com.test.profile_service.domain.payment.Payment;
import com.test.profile_service.dto.payment.request.PaymentRequestDto;
import com.test.profile_service.dto.payment.response.PaymentApiResponse;
import com.test.profile_service.dto.payment.response.PaymentResponseDto;
import com.test.profile_service.repository.coupon.CouponRepository;
import com.test.profile_service.repository.member.MemberProfileRepository;
import com.test.profile_service.repository.payment.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Base64;
import java.util.HashMap;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final MemberProfileRepository memberProfileRepository;
    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;
    private final CouponRepository couponRepository;

    // @Value 어노테이션을 사용하여 속성 파일(application.yml)에 Secret Key 를 정의한 후에 안전하게 관리
    @Value("${tosspayments.secretKey}")
    private String secretKey;

    @Override
    @Transactional
    public PaymentResponseDto confirmPayment(PaymentRequestDto requestDto) {

        // 중복 결제 체크
        if(paymentRepository.existsByPaymentKey(requestDto.getPaymentKey())) {
            throw new IllegalStateException("이미 처리된 결제입니다.");
        }

        // Toss 결제 승인 API 호출 url
        String url = "https://api.tosspayments.com/v1/payments/confirm";

        // header 설정
        HttpHeaders headers = new HttpHeaders();
        String base64Cred = Base64.getEncoder().encodeToString((secretKey + ":").getBytes());
        headers.add(HttpHeaders.AUTHORIZATION, "Basic" + base64Cred);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 요청 body
        HashMap<Object, Object> body = new HashMap<>();
        body.put("paymentKey", requestDto.getPaymentKey());
        body.put("orderId", requestDto.getOrderId());
        body.put("amount", requestDto.getAmount());

        // HttpEntity : 요청 header와 body를 캡슐화하는 데 사용
        HttpEntity<HashMap<Object, Object>> httpEntity = new HttpEntity<>(body, headers);

        ResponseEntity<PaymentApiResponse> response;
        try{
            // postForEntity() 메서드를 사용하여 POST 요청을 보내고 response 로 응답 받기
            response = restTemplate.postForEntity(url, httpEntity, PaymentApiResponse.class);
        } catch (HttpClientErrorException e) {
            // Toss API 에러 처리
            throw new IllegalStateException("Toss 결제 승인 실패: " + e.getStatusCode());
        }

        PaymentApiResponse apiResponse = response.getBody();
        if(apiResponse == null || !"DONE".equals(apiResponse.getStatus())) {
            throw new IllegalStateException("결제 승인 정보가 유효하지 않습니다.");
        }

        // 회원 조회
        MemberProfile member = memberProfileRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new NoSuchElementException("회원 없음"));

        // 최종 결제 금액 계산
        int approvedAmount = apiResponse.getApprovedAmount();
        int finalAmount = approvedAmount;

        if(requestDto.getCouponCode() != null) {
            CouponCode code = CouponCode.valueOf(requestDto.getCouponCode());
            Coupon coupon = couponRepository.findByCode(code)
                    .orElseThrow(() -> new NoSuchElementException("쿠폰을 찾을 수 없습니다."));

            if(!coupon.isUsable()) {
                throw new IllegalStateException("쿠폰 사용 가능 횟수를 초과하였습니다.");
            }
            finalAmount = calculateDiscountedAmount(approvedAmount, coupon.getDiscountRate(), coupon.getMaxDiscountAmount());

            coupon.use();   // 쿠폰 사용 차감
            couponRepository.save(coupon);  // 쿠폰 변경사항 저장
        }


        // DB 저장 및 포인트 적립
        Payment payment = new Payment();
        payment.setPaymentKey(apiResponse.getPaymentKey());
        payment.setOrderId(apiResponse.getOrderId());
        payment.setAmount(finalAmount); // 할인 적용된 금액 저장
        payment.setStatus(apiResponse.getStatus());
        payment.setMethod(apiResponse.getMethod());
        payment.setMember(member);
        paymentRepository.save(payment);

        // 포인트 적립 (원화 1:1)
        member.addPoints(finalAmount);

        return new PaymentResponseDto(payment.getOrderId(), finalAmount, payment.getStatus());

    }


    private int calculateDiscountedAmount(int originalAmount, int rate, int maxDiscount) {
        int discount = originalAmount * rate / 100;
        return originalAmount - Math.min(discount, maxDiscount);
    }
}
