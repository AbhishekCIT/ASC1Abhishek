package com.example.airbooking.exception;

/**
 * Exception thrown when no flights are found for a search.
 */
public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(String message) {
        super(message);
    }
}
