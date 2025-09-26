package com.example.flightbooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    @Test
    @DisplayName("Should set and get bookingId correctly")
    void testBookingId() {
        booking.setBookingId("B123");
        assertEquals("B123", booking.getBookingId(), "BookingId should be set and retrieved correctly");
    }

    @Test
    @DisplayName("Should set and get userId correctly")
    void testUserId() {
        booking.setUserId("U456");
        assertEquals("U456", booking.getUserId(), "UserId should be set and retrieved correctly");
    }

    @Test
    @DisplayName("Should set and get flightId correctly")
    void testFlightId() {
        booking.setFlightId("F789");
        assertEquals("F789", booking.getFlightId(), "FlightId should be set and retrieved correctly");
    }

    @Test
    @DisplayName("Should set and get bookingTime correctly")
    void testBookingTime() {
        LocalDateTime now = LocalDateTime.now();
        booking.setBookingTime(now);
        assertEquals(now, booking.getBookingTime(), "BookingTime should be set and retrieved correctly");
    }

    @Test
    @DisplayName("Should set and get status correctly")
    void testStatus() {
        booking.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", booking.getStatus(), "Status should be set and retrieved correctly");
    }

    @Test
    @DisplayName("Should set and get pnr correctly")
    void testPnr() {
        booking.setPnr("PNR123");
        assertEquals("PNR123", booking.getPnr(), "PNR should be set and retrieved correctly");
    }

    @Test
    @DisplayName("Should handle null values for all fields")
    void testNullValues() {
        booking.setBookingId(null);
        booking.setUserId(null);
        booking.setFlightId(null);
        booking.setBookingTime(null);
        booking.setStatus(null);
        booking.setPnr(null);
        assertNull(booking.getBookingId());
        assertNull(booking.getUserId());
        assertNull(booking.getFlightId());
        assertNull(booking.getBookingTime());
        assertNull(booking.getStatus());
        assertNull(booking.getPnr());
    }
}
