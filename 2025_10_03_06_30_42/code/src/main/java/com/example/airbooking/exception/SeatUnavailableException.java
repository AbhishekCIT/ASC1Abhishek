package com.example.airbooking.exception;

/**
 * Exception thrown when no seats are available for booking.
 */
public class SeatUnavailableException extends RuntimeException {
    public SeatUnavailableException(String message) {
        super(message);
    }
}
