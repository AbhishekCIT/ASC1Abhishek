package com.example.calculator.exception;

/**
 * Exception thrown when UI fails to render properly
 */
public class RenderException extends RuntimeException {
    public RenderException(String message) {
        super(message);
    }
}
