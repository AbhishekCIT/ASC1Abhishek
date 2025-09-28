package com.example.calculator.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for InvalidDecimalInputException.
 * Covers constructor and message propagation.
 */
class InvalidDecimalInputExceptionTest {
    /**
     * Test constructor and message propagation.
     */
    @Test
    @DisplayName("Constructor Sets Message Correctly")
    void testConstructorMessage() {
        InvalidDecimalInputException ex = new InvalidDecimalInputException("Invalid decimal input");
        assertEquals("Invalid decimal input", ex.getMessage());
    }
}
