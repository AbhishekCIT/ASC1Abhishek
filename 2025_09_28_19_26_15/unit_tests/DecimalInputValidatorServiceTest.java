package com.example.calculator.util;

import com.example.calculator.exception.InvalidDecimalInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for DecimalInputValidatorService.
 * Covers valid decimals, multiple decimal points, empty/null, and invalid input.
 */
class DecimalInputValidatorServiceTest {
    private DecimalInputValidatorService service;

    @BeforeEach
    void setUp() {
        service = new DecimalInputValidatorService();
    }

    /**
     * Test valid decimal input.
     */
    @Test
    @DisplayName("Valid Decimal Input")
    void testValidDecimal() {
        assertDoesNotThrow(() -> service.validateDecimal("3.14"));
        assertDoesNotThrow(() -> service.validateDecimal("42"));
        assertDoesNotThrow(() -> service.validateDecimal("0.0"));
        assertDoesNotThrow(() -> service.validateDecimal("-2.5"));
    }

    /**
     * Test input with multiple decimal points throws exception.
     */
    @Test
    @DisplayName("Multiple Decimal Points Throws Exception")
    void testMultipleDecimalPoints() {
        InvalidDecimalInputException ex = assertThrows(InvalidDecimalInputException.class, () -> service.validateDecimal("3.1.4"));
        assertTrue(ex.getMessage().contains("more than one decimal point"));
    }

    /**
     * Test empty and null input throws exception.
     */
    @Test
    @DisplayName("Empty and Null Input Throws Exception")
    void testEmptyAndNullInput() {
        assertThrows(InvalidDecimalInputException.class, () -> service.validateDecimal(""));
        assertThrows(InvalidDecimalInputException.class, () -> service.validateDecimal(null));
    }

    /**
     * Test invalid decimal input throws exception.
     */
    @Test
    @DisplayName("Invalid Decimal Input Throws Exception")
    void testInvalidDecimalInput() {
        assertThrows(InvalidDecimalInputException.class, () -> service.validateDecimal("abc"));
        assertThrows(InvalidDecimalInputException.class, () -> service.validateDecimal("1a2.3"));
    }
}
