package com.example.airtransport.exception;

/**
 * Exception thrown when external flight API integration fails.
 */
public class ExternalAPIException extends Exception {
    public ExternalAPIException(String message) {
        super(message);
    }
}
