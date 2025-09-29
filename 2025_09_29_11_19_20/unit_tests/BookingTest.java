package com.example.airbooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Booking entity.
 */
class BookingTest {
    private Booking booking;
    private Flight flight;
    private Passenger passenger;

    @BeforeEach
    void setUp() {
        booking = new Booking();
        flight = new Flight();
        passenger = new Passenger();
    }

    @Test
    @DisplayName("Test setting and getting id")
    void testId() {
        booking.setId(100L);
        assertEquals(100L, booking.getId());
    }

    @Test
    @DisplayName("Test setting and getting flight")
    void testFlight() {
        booking.setFlight(flight);
        assertSame(flight, booking.getFlight());
    }

    @Test
    @DisplayName("Test setting and getting passenger")
    void testPassenger() {
        booking.setPassenger(passenger);
        assertSame(passenger, booking.getPassenger());
    }

    @Test
    @DisplayName("Test setting and getting seat")
    void testSeat() {
        booking.setSeat("12A");
        assertEquals("12A", booking.getSeat());
    }

    @Test
    @DisplayName("Test setting and getting status")
    void testStatus() {
        booking.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", booking.getStatus());
    }

    @Test
    @DisplayName("Test setting and getting createdAt")
    void testCreatedAt() {
        LocalDateTime now = LocalDateTime.now();
        booking.setCreatedAt(now);
        assertEquals(now, booking.getCreatedAt());
    }

    @Test
    @DisplayName("Test null values for fields")
    void testNullFields() {
        booking.setFlight(null);
        booking.setPassenger(null);
        booking.setSeat(null);
        booking.setStatus(null);
        booking.setCreatedAt(null);
        assertNull(booking.getFlight());
        assertNull(booking.getPassenger());
        assertNull(booking.getSeat());
        assertNull(booking.getStatus());
        assertNull(booking.getCreatedAt());
    }
}
