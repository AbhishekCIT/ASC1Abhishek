package com.example.airbooking.exception;

/**
 * Exception thrown when a booking is not found or unauthorized.
 */
public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(String message) {
        super(message);
    }
}
