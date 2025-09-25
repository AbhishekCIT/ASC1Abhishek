package com.example.scheduler.exception;

/**
 * Exception thrown when frequency violates business rules.
 */
public class FrequencyRuleException extends RuntimeException {
    public FrequencyRuleException(String message) {
        super(message);
    }
}
