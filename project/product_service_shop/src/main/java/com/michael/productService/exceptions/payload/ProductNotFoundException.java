package com.michael.productService.exceptions.payload;

public class ProductNotFoundException  extends RuntimeException{

    public ProductNotFoundException(String message) {
        super(message);
    }
}
