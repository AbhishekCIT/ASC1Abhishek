package com.example.airtransport.exception;

/**
 * Exception thrown when refund calculation fails.
 */
public class RefundCalculationException extends RuntimeException {
    public RefundCalculationException(String message) {
        super(message);
    }
}
