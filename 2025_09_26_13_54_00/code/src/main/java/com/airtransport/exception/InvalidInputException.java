package com.airtransport.exception;

/**
 * Thrown when input validation fails.
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) { super(message); }
}
