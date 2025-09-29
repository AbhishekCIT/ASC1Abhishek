package com.example.flightsearch.exception;

/**
 * Thrown if origin or destination is not a valid airport code.
 */
public class InvalidAirportCodeException extends RuntimeException {
    public InvalidAirportCodeException(String message) { super(message); }
}
