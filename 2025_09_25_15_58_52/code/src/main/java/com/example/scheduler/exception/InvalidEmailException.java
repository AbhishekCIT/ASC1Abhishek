package com.example.scheduler.exception;

/**
 * Exception thrown when an invalid email is encountered.
 */
public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
