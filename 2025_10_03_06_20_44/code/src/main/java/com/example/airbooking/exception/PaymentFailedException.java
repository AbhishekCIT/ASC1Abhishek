package com.example.airbooking.exception;

/**
 * Exception thrown when payment fails or is declined.
 */
public class PaymentFailedException extends RuntimeException {
    public PaymentFailedException(String message) {
        super(message);
    }
}
