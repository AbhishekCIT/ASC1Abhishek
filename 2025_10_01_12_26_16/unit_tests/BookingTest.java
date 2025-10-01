package com.example.airlinebooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Booking entity.
 * Covers all getters, setters, and edge cases.
 */
class BookingTest {
    private Booking booking;
    private Flight flight;
    private Passenger passenger;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        booking = new Booking();
        flight = new Flight();
        passenger = new Passenger();
        now = LocalDateTime.now();
    }

    @Test
    @DisplayName("Set and get bookingRef")
    void testBookingRef() {
        booking.setBookingRef("BR123");
        assertEquals("BR123", booking.getBookingRef());
    }

    @Test
    @DisplayName("Set and get flight")
    void testFlight() {
        booking.setFlight(flight);
        assertEquals(flight, booking.getFlight());
    }

    @Test
    @DisplayName("Set and get passenger")
    void testPassenger() {
        booking.setPassenger(passenger);
        assertEquals(passenger, booking.getPassenger());
    }

    @Test
    @DisplayName("Set and get status")
    void testStatus() {
        booking.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", booking.getStatus());
        booking.setStatus(null);
        assertNull(booking.getStatus());
    }

    @Test
    @DisplayName("Set and get bookingDate")
    void testBookingDate() {
        booking.setBookingDate(now);
        assertEquals(now, booking.getBookingDate());
        booking.setBookingDate(null);
        assertNull(booking.getBookingDate());
    }

    @Test
    @DisplayName("Edge case: All fields null")
    void testAllFieldsNull() {
        Booking empty = new Booking();
        assertNull(empty.getBookingRef());
        assertNull(empty.getFlight());
        assertNull(empty.getPassenger());
        assertNull(empty.getStatus());
        assertNull(empty.getBookingDate());
    }
}
