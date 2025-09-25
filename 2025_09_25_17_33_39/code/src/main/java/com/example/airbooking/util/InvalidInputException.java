package com.example.airbooking.util;

/**
 * Thrown when required input fields are missing or invalid.
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
