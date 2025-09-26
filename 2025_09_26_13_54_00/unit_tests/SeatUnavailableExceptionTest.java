package com.airtransport.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SeatUnavailableException.
 */
public class SeatUnavailableExceptionTest {
    /**
     * Test exception message is set correctly.
     */
    @Test
    void testExceptionMessage() {
        String message = "Seat not available";
        SeatUnavailableException ex = new SeatUnavailableException(message);
        assertEquals(message, ex.getMessage());
    }

    /**
     * Test exception is instance of RuntimeException.
     */
    @Test
    void testExceptionInheritance() {
        SeatUnavailableException ex = new SeatUnavailableException("msg");
        assertTrue(ex instanceof RuntimeException);
    }
}
