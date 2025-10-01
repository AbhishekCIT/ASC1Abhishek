package com.example.airbooking.exception;

/**
 * Exception for validation errors.
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
