package com.example.scheduling.exception;

/**
 * Exception thrown when a report type is not found in the system.
 */
public class ReportTypeNotFoundException extends RuntimeException {
    public ReportTypeNotFoundException(String message) {
        super(message);
    }
}
