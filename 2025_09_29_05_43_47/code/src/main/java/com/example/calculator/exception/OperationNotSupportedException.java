package com.example.calculator.exception;

/**
 * Exception thrown when an invalid operation is requested
 */
public class OperationNotSupportedException extends RuntimeException {
    public OperationNotSupportedException(String message) {
        super(message);
    }
}
