package com.example.calculator.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for InvalidKeyException.
 * Covers constructor and message propagation.
 */
class InvalidKeyExceptionTest {
    /**
     * Test constructor and message propagation.
     */
    @Test
    @DisplayName("Constructor Sets Message Correctly")
    void testConstructorMessage() {
        InvalidKeyException ex = new InvalidKeyException("Invalid key pressed");
        assertEquals("Invalid key pressed", ex.getMessage());
    }
}
