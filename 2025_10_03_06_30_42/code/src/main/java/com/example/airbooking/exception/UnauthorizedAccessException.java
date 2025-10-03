package com.example.airbooking.exception;

/**
 * Exception thrown when a user tries to access unauthorized booking details.
 */
public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
