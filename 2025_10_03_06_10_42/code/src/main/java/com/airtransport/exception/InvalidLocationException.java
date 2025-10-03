package com.airtransport.exception;

/**
 * Exception thrown for invalid origin/destination locations.
 */
public class InvalidLocationException extends RuntimeException {
    public InvalidLocationException(String message) {
        super(message);
    }
}
