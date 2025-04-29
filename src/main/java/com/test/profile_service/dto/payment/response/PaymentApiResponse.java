package com.test.profile_service.dto.payment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 서버가 Toss에 결제 승인 요청 보내고, 응답받을 body 를 매핑할 class.
 */
@Getter
@NoArgsConstructor
public class PaymentApiResponse {

    private String paymentKey;   // 결제 키
    private String orderId;      // 주문 번호
    private String status;       // 결제 상태 (예: DONE, CANCELED)
    private int approvedAmount;  // 결제 승인된 금액

    private String method;       // 결제 수단 (예: 카드, 가상계좌)
    private String requestedAt;  // 결제 요청 시간
    private String approvedAt;   // 결제 승인 시간

    @Builder
    public PaymentApiResponse(String paymentKey, String orderId, String status, int approvedAmount, String method, String requestedAt, String approvedAt) {
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.status = status;
        this.approvedAmount = approvedAmount;
        this.method = method;
        this.requestedAt = requestedAt;
        this.approvedAt = approvedAt;
    }
}