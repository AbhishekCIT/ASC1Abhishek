package com.example.scheduling.exception;

/**
 * Exception thrown when frequency is invalid.
 */
public class InvalidFrequencyException extends RuntimeException {
    public InvalidFrequencyException(String message) {
        super(message);
    }
}
