package com.michael.clients.payment;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @PostMapping("/api/v1/payments/doPayment")
    Long doPayment(@RequestBody PaymentRequest paymentRequest);

    @CircuitBreaker(name = "payment-service", fallbackMethod = "getDefaultPayment")
    @GetMapping("/api/v1/payments/{orderId}")
    ResponseEntity<PaymentResponse> fetchPaymentDetailsByOrderId(@PathVariable(name = "orderId") Long orderId);


    default ResponseEntity<PaymentResponse> getDefaultPayment(Long orderId, Exception exception) {
        return new ResponseEntity<>(PaymentResponse.builder()
                .orderId(orderId)
                .amount(1L)
                .paymentMode("Default Payment Mode")
                .paymentStatus("Default Status")
                .paymentDay(Instant.now())
                .referenceNumber("Default Reference Number")
                .build(), HttpStatus.NO_CONTENT);

    }
}
