package com.example.flightsearch.exception;

/**
 * Exception thrown when an invalid airport code is provided.
 */
public class InvalidAirportCodeException extends RuntimeException {
    public InvalidAirportCodeException(String message) {
        super(message);
    }
}
