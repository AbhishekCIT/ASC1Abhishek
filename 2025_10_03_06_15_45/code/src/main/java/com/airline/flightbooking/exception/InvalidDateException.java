package com.airline.flightbooking.exception;

/**
 * Exception thrown when the travel date is invalid (e.g., in the past).
 */
public class InvalidDateException extends RuntimeException {
    public InvalidDateException(String message) {
        super(message);
    }
}
