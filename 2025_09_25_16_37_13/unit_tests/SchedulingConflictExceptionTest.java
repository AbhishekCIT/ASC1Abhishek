package com.example.scheduling.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SchedulingConflictException.
 */
public class SchedulingConflictExceptionTest {

    /**
     * Test exception message is set correctly.
     */
    @Test
    void testExceptionMessage() {
        SchedulingConflictException ex = new SchedulingConflictException("Scheduling rule conflict");
        assertEquals("Scheduling rule conflict", ex.getMessage());
    }

    /**
     * Test exception with null message (edge case).
     */
    @Test
    void testExceptionNullMessage() {
        SchedulingConflictException ex = new SchedulingConflictException(null);
        assertNull(ex.getMessage());
    }
}
