package com.example.scheduling.exception;

/**
 * Exception thrown when no recipient is selected.
 */
public class NoRecipientException extends RuntimeException {
    public NoRecipientException(String message) {
        super(message);
    }
}
