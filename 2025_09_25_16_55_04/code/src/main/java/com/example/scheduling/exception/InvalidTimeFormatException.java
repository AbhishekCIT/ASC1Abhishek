package com.example.scheduling.exception;

/**
 * Exception thrown when time format is invalid.
 */
public class InvalidTimeFormatException extends RuntimeException {
    public InvalidTimeFormatException(String message) {
        super(message);
    }
}
