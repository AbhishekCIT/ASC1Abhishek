package com.example.scheduling.exception;

/**
 * Exception thrown when a scheduling rule conflicts with an existing rule.
 */
public class SchedulingConflictException extends RuntimeException {
    public SchedulingConflictException(String message) {
        super(message);
    }
}
