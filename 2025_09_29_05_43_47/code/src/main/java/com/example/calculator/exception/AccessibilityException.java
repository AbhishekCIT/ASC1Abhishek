package com.example.calculator.exception;

/**
 * Exception thrown when UI fails accessibility compliance
 */
public class AccessibilityException extends RuntimeException {
    public AccessibilityException(String message) {
        super(message);
    }
}
