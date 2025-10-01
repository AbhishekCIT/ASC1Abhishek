package com.example.airbooking.exception;

/**
 * Exception thrown when a booking ID does not exist.
 */
public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(String message) {
        super(message);
    }
}
