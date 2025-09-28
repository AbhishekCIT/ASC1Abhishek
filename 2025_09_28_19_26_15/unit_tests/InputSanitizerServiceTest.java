package com.example.calculator.util;

import com.example.calculator.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for InputSanitizerService.
 * Covers valid input, empty/null, and non-numeric input.
 */
class InputSanitizerServiceTest {
    private InputSanitizerService service;

    @BeforeEach
    void setUp() {
        service = new InputSanitizerService();
    }

    /**
     * Test valid numeric input.
     */
    @Test
    @DisplayName("Valid Numeric Input")
    void testValidInput() {
        assertEquals(3.14, service.sanitizeAndValidate("3.14", "num1"));
        assertEquals(-2.0, service.sanitizeAndValidate("-2.0", "num2"));
        assertEquals(0.0, service.sanitizeAndValidate("0", "num1"));
    }

    /**
     * Test empty and null input throws exception.
     */
    @Test
    @DisplayName("Empty and Null Input Throws Exception")
    void testEmptyAndNullInput() {
        assertThrows(InvalidInputException.class, () -> service.sanitizeAndValidate("", "num1"));
        assertThrows(InvalidInputException.class, () -> service.sanitizeAndValidate(null, "num2"));
    }

    /**
     * Test non-numeric input throws exception.
     */
    @Test
    @DisplayName("Non-Numeric Input Throws Exception")
    void testNonNumericInput() {
        assertThrows(InvalidInputException.class, () -> service.sanitizeAndValidate("abc", "num1"));
        assertThrows(InvalidInputException.class, () -> service.sanitizeAndValidate("1.2.3", "num2"));
    }
}
