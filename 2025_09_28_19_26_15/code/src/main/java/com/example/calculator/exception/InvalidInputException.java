package com.example.calculator.exception;

/**
 * Exception thrown when input is invalid (empty or non-numeric).
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
