package com.michael.productService.exceptions.payload;

public class ProductAlreadyExistsException  extends RuntimeException{
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
