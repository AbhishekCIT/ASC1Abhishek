package com.example.airbooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Booking entity.
 */
public class BookingTest {
    private Booking booking;
    private Flight flight;
    private Passenger passenger;
    private Payment payment;

    @BeforeEach
    void setUp() {
        booking = new Booking();
        flight = new Flight();
        passenger = new Passenger();
        payment = new Payment();
    }

    /**
     * Test setting and getting booking fields.
     */
    @Test
    void testBookingFields() {
        booking.setId(1L);
        booking.setFlight(flight);
        booking.setBookingDate(LocalDateTime.now());
        booking.setBookingRef("ABC123");
        booking.setStatus("CONFIRMED");
        booking.setPassengers(Arrays.asList(passenger));
        booking.setPayment(payment);

        assertEquals(1L, booking.getId());
        assertEquals(flight, booking.getFlight());
        assertNotNull(booking.getBookingDate());
        assertEquals("ABC123", booking.getBookingRef());
        assertEquals("CONFIRMED", booking.getStatus());
        assertEquals(1, booking.getPassengers().size());
        assertEquals(payment, booking.getPayment());
    }

    /**
     * Test edge case: null passengers list.
     */
    @Test
    void testNullPassengers() {
        booking.setPassengers(null);
        assertNull(booking.getPassengers());
    }

    /**
     * Test edge case: empty bookingRef.
     */
    @Test
    void testEmptyBookingRef() {
        booking.setBookingRef("");
        assertEquals("", booking.getBookingRef());
    }
}
