package com.example.calculator.exception;

/**
 * Exception thrown when decimal input is invalid (multiple decimal points or not a valid float).
 */
public class InvalidDecimalInputException extends RuntimeException {
    public InvalidDecimalInputException(String message) {
        super(message);
    }
}
