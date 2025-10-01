package com.example.airbooking.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ValidationException.
 * Covers normal and edge cases for exception construction and message.
 */
class ValidationExceptionTest {

    /**
     * Test that ValidationException stores and returns the correct message.
     */
    @Test
    void testValidationException_Message() {
        String msg = "Validation error occurred";
        ValidationException ex = new ValidationException(msg);
        assertEquals(msg, ex.getMessage());
    }

    /**
     * Test ValidationException with null message (edge case).
     */
    @Test
    void testValidationException_NullMessage() {
        ValidationException ex = new ValidationException(null);
        assertNull(ex.getMessage());
    }
}
