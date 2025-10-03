package com.example.airtransport.exception;

/**
 * Exception thrown when selected seats are not available.
 */
public class SeatUnavailableException extends RuntimeException {
    public SeatUnavailableException(String message) {
        super(message);
    }
}
