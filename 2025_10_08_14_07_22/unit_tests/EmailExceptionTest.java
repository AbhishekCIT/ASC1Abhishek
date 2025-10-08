package com.example.airbooking.exception;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for EmailException.
 * Covers normal and edge scenarios for exception message.
 */
class EmailExceptionTest {
    /**
     * Test EmailException with a valid message (normal scenario).
     */
    @Test
    void testEmailException_validMessage() {
        EmailException ex = new EmailException("Email failed");
        assertEquals("Email failed", ex.getMessage());
    }

    /**
     * Test EmailException with null message (edge case).
     */
    @Test
    void testEmailException_nullMessage() {
        EmailException ex = new EmailException(null);
        assertNull(ex.getMessage());
    }
}
