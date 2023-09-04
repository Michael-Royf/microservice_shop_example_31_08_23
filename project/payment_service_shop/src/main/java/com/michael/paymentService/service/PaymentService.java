package com.michael.paymentService.service;

import com.michael.clients.payment.PaymentResponse;
import com.michael.paymentService.payload.request.PaymentRequest;


public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(Long orderId);
}
