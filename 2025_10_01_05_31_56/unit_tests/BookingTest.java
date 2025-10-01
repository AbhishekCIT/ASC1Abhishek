package com.example.airbooking.model;

import org.junit.jupiter.api.Test;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Booking entity.
 * Covers normal, edge, and boundary cases for getters and setters.
 */
class BookingTest {

    /**
     * Test setting and getting all fields (normal scenario).
     */
    @Test
    void testBooking_GettersSetters_Normal() {
        Booking booking = new Booking();
        booking.setBookingId(1L);
        booking.setUserId(2L);
        booking.setFlightId(3L);
        booking.setStatus("CONFIRMED");
        Instant now = Instant.now();
        booking.setBookingTime(now);
        assertEquals(1L, booking.getBookingId());
        assertEquals(2L, booking.getUserId());
        assertEquals(3L, booking.getFlightId());
        assertEquals("CONFIRMED", booking.getStatus());
        assertEquals(now, booking.getBookingTime());
    }

    /**
     * Test setting fields to null (edge cases).
     */
    @Test
    void testBooking_SetFields_Null() {
        Booking booking = new Booking();
        booking.setBookingId(null);
        booking.setUserId(null);
        booking.setFlightId(null);
        booking.setStatus(null);
        booking.setBookingTime(null);
        assertNull(booking.getBookingId());
        assertNull(booking.getUserId());
        assertNull(booking.getFlightId());
        assertNull(booking.getStatus());
        assertNull(booking.getBookingTime());
    }

    /**
     * Test setting status to empty string (boundary case).
     */
    @Test
    void testBooking_SetStatus_EmptyString() {
        Booking booking = new Booking();
        booking.setStatus("");
        assertEquals("", booking.getStatus());
    }
}
