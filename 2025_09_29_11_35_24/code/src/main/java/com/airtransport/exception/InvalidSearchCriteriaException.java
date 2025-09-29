package com.airtransport.exception;

/**
 * Exception thrown when search criteria are invalid.
 */
public class InvalidSearchCriteriaException extends RuntimeException {
    public InvalidSearchCriteriaException(String message) {
        super(message);
    }
}
