package com.michael.clients.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @PostMapping("/api/v1/payments/doPayment")
    Long doPayment(@RequestBody PaymentRequest paymentRequest);

    @GetMapping("/api/v1/payments/{orderId}")
    ResponseEntity<PaymentResponse> fetchPaymentDetailsByOrderId(@PathVariable(name = "orderId") Long orderId);
}
