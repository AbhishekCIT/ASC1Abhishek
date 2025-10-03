package com.example.airbooking.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingOverbookedException
 */
public class BookingOverbookedExceptionTest {
    /**
     * Test exception message is set correctly
     */
    @Test
    void testMessage() {
        BookingOverbookedException ex = new BookingOverbookedException("Overbooked");
        assertEquals("Overbooked", ex.getMessage());
    }

    /**
     * Test exception with null message
     */
    @Test
    void testNullMessage() {
        BookingOverbookedException ex = new BookingOverbookedException(null);
        assertNull(ex.getMessage());
    }
}
