package com.airtransport.exception;

/**
 * Exception thrown when external flight provider API fails.
 */
public class ProviderAPIException extends RuntimeException {
    public ProviderAPIException(String message) {
        super(message);
    }
    public ProviderAPIException(String message, Throwable cause) {
        super(message, cause);
    }
}
