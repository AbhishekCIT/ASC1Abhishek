package com.example.calculator.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for InvalidInputException.
 * Covers constructor and message propagation.
 */
class InvalidInputExceptionTest {
    /**
     * Test constructor and message propagation.
     */
    @Test
    @DisplayName("Constructor Sets Message Correctly")
    void testConstructorMessage() {
        InvalidInputException ex = new InvalidInputException("Invalid input");
        assertEquals("Invalid input", ex.getMessage());
    }
}
