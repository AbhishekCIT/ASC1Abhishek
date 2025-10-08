package com.example.calculator.util;

import org.springframework.stereotype.Component;
import com.example.calculator.exception.InvalidInputException;
import com.example.calculator.exception.OperationNotSupportedException;

/**
 * Utility class for input validation.
 */
@Component
public class InputValidatorUtil {
    /**
     * Validates the calculation inputs.
     * @param number1 First operand
     * @param number2 Second operand
     * @param operation Operation string
     * @throws InvalidInputException if input is invalid
     * @throws OperationNotSupportedException if operation is not supported
     */
    public void validateInputs(Double number1, Double number2, String operation) {
        if (number1 == null || number2 == null) {
            throw new InvalidInputException("Inputs cannot be empty");
        }
        if (operation == null || operation.isEmpty()) {
            throw new InvalidInputException("Operation cannot be empty");
        }
        if (!isNumeric(number1) || !isNumeric(number2)) {
            throw new InvalidInputException("Input must be a number");
        }
        if (!isValidOperation(operation)) {
            throw new OperationNotSupportedException("Invalid operation");
        }
    }

    private boolean isNumeric(Double value) {
        // All non-null Doubles are numeric
        return value != null;
    }

    private boolean isValidOperation(String operation) {
        return operation.equalsIgnoreCase("add") ||
               operation.equalsIgnoreCase("subtract") ||
               operation.equalsIgnoreCase("multiply") ||
               operation.equalsIgnoreCase("divide");
    }
}
