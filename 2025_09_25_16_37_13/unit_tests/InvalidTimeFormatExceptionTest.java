package com.example.scheduling.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for InvalidTimeFormatException.
 */
public class InvalidTimeFormatExceptionTest {

    /**
     * Test exception message is set correctly.
     */
    @Test
    void testExceptionMessage() {
        InvalidTimeFormatException ex = new InvalidTimeFormatException("Invalid time format");
        assertEquals("Invalid time format", ex.getMessage());
    }

    /**
     * Test exception with null message (edge case).
     */
    @Test
    void testExceptionNullMessage() {
        InvalidTimeFormatException ex = new InvalidTimeFormatException(null);
        assertNull(ex.getMessage());
    }
}
