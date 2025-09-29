package com.example.calculator.exception;

/**
 * Exception thrown when a key cannot be mapped to a calculator action
 */
public class KeyboardMappingException extends RuntimeException {
    public KeyboardMappingException(String message) {
        super(message);
    }
}
