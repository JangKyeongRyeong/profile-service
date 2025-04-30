package com.test.profile_service.dto.payment.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 클라이언트가 서버에 결제 승인을 요청할 때 사용.
 * paymentKey, orderId, amount는 Toss 결제 이후 클라이언트가 보내는 필수 값.
 * memberId는 서버에서 포인트 적립할 회원을 찾기 위해 추가.
 */
@Getter
@NoArgsConstructor
public class PaymentRequestDto {

    private String paymentKey;  // toss에서 받은 결제키
    private String orderId;     // 주문 번호
    private int amount;         // 원래 결제 금액
    private Long memberId;      // 결제하는 회원 ID
    private String couponCode;  // 쿠폰 코드


    @Builder
    public PaymentRequestDto(String paymentKey, String orderId, int amount, Long memberId, String couponCode) {
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.amount = amount;
        this.memberId = memberId;
        this.couponCode = couponCode;
    }

}
