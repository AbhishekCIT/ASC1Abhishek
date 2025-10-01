package com.example.airbooking.exception;

/**
 * Exception for payment errors.
 */
public class PaymentException extends RuntimeException {
    public PaymentException(String message) {
        super(message);
    }
}
