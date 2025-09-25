package com.example.scheduling.exception;

/**
 * Exception thrown when an invalid frequency is provided.
 */
public class InvalidFrequencyException extends RuntimeException {
    public InvalidFrequencyException(String message) {
        super(message);
    }
}
