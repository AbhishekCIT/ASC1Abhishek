package com.example.calculator.util;

import org.springframework.stereotype.Component;

/**
 * Utility class for arithmetic operations
 */
@Component
public class ArithmeticUtils {

    /**
     * Adds two numbers
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * Subtracts second number from first
     */
    public double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Multiplies two numbers
     */
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Divides first number by second
     */
    public double divide(double a, double b) {
        return a / b;
    }
}
