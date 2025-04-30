package com.test.profile_service.service.payment;

import com.test.profile_service.domain.coupon.Coupon;
import com.test.profile_service.domain.payment.Payment;
import com.test.profile_service.dto.payment.request.PaymentRequestDto;
import com.test.profile_service.dto.payment.response.PaymentResponseDto;

public interface PaymentService {

    PaymentResponseDto confirmPayment(PaymentRequestDto requestDto);

}
