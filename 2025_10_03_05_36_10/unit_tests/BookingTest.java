package com.airtransport.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Booking model.
 * Covers all getters and setters, including edge cases.
 */
public class BookingTest {
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
     * Test setting and getting status.
     */
    @Test
    void testStatus() {
        booking.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", booking.getStatus());
    }

    /**
     * Test setting and getting bookingDate.
     */
    @Test
    void testBookingDate() {
        LocalDateTime now = LocalDateTime.now();
        booking.setBookingDate(now);
        assertEquals(now, booking.getBookingDate());
    }

    /**
     * Test setting and getting totalAmount.
     */
    @Test
    void testTotalAmount() {
        booking.setTotalAmount(250.0);
        assertEquals(250.0, booking.getTotalAmount());
    }

    /**
     * Test setting and getting passengers.
     * Purpose: Normal and edge case (empty list).
     */
    @Test
    void testPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        Passenger p = new Passenger();
        p.setName("John Doe");
        passengers.add(p);
        booking.setPassengers(passengers);
        assertEquals(passengers, booking.getPassengers());

        booking.setPassengers(new ArrayList<>());
        assertTrue(booking.getPassengers().isEmpty());
    }

    /**
     * Test setting and getting flight.
     * Purpose: Normal and null case.
     */
    @Test
    void testFlight() {
        Flight flight = new Flight();
        flight.setFlightId(1);
        booking.setFlight(flight);
        assertEquals(flight, booking.getFlight());

        booking.setFlight(null);
        assertNull(booking.getFlight());
    }

    /**
     * Test setting and getting payment.
     * Purpose: Normal and null case.
     */
    @Test
    void testPayment() {
        Payment payment = new Payment();
        payment.setPaymentId("P123");
        booking.setPayment(payment);
        assertEquals(payment, booking.getPayment());

        booking.setPayment(null);
        assertNull(booking.getPayment());
    }
}
