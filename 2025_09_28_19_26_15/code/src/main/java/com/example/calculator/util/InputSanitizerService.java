package com.example.calculator.util;

import com.example.calculator.exception.InvalidInputException;
import org.springframework.stereotype.Component;

/**
 * Utility service to sanitize and validate user input.
 */
@Component
public class InputSanitizerService {
    /**
     * Validates and sanitizes the input string, ensuring it is numeric and not empty.
     * @param input The input string
     * @param fieldName The name of the field (for error messages)
     * @return The parsed double value
     * @throws InvalidInputException if input is invalid
     */
    public double sanitizeAndValidate(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidInputException("Input fields cannot be empty.");
        }
        try {
            return Double.parseDouble(input.trim());
        } catch (NumberFormatException ex) {
            throw new InvalidInputException("Input fields must be numeric.");
        }
    }
}
