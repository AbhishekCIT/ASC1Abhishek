package com.example.airbooking.util;

/**
 * Thrown when payment processing fails.
 */
public class PaymentFailedException extends RuntimeException {
    public PaymentFailedException(String message) {
        super(message);
    }
}
