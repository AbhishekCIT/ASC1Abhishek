package com.example.airbooking.exception;

/**
 * Exception thrown when selected seat is no longer available.
 */
public class SeatUnavailableException extends RuntimeException {
    public SeatUnavailableException(String message) {
        super(message);
    }
}
