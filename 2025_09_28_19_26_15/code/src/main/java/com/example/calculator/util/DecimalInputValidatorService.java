package com.example.calculator.util;

import com.example.calculator.exception.InvalidDecimalInputException;
import org.springframework.stereotype.Component;

/**
 * Utility service to validate decimal input.
 */
@Component
public class DecimalInputValidatorService {
    /**
     * Validates that the input string is a valid decimal number with at most one decimal point.
     * @param input The input string
     * @throws InvalidDecimalInputException if input is invalid
     */
    public void validateDecimal(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidDecimalInputException("Input fields cannot be empty.");
        }
        String trimmed = input.trim();
        int dotCount = 0;
        for (char c : trimmed.toCharArray()) {
            if (c == '.') dotCount++;
        }
        if (dotCount > 1) {
            throw new InvalidDecimalInputException("Invalid decimal input: more than one decimal point.");
        }
        try {
            Double.parseDouble(trimmed);
        } catch (NumberFormatException ex) {
            throw new InvalidDecimalInputException("Invalid decimal input.");
        }
    }
}
