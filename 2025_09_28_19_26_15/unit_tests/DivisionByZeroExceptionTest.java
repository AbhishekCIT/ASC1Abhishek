package com.example.calculator.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for DivisionByZeroException.
 * Covers constructor and message propagation.
 */
class DivisionByZeroExceptionTest {
    /**
     * Test constructor and message propagation.
     */
    @Test
    @DisplayName("Constructor Sets Message Correctly")
    void testConstructorMessage() {
        DivisionByZeroException ex = new DivisionByZeroException("Division by zero error");
        assertEquals("Division by zero error", ex.getMessage());
    }
}
