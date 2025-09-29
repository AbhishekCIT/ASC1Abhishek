package com.airtransport.exception;

/**
 * Exception thrown when no flights are found for the given search criteria.
 */
public class NoFlightsFoundException extends RuntimeException {
    public NoFlightsFoundException(String message) {
        super(message);
    }
}
