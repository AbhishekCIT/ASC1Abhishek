package com.example.calculator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.calculator.exception.DivisionByZeroException;

/**
 * Service class for arithmetic operations.
 */
@Service
public class CalculatorService {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    /**
     * Adds two numbers.
     */
    public Double add(Double a, Double b) {
        Double result = a + b;
        logger.info("Addition: {} + {} = {}", a, b, result);
        return result;
    }

    /**
     * Subtracts two numbers.
     */
    public Double subtract(Double a, Double b) {
        Double result = a - b;
        logger.info("Subtraction: {} - {} = {}", a, b, result);
        return result;
    }

    /**
     * Multiplies two numbers.
     */
    public Double multiply(Double a, Double b) {
        Double result = a * b;
        logger.info("Multiplication: {} * {} = {}", a, b, result);
        return result;
    }

    /**
     * Divides two numbers. Throws DivisionByZeroException if b == 0.
     */
    public Double divide(Double a, Double b) {
        if (b == 0.0) {
            logger.error("Division by zero attempted: {}/{}", a, b);
            throw new DivisionByZeroException("Division by zero is not allowed.");
        }
        Double result = a / b;
        logger.info("Division: {} / {} = {}", a, b, result);
        return result;
    }
}
