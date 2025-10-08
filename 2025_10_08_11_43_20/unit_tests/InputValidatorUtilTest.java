package com.example.calculator.util;

import com.example.calculator.exception.InvalidInputException;
import com.example.calculator.exception.OperationNotSupportedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for InputValidatorUtil.
 * Purpose: Test all input validation scenarios, including edge cases and error handling.
 */
public class InputValidatorUtilTest {
    private InputValidatorUtil validator;

    @BeforeEach
    void setUp() {
        validator = new InputValidatorUtil();
    }

    /**
     * Test valid inputs for all operations.
     */
    @Test
    void testValidateInputs_Valid() {
        assertDoesNotThrow(() -> validator.validateInputs(1.0, 2.0, "add"));
        assertDoesNotThrow(() -> validator.validateInputs(1.0, 2.0, "subtract"));
        assertDoesNotThrow(() -> validator.validateInputs(1.0, 2.0, "multiply"));
        assertDoesNotThrow(() -> validator.validateInputs(1.0, 2.0, "divide"));
    }

    /**
     * Test null number1 throws InvalidInputException.
     */
    @Test
    void testValidateInputs_NullNumber1() {
        InvalidInputException ex = assertThrows(InvalidInputException.class, () -> validator.validateInputs(null, 2.0, "add"));
        assertEquals("Inputs cannot be empty", ex.getMessage());
    }

    /**
     * Test null number2 throws InvalidInputException.
     */
    @Test
    void testValidateInputs_NullNumber2() {
        InvalidInputException ex = assertThrows(InvalidInputException.class, () -> validator.validateInputs(1.0, null, "add"));
        assertEquals("Inputs cannot be empty", ex.getMessage());
    }

    /**
     * Test null operation throws InvalidInputException.
     */
    @Test
    void testValidateInputs_NullOperation() {
        InvalidInputException ex = assertThrows(InvalidInputException.class, () -> validator.validateInputs(1.0, 2.0, null));
        assertEquals("Operation cannot be empty", ex.getMessage());
    }

    /**
     * Test empty operation throws InvalidInputException.
     */
    @Test
    void testValidateInputs_EmptyOperation() {
        InvalidInputException ex = assertThrows(InvalidInputException.class, () -> validator.validateInputs(1.0, 2.0, ""));
        assertEquals("Operation cannot be empty", ex.getMessage());
    }

    /**
     * Test invalid operation throws OperationNotSupportedException.
     */
    @Test
    void testValidateInputs_InvalidOperation() {
        OperationNotSupportedException ex = assertThrows(OperationNotSupportedException.class, () -> validator.validateInputs(1.0, 2.0, "mod"));
        assertEquals("Invalid operation", ex.getMessage());
    }
}
