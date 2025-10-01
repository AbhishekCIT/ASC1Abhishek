package com.example.airbooking.exception;

/**
 * Exception thrown when no flights match the search criteria.
 */
public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(String message) {
        super(message);
    }
}
