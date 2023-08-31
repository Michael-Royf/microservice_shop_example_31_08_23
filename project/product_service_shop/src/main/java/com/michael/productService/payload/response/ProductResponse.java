package com.michael.productService.payload.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {
    private Long id;
    private String productName;
    private Long price;
    private Long quantity;
}
