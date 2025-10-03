package com.example.airbooking.exception;

/**
 * Exception thrown when booking modification is not allowed due to window expiration.
 */
public class BookingModificationWindowException extends RuntimeException {
    public BookingModificationWindowException(String message) {
        super(message);
    }
}
