package com.example.calculator.util;

import com.example.calculator.exception.InvalidInputException;
import com.example.calculator.exception.OperationNotSupportedException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Utility class for input validation
 */
@Component
public class InputValidator {
    private static final List<String> SUPPORTED_OPERATIONS = Arrays.asList("add", "subtract", "multiply", "divide");

    /**
     * Validates if input is a valid number (integer or decimal)
     * @param input input value
     * @param fieldName field name for error message
     * @return double value
     * @throws InvalidInputException if not a valid number
     */
    public double validateNumber(Object input, String fieldName) {
        if (input instanceof Number) {
            return ((Number) input).doubleValue();
        }
        try {
            return Double.parseDouble(input.toString());
        } catch (Exception e) {
            throw new InvalidInputException("Invalid input: " + fieldName + " must be a number");
        }
    }

    /**
     * Validates if operation is supported
     * @param op operation string
     * @return operation string
     * @throws OperationNotSupportedException if operation is not supported
     */
    public String validateOperation(String op) {
        if (op == null || !SUPPORTED_OPERATIONS.contains(op)) {
            throw new OperationNotSupportedException("Invalid operation");
        }
        return op;
    }
}
