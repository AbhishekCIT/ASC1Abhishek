package com.example.booking.exception;

/**
 * Thrown if payment authorization fails.
 */
public class PaymentFailedException extends RuntimeException {
    public PaymentFailedException(String message) { super(message); }
}
