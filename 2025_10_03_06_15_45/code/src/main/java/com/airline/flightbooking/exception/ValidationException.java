package com.airline.flightbooking.exception;

/**
 * Exception thrown when validation fails (e.g., passenger details missing/invalid).
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
