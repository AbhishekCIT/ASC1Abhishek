package com.example.flightsearch.exception;

/**
 * Exception thrown when airline data provider API fails.
 */
public class ExternalAPIException extends RuntimeException {
    public ExternalAPIException(String message) {
        super(message);
    }
    public ExternalAPIException(String message, Throwable cause) {
        super(message, cause);
    }
}
