package com.example.calculator.service;

import org.springframework.stereotype.Service;

/**
 * Service for generating error messages for calculation errors.
 */
@Service
public class ErrorHandlingService {
    /**
     * Returns error message for division by zero.
     */
    public String handleDivisionByZero() {
        return "Division by zero is not allowed.";
    }

    /**
     * Returns error message for invalid input.
     */
    public String handleInvalidInput() {
        return "Input must be a valid number.";
    }

    /**
     * Returns error message for unsupported operation.
     */
    public String handleOperationNotSupported() {
        return "Operation not supported.";
    }
}
