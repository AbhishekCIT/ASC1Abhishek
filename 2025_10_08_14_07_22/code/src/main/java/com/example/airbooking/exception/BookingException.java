package com.example.airbooking.exception;

/**
 * Exception thrown when booking fails due to invalid data.
 */
public class BookingException extends RuntimeException {
    public BookingException(String message) {
        super(message);
    }
}
