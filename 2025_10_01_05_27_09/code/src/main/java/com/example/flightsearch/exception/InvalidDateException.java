package com.example.flightsearch.exception;

/**
 * Exception thrown when an invalid travel date is provided.
 */
public class InvalidDateException extends RuntimeException {
    public InvalidDateException(String message) {
        super(message);
    }
}
