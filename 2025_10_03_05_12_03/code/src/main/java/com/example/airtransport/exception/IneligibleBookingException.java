package com.example.airtransport.exception;

/**
 * Exception thrown when a booking is not eligible for cancellation.
 */
public class IneligibleBookingException extends RuntimeException {
    public IneligibleBookingException(String message) {
        super(message);
    }
}
