package com.example.airbooking.exception;

/**
 * Exception thrown when refund processing fails.
 */
public class RefundProcessingException extends RuntimeException {
    public RefundProcessingException(String message) {
        super(message);
    }
}
