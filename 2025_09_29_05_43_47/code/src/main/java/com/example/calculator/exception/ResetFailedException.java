package com.example.calculator.exception;

/**
 * Exception thrown when reset fails
 */
public class ResetFailedException extends RuntimeException {
    public ResetFailedException(String message) {
        super(message);
    }
}
