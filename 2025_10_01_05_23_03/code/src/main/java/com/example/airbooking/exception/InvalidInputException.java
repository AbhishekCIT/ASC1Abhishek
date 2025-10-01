package com.example.airbooking.exception;

/**
 * Exception thrown when required fields are missing or invalid.
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
