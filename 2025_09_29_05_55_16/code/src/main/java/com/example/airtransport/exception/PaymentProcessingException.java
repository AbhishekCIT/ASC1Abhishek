package com.example.airtransport.exception;

/**
 * Exception thrown when payment processing fails or is declined.
 */
public class PaymentProcessingException extends Exception {
    public PaymentProcessingException(String message) {
        super(message);
    }
}
