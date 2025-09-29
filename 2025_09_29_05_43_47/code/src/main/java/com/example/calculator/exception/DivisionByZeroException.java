package com.example.calculator.exception;

/**
 * Exception thrown when division by zero is attempted
 */
public class DivisionByZeroException extends RuntimeException {
    public DivisionByZeroException(String message) {
        super(message);
    }
}
