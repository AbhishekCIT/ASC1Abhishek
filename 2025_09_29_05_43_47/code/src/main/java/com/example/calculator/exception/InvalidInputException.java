package com.example.calculator.exception;

/**
 * Exception thrown when non-numeric input is detected
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
