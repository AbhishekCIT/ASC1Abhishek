package com.example.airbooking.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingNotFoundException
 */
public class BookingNotFoundExceptionTest {
    /**
     * Test exception message is set correctly
     */
    @Test
    void testMessage() {
        BookingNotFoundException ex = new BookingNotFoundException("Booking not found");
        assertEquals("Booking not found", ex.getMessage());
    }

    /**
     * Test exception with null message
     */
    @Test
    void testNullMessage() {
        BookingNotFoundException ex = new BookingNotFoundException(null);
        assertNull(ex.getMessage());
    }
}
