package com.example.airbooking.exception;

/**
 * Exception thrown when payment gateway authorization fails.
 */
public class PaymentFailedException extends RuntimeException {
    public PaymentFailedException(String message) {
        super(message);
    }
}
