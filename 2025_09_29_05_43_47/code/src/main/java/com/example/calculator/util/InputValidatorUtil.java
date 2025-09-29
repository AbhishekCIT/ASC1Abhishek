package com.example.calculator.util;

import com.example.calculator.exception.InvalidInputException;
import org.springframework.stereotype.Component;

/**
 * Utility class for input validation
 */
@Component
public class InputValidatorUtil {
    /**
     * Validates if the input is a valid number
     * @param value String value to validate
     * @param fieldName Name of the field for error reporting
     */
    public static void validateNumeric(String value, String fieldName) {
        try {
            new java.math.BigDecimal(value);
        } catch (NumberFormatException ex) {
            throw new InvalidInputException("Input must be a number for field: " + fieldName);
        }
    }
}
