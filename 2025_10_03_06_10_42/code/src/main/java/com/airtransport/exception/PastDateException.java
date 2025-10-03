package com.airtransport.exception;

/**
 * Exception thrown when travel date is in the past.
 */
public class PastDateException extends RuntimeException {
    public PastDateException(String message) {
        super(message);
    }
}
