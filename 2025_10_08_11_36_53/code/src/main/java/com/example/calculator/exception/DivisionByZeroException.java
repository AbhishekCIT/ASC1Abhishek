package com.example.calculator.exception;

/**
 * Exception thrown for division by zero
 */
public class DivisionByZeroException extends RuntimeException {
    public DivisionByZeroException(String message) {
        super(message);
    }
}
