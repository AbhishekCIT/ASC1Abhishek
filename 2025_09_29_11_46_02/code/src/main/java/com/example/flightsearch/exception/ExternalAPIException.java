package com.example.flightsearch.exception;

/**
 * Thrown if external flight API fails.
 */
public class ExternalAPIException extends RuntimeException {
    public ExternalAPIException(String message) { super(message); }
}
