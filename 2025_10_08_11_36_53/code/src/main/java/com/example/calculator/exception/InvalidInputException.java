package com.example.calculator.exception;

/**
 * Exception thrown for invalid input
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
