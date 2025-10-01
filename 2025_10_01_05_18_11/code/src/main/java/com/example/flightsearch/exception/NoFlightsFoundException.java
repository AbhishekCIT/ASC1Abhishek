package com.example.flightsearch.exception;

/**
 * Exception thrown when no flights are found for the search criteria.
 */
public class NoFlightsFoundException extends RuntimeException {
    public NoFlightsFoundException(String message) {
        super(message);
    }
}
