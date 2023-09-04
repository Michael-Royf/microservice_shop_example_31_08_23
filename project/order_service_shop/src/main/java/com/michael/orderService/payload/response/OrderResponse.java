package com.michael.orderService.payload.response;

import com.michael.clients.payment.PaymentResponse;
import com.michael.clients.product.ProductResponse;
import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderResponse {
    private Long id;
    private Instant orderDate;
    private String orderStatus;
    private Long amount;
    private ProductResponse productResponse;
    private PaymentResponse paymentResponse;
}
