package com.michael.productService.payload.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequest {
    //validation
    @NotBlank(message = "Product Name is required")
    private String productName;
    // @NotBlank(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than or equal to 1")
    private Long price;

    //@NotBlank(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than or equal to 1")
    private Long quantity;
}
