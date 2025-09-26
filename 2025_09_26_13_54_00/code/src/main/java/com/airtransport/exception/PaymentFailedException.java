package com.airtransport.exception;

/**
 * Thrown when payment processing fails.
 */
public class PaymentFailedException extends RuntimeException {
    public PaymentFailedException(String message) { super(message); }
}
