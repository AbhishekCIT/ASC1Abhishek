package com.example.calculator.service;

import com.example.calculator.exception.DivisionByZeroException;
import org.springframework.stereotype.Service;

/**
 * Service to perform arithmetic operations.
 */
@Service
public class ArithmeticService {
    /**
     * Adds two numbers.
     */
    public double add(double num1, double num2) {
        return num1 + num2;
    }

    /**
     * Subtracts num2 from num1.
     */
    public double subtract(double num1, double num2) {
        return num1 - num2;
    }

    /**
     * Multiplies two numbers.
     */
    public double multiply(double num1, double num2) {
        return num1 * num2;
    }

    /**
     * Divides num1 by num2. Throws DivisionByZeroException if num2 is zero.
     */
    public double divide(double num1, double num2) {
        if (num2 == 0) {
            throw new DivisionByZeroException("Division by zero is not allowed.");
        }
        return num1 / num2;
    }
}
