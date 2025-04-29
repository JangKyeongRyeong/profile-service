package com.test.profile_service.controller.payment;

import com.test.profile_service.dto.payment.request.PaymentRequestDto;
import com.test.profile_service.dto.payment.response.PaymentResponseDto;
import com.test.profile_service.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/confirm")
    public ResponseEntity<PaymentResponseDto> confirmPayment(@RequestBody PaymentRequestDto requestDto) {
        PaymentResponseDto responseDto = paymentService.confirmPayment(requestDto);
        return ResponseEntity.ok(responseDto);
    }

}
