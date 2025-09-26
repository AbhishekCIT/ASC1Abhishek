package com.example.flightsearch.exception;

/**
 * Exception thrown when no flights match the applied filters.
 */
public class FilterMismatchException extends RuntimeException {
    public FilterMismatchException(String message) { super(message); }
}
