package com.example.booking.exception;

import org.junit.jupiter.api.*;

/**
 * Unit tests for SeatUnavailableException.
 */
class SeatUnavailableExceptionTest {
    /**
     * Test exception message is set correctly.
     */
    @Test
    void testExceptionMessage() {
        String msg = "Seat unavailable";
        SeatUnavailableException ex = new SeatUnavailableException(msg);
        Assertions.assertEquals(msg, ex.getMessage());
    }

    /**
     * Test exception with null message (edge case).
     */
    @Test
    void testExceptionNullMessage() {
        SeatUnavailableException ex = new SeatUnavailableException(null);
        Assertions.assertNull(ex.getMessage());
    }
}
