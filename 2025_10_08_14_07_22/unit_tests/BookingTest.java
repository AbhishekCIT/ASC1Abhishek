package com.example.airbooking.entity;

import org.junit.jupiter.api.*;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Booking entity.
 * Covers normal, edge, and boundary scenarios for all fields and methods.
 */
class BookingTest {
    private Booking booking;

    @BeforeEach
    void setUp() {
        booking = new Booking();
    }

    /**
     * Test setting and getting bookingId (normal scenario).
     */
    @Test
    void testBookingId_setAndGet() {
        booking.setBookingId(123L);
        assertEquals(123L, booking.getBookingId());
    }

    /**
     * Test setting and getting user (normal scenario).
     */
    @Test
    void testUser_setAndGet() {
        User user = new User();
        booking.setUser(user);
        assertEquals(user, booking.getUser());
    }

    /**
     * Test setting and getting flight (normal scenario).
     */
    @Test
    void testFlight_setAndGet() {
        Flight flight = new Flight();
        booking.setFlight(flight);
        assertEquals(flight, booking.getFlight());
    }

    /**
     * Test setting and getting status (normal scenario).
     */
    @Test
    void testStatus_setAndGet() {
        booking.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", booking.getStatus());
    }

    /**
     * Test setting and getting passengerDetails (normal scenario).
     */
    @Test
    void testPassengerDetails_setAndGet() {
        booking.setPassengerDetails("John Doe, Seat 12A");
        assertEquals("John Doe, Seat 12A", booking.getPassengerDetails());
    }

    /**
     * Test setting and getting createdAt (normal scenario).
     */
    @Test
    void testCreatedAt_setAndGet() {
        LocalDateTime now = LocalDateTime.now();
        booking.setCreatedAt(now);
        assertEquals(now, booking.getCreatedAt());
    }

    /**
     * Test setting null values (edge case).
     */
    @Test
    void testSetNullValues() {
        booking.setUser(null);
        booking.setFlight(null);
        booking.setStatus(null);
        booking.setPassengerDetails(null);
        booking.setCreatedAt(null);
        assertNull(booking.getUser());
        assertNull(booking.getFlight());
        assertNull(booking.getStatus());
        assertNull(booking.getPassengerDetails());
        assertNull(booking.getCreatedAt());
    }

    /**
     * Test boundary condition for bookingId (edge case).
     */
    @Test
    void testBookingId_boundaryValues() {
        booking.setBookingId(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, booking.getBookingId());
        booking.setBookingId(Long.MIN_VALUE);
        assertEquals(Long.MIN_VALUE, booking.getBookingId());
    }
}
