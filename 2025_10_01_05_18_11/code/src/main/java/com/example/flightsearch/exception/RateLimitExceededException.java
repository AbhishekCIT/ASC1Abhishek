package com.example.flightsearch.exception;

/**
 * Exception thrown when user exceeds allowed request rate.
 */
public class RateLimitExceededException extends RuntimeException {
    public RateLimitExceededException(String message) {
        super(message);
    }
}
