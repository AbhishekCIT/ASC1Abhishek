package com.example.airbooking.exception;

/**
 * Exception thrown when cancellation policy is violated.
 */
public class CancellationPolicyException extends RuntimeException {
    public CancellationPolicyException(String message) {
        super(message);
    }
}
