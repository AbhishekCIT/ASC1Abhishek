package com.example.scheduling.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ReportTypeNotFoundException.
 */
public class ReportTypeNotFoundExceptionTest {

    /**
     * Test exception message is set correctly.
     */
    @Test
    void testExceptionMessage() {
        ReportTypeNotFoundException ex = new ReportTypeNotFoundException("Report type not found");
        assertEquals("Report type not found", ex.getMessage());
    }

    /**
     * Test exception with null message (edge case).
     */
    @Test
    void testExceptionNullMessage() {
        ReportTypeNotFoundException ex = new ReportTypeNotFoundException(null);
        assertNull(ex.getMessage());
    }
}
