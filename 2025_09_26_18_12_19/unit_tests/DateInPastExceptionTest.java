package com.example.flightbooking.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for DateInPastException.
 */
class DateInPastExceptionTest {

    /**
     * Test that the exception message is set correctly.
     */
    @Test
    void testExceptionMessage() {
        String message = "Travel date cannot be in the past.";
        DateInPastException ex = new DateInPastException(message);
        assertEquals(message, ex.getMessage(), "Exception message should match input");
    }

    /**
     * Test that DateInPastException is a RuntimeException.
     */
    @Test
    void testExceptionType() {
        DateInPastException ex = new DateInPastException("msg");
        assertTrue(ex instanceof RuntimeException, "Should be a RuntimeException");
    }
}
