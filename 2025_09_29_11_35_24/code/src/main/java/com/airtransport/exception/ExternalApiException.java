package com.airtransport.exception;

/**
 * Exception thrown when an external API call fails or times out.
 */
public class ExternalApiException extends RuntimeException {
    public ExternalApiException(String message) {
        super(message);
    }
    public ExternalApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
