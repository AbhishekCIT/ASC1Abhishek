package com.example.airbooking.exception;

/**
 * Exception thrown for invalid input parameters.
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
