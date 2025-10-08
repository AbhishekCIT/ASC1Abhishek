package com.example.airbooking.exception;

/**
 * Exception thrown when payment fails or is declined.
 */
public class PaymentException extends RuntimeException {
    public PaymentException(String message) {
        super(message);
    }
}
