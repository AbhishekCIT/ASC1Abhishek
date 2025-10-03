package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Booking entity
 */
public class BookingTest {
    private Booking booking;
    private User user;
    private Flight flight;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        booking = new Booking();
        user = new User();
        flight = new Flight();
        now = LocalDateTime.now();
    }

    /**
     * Test setting and getting bookingId
     */
    @Test
    void testBookingId() {
        booking.setBookingId(123L);
        assertEquals(123L, booking.getBookingId());
    }

    /**
     * Test setting and getting user
     */
    @Test
    void testUser() {
        booking.setUser(user);
        assertEquals(user, booking.getUser());
    }

    /**
     * Test setting and getting flight
     */
    @Test
    void testFlight() {
        booking.setFlight(flight);
        assertEquals(flight, booking.getFlight());
    }

    /**
     * Test setting and getting status
     */
    @Test
    void testStatus() {
        booking.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", booking.getStatus());
    }

    /**
     * Test setting and getting createdAt
     */
    @Test
    void testCreatedAt() {
        booking.setCreatedAt(now);
        assertEquals(now, booking.getCreatedAt());
    }

    /**
     * Test edge case: null values
     */
    @Test
    void testNullValues() {
        booking.setUser(null);
        booking.setFlight(null);
        booking.setStatus(null);
        booking.setCreatedAt(null);
        assertNull(booking.getUser());
        assertNull(booking.getFlight());
        assertNull(booking.getStatus());
        assertNull(booking.getCreatedAt());
    }
}
