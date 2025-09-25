package com.example.scheduling.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for UnauthorizedAccessException.
 */
public class UnauthorizedAccessExceptionTest {

    /**
     * Test exception message is set correctly.
     */
    @Test
    void testExceptionMessage() {
        UnauthorizedAccessException ex = new UnauthorizedAccessException("Unauthorized access");
        assertEquals("Unauthorized access", ex.getMessage());
    }

    /**
     * Test exception with null message (edge case).
     */
    @Test
    void testExceptionNullMessage() {
        UnauthorizedAccessException ex = new UnauthorizedAccessException(null);
        assertNull(ex.getMessage());
    }
}
