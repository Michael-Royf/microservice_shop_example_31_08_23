package com.michael.orderService.payload.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderRequest {
    private Long productId;
    private Long totalAmount;
    private Long quantity;
    private PaymentMode paymentMode;
}
