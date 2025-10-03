package com.example.airbooking.exception;

/**
 * Exception thrown when booking is not found.
 */
public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(String message) {
        super(message);
    }
}
