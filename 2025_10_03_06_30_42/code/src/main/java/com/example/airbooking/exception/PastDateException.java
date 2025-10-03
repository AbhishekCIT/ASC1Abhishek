package com.example.airbooking.exception;

/**
 * Exception thrown when a travel date is in the past.
 */
public class PastDateException extends RuntimeException {
    public PastDateException(String message) {
        super(message);
    }
}
