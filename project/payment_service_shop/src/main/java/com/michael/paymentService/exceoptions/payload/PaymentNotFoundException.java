package com.michael.paymentService.exceoptions.payload;

public class PaymentNotFoundException  extends RuntimeException{
    public PaymentNotFoundException(String message) {
        super(message);
    }
}
