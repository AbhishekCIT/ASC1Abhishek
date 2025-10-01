package com.example.airbooking.exception;

/**
 * Exception for booking errors.
 */
public class BookingException extends RuntimeException {
    public BookingException(String message) {
        super(message);
    }
}
