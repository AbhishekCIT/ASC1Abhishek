package com.example.calculator.service;

import org.springframework.stereotype.Service;

/**
 * Service for performing arithmetic operations.
 */
@Service
public class ArithmeticService {
    /**
     * Adds two numbers.
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * Subtracts the second number from the first.
     */
    public double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Multiplies two numbers.
     */
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Divides the first number by the second.
     * Throws ArithmeticException if division by zero.
     */
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return a / b;
    }
}
