package com.example.airbooking.exception;

/**
 * Exception thrown when confirmation email could not be sent.
 */
public class EmailException extends RuntimeException {
    public EmailException(String message) {
        super(message);
    }
}
