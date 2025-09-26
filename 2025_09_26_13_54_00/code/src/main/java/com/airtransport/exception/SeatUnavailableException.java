package com.airtransport.exception;

/**
 * Thrown when the selected seat is not available.
 */
public class SeatUnavailableException extends RuntimeException {
    public SeatUnavailableException(String message) { super(message); }
}
