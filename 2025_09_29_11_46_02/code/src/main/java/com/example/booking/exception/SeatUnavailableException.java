package com.example.booking.exception;

/**
 * Thrown if selected seats are not available.
 */
public class SeatUnavailableException extends RuntimeException {
    public SeatUnavailableException(String message) { super(message); }
}
