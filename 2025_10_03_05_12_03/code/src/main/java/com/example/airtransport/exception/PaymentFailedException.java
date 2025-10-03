package com.example.airtransport.exception;

/**
 * Exception thrown when payment fails.
 */
public class PaymentFailedException extends RuntimeException {
    public PaymentFailedException(String message) {
        super(message);
    }
}
