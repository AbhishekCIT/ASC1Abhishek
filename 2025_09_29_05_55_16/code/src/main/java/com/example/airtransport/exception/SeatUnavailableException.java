package com.example.airtransport.exception;

/**
 * Exception thrown when the selected seat is no longer available.
 */
public class SeatUnavailableException extends Exception {
    public SeatUnavailableException(String message) {
        super(message);
    }
}
