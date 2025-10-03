package com.airline.flightbooking.exception;

/**
 * Exception thrown when payment processing fails.
 */
public class PaymentFailedException extends RuntimeException {
    public PaymentFailedException(String message) {
        super(message);
    }
}
