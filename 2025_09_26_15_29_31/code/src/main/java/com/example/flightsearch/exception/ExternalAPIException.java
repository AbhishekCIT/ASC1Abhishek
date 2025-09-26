package com.example.flightsearch.exception;

/**
 * Exception thrown when an external flight API fails or is unavailable.
 */
public class ExternalAPIException extends RuntimeException {
    public ExternalAPIException(String message) { super(message); }
    public ExternalAPIException(String message, Throwable cause) { super(message, cause); }
}
