package com.example.airtransport.exception;

/**
 * Exception thrown when refund transaction fails.
 */
public class RefundFailedException extends RuntimeException {
    public RefundFailedException(String message) {
        super(message);
    }
}
