package com.example.flightsearch.exception;

/**
 * Thrown if the date is in the past or invalid.
 */
public class InvalidDateException extends RuntimeException {
    public InvalidDateException(String message) { super(message); }
}
