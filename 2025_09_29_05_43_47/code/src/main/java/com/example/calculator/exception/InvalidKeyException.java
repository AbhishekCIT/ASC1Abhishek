package com.example.calculator.exception;

/**
 * Exception thrown when an invalid key is pressed
 */
public class InvalidKeyException extends RuntimeException {
    public InvalidKeyException(String message) {
        super(message);
    }
}
