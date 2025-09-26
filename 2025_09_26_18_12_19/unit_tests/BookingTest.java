package com.example.flightbooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Booking entity.
 */
class BookingTest {
    private Booking booking;

    @BeforeEach
    void setUp() {
        booking = new Booking();
    }

    /**
     * Test setting and getting bookingId.
     */
    @Test
    void testBookingId() {
        booking.setBookingId("B123");
        assertEquals("B123", booking.getBookingId());
    }

    /**
     * Test setting and getting userId.
     */
    @Test
    void testUserId() {
        booking.setUserId("U456");
        assertEquals("U456", booking.getUserId());
    }

    /**
     * Test setting and getting flightId.
     */
    @Test
    void testFlightId() {
        booking.setFlightId("F789");
        assertEquals("F789", booking.getFlightId());
    }

    /**
     * Test setting and getting status.
     */
    @Test
    void testStatus() {
        booking.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", booking.getStatus());
    }

    /**
     * Test setting and getting bookedAt.
     */
    @Test
    void testBookedAt() {
        LocalDateTime now = LocalDateTime.now();
        booking.setBookedAt(now);
        assertEquals(now, booking.getBookedAt());
    }

    /**
     * Test setting null values.
     */
    @Test
    void testNullValues() {
        booking.setBookingId(null);
        booking.setUserId(null);
        booking.setFlightId(null);
        booking.setStatus(null);
        booking.setBookedAt(null);
        assertNull(booking.getBookingId());
        assertNull(booking.getUserId());
        assertNull(booking.getFlightId());
        assertNull(booking.getStatus());
        assertNull(booking.getBookedAt());
    }
}
