package com.example.flightsearch.exception;

/**
 * Exception thrown when an invalid passenger count is provided.
 */
public class InvalidPassengerCountException extends RuntimeException {
    public InvalidPassengerCountException(String message) {
        super(message);
    }
}
