package com.example.airbooking.util;

/**
 * Thrown when no seats are available for the selected flight.
 */
public class NoSeatsAvailableException extends RuntimeException {
    public NoSeatsAvailableException(String message) {
        super(message);
    }
}
