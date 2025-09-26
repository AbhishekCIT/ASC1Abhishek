package com.example.airbooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Booking (POJO).
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
    @DisplayName("Set and get bookingId")
    void testBookingId() {
        booking.setBookingId(10L);
        assertEquals(10L, booking.getBookingId());
    }

    /**
     * Test setting and getting userId.
     */
    @Test
    @DisplayName("Set and get userId")
    void testUserId() {
        booking.setUserId(20L);
        assertEquals(20L, booking.getUserId());
    }

    /**
     * Test setting and getting flightId.
     */
    @Test
    @DisplayName("Set and get flightId")
    void testFlightId() {
        booking.setFlightId(30L);
        assertEquals(30L, booking.getFlightId());
    }

    /**
     * Test setting and getting status.
     */
    @Test
    @DisplayName("Set and get status")
    void testStatus() {
        booking.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", booking.getStatus());
    }

    /**
     * Test setting and getting bookingDate.
     */
    @Test
    @DisplayName("Set and get bookingDate")
    void testBookingDate() {
        LocalDateTime now = LocalDateTime.now();
        booking.setBookingDate(now);
        assertEquals(now, booking.getBookingDate());
    }

    /**
     * Test setting and getting ticketNumber.
     */
    @Test
    @DisplayName("Set and get ticketNumber")
    void testTicketNumber() {
        booking.setTicketNumber("TKT123");
        assertEquals("TKT123", booking.getTicketNumber());
    }

    /**
     * Test default values (should be null).
     */
    @Test
    @DisplayName("Default values are null")
    void testDefaultValues() {
        assertNull(booking.getBookingId());
        assertNull(booking.getUserId());
        assertNull(booking.getFlightId());
        assertNull(booking.getStatus());
        assertNull(booking.getBookingDate());
        assertNull(booking.getTicketNumber());
    }
}
