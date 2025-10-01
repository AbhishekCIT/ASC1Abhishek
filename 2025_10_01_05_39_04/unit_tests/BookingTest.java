package com.airline.booking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Booking entity.
 * Purpose: Test getters, setters, and entity relationships for normal, edge, and boundary scenarios.
 */
public class BookingTest {
    private Booking booking;
    private Passenger passenger;
    private Flight flight;
    private Seat seat;

    @BeforeEach
    void setUp() {
        booking = new Booking();
        passenger = new Passenger();
        flight = new Flight();
        seat = new Seat();
    }

    /**
     * Test setting and getting all fields (normal scenario).
     */
    @Test
    void testSetAndGetFields() {
        booking.setId("B123");
        booking.setPassenger(passenger);
        booking.setFlight(flight);
        booking.setSeat(seat);
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus("CONFIRMED");

        assertEquals("B123", booking.getId());
        assertEquals(passenger, booking.getPassenger());
        assertEquals(flight, booking.getFlight());
        assertEquals(seat, booking.getSeat());
        assertNotNull(booking.getBookingTime());
        assertEquals("CONFIRMED", booking.getStatus());
    }

    /**
     * Test setting null values (edge case).
     */
    @Test
    void testSetNullFields() {
        booking.setPassenger(null);
        booking.setFlight(null);
        booking.setSeat(null);
        booking.setBookingTime(null);
        booking.setStatus(null);

        assertNull(booking.getPassenger());
        assertNull(booking.getFlight());
        assertNull(booking.getSeat());
        assertNull(booking.getBookingTime());
        assertNull(booking.getStatus());
    }

    /**
     * Test boundary condition for status field (empty string).
     */
    @Test
    void testStatusBoundaryCondition() {
        booking.setStatus("");
        assertEquals("", booking.getStatus());
    }
}
