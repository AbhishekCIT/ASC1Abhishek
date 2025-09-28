package com.example.airlinebooking.exception;

/**
 * Exception thrown when selected seat(s) are not available.
 */
public class SeatUnavailableException extends RuntimeException {
    public SeatUnavailableException(String message) {
        super(message);
    }
}
