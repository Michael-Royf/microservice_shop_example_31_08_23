package com.michael.orderService.payload.response;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderResponse {
    private Long id;
    private Long productId;
    private Long quantity;
    private Instant orderDate;
    private String orderStatus;
    private Long amount;
}
