package com.example.flightsearch.exception;

/**
 * Thrown if passengers is not a positive integer.
 */
public class InvalidPassengerCountException extends RuntimeException {
    public InvalidPassengerCountException(String message) { super(message); }
}
