package com.test.profile_service.dto.payment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 서버가 클라이언트한테 결제 결과를 줄 때 사용.
 */
@Getter
@NoArgsConstructor
public class PaymentResponseDto {

    private String orderId; // 주문번호
    private int amount;     // 결제금액
    private String status;  // 결제상태 ( 1. DONE  2. CANCELED )

    @Builder
    public PaymentResponseDto(String orderId, int amount, String status) {
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
    }

}
