package com.airtransport.exception;

/**
 * Exception thrown for generic validation errors.
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
