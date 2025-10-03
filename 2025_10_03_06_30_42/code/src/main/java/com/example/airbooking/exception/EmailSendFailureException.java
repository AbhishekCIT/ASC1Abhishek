package com.example.airbooking.exception;

/**
 * Exception thrown when sending confirmation email fails.
 */
public class EmailSendFailureException extends RuntimeException {
    public EmailSendFailureException(String message) {
        super(message);
    }
}
