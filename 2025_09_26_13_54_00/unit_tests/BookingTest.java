package com.airtransport.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Booking entity.
 */
public class BookingTest {
    private Booking booking;

    @BeforeEach
    void setUp() {
        booking = new Booking();
    }

    /**
     * Test setting and getting all fields.
     */
    @Test
    void testSettersAndGetters() {
        Long id = 1L;
        Long userId = 2L;
        Long flightId = 3L;
        String status = "CONFIRMED";
        String ticketNumber = "TICKET123";
        LocalDateTime bookingDate = LocalDateTime.now();

        booking.setId(id);
        booking.setUserId(userId);
        booking.setFlightId(flightId);
        booking.setStatus(status);
        booking.setTicketNumber(ticketNumber);
        booking.setBookingDate(bookingDate);

        assertEquals(id, booking.getId());
        assertEquals(userId, booking.getUserId());
        assertEquals(flightId, booking.getFlightId());
        assertEquals(status, booking.getStatus());
        assertEquals(ticketNumber, booking.getTicketNumber());
        assertEquals(bookingDate, booking.getBookingDate());
    }

    /**
     * Test setting null values for optional fields.
     */
    @Test
    void testSettersWithNulls() {
        booking.setStatus(null);
        booking.setTicketNumber(null);
        booking.setBookingDate(null);
        assertNull(booking.getStatus());
        assertNull(booking.getTicketNumber());
        assertNull(booking.getBookingDate());
    }

    /**
     * Test default values after construction.
     */
    @Test
    void testDefaultValues() {
        assertNull(booking.getId());
        assertNull(booking.getUserId());
        assertNull(booking.getFlightId());
        assertNull(booking.getStatus());
        assertNull(booking.getTicketNumber());
        assertNull(booking.getBookingDate());
    }
}
