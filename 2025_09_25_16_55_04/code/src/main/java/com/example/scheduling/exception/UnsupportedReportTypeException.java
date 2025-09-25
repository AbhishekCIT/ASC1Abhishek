package com.example.scheduling.exception;

/**
 * Exception thrown when report type is not supported.
 */
public class UnsupportedReportTypeException extends RuntimeException {
    public UnsupportedReportTypeException(String message) {
        super(message);
    }
}
