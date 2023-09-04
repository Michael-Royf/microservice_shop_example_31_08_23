package com.michael.orderService.exceptions;

public class OrderNotFoundException  extends RuntimeException{
    public OrderNotFoundException(String message) {
        super(message);
    }
}
