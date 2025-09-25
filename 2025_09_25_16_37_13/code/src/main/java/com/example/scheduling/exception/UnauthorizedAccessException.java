package com.example.scheduling.exception;

/**
 * Exception thrown when a user lacks permission to perform an action.
 */
public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
