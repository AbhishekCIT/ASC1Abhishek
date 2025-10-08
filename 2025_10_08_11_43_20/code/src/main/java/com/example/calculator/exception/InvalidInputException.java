package com.example.calculator.exception;

/**
 * Exception thrown when inputs are not numeric or empty.
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
