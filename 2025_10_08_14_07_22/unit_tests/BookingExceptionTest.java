package com.example.airbooking.exception;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for BookingException.
 * Covers normal and edge scenarios for exception message.
 */
class BookingExceptionTest {
    /**
     * Test BookingException with a valid message (normal scenario).
     */
    @Test
    void testBookingException_validMessage() {
        BookingException ex = new BookingException("Booking failed");
        assertEquals("Booking failed", ex.getMessage());
    }

    /**
     * Test BookingException with null message (edge case).
     */
    @Test
    void testBookingException_nullMessage() {
        BookingException ex = new BookingException(null);
        assertNull(ex.getMessage());
    }
}
