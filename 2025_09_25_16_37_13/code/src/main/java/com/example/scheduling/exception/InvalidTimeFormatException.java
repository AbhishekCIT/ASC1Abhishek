package com.example.scheduling.exception;

/**
 * Exception thrown when an invalid time format is provided.
 */
public class InvalidTimeFormatException extends RuntimeException {
    public InvalidTimeFormatException(String message) {
        super(message);
    }
}
