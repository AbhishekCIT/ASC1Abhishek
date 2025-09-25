package com.example.scheduler.exception;

/**
 * Exception thrown when a schedule is set for a past date/time.
 */
public class PastDateException extends RuntimeException {
    public PastDateException(String message) {
        super(message);
    }
}
