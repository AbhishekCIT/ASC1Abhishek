package com.example.calculator.exception;

/**
 * Exception thrown for unsupported operations
 */
public class OperationNotSupportedException extends RuntimeException {
    public OperationNotSupportedException(String message) {
        super(message);
    }
}
