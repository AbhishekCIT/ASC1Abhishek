package com.example.airbooking.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingException.
 * Covers normal and edge cases for exception construction and message.
 */
class BookingExceptionTest {

    /**
     * Test that BookingException stores and returns the correct message.
     */
    @Test
    void testBookingException_Message() {
        String msg = "Booking error occurred";
        BookingException ex = new BookingException(msg);
        assertEquals(msg, ex.getMessage());
    }

    /**
     * Test BookingException with null message (edge case).
     */
    @Test
    void testBookingException_NullMessage() {
        BookingException ex = new BookingException(null);
        assertNull(ex.getMessage());
    }
}
