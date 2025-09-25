package com.example.scheduling.exception;

/**
 * Exception thrown when a schedule is not found.
 */
public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException(String message) {
        super(message);
    }
}
