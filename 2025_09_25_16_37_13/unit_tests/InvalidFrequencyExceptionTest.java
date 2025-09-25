package com.example.scheduling.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for InvalidFrequencyException.
 */
public class InvalidFrequencyExceptionTest {

    /**
     * Test exception message is set correctly.
     */
    @Test
    void testExceptionMessage() {
        InvalidFrequencyException ex = new InvalidFrequencyException("Invalid frequency");
        assertEquals("Invalid frequency", ex.getMessage());
    }

    /**
     * Test exception with null message (edge case).
     */
    @Test
    void testExceptionNullMessage() {
        InvalidFrequencyException ex = new InvalidFrequencyException(null);
        assertNull(ex.getMessage());
    }
}
