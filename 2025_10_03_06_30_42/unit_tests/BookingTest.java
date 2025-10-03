package com.example.airbooking.entity;

import org.junit.jupiter.api.*;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Booking entity.
 * Covers normal, edge, boundary, and error scenarios for all methods.
 */
class BookingTest {
    private Booking booking;
    private Flight flight;
    private Payment payment;
    private List<Passenger> passengers;

    @BeforeEach
    void setUp() {
        booking = new Booking();
        flight = new Flight();
        flight.setFlightId("FL123");
        payment = new Payment();
        payment.setPaymentId("PM456");
        passengers = new ArrayList<>();
        Passenger p = new Passenger();
        p.setName("John Doe");
        passengers.add(p);
    }

    /**
     * Test setting and getting bookingId (normal scenario).
     */
    @Test
    void testBookingId() {
        booking.setBookingId("BK789");
        assertEquals("BK789", booking.getBookingId());
    }

    /**
     * Test setting and getting flight (normal scenario).
     */
    @Test
    void testFlight() {
        booking.setFlight(flight);
        assertEquals(flight, booking.getFlight());
    }

    /**
     * Test setting and getting bookingDate (normal scenario).
     */
    @Test
    void testBookingDate() {
        LocalDateTime now = LocalDateTime.now();
        booking.setBookingDate(now);
        assertEquals(now, booking.getBookingDate());
    }

    /**
     * Test setting and getting status (normal scenario).
     */
    @Test
    void testStatus() {
        booking.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", booking.getStatus());
    }

    /**
     * Test setting and getting email (normal scenario).
     */
    @Test
    void testEmail() {
        booking.setEmail("user@example.com");
        assertEquals("user@example.com", booking.getEmail());
    }

    /**
     * Test setting and getting passengers (normal scenario).
     */
    @Test
    void testPassengers() {
        booking.setPassengers(passengers);
        assertEquals(passengers, booking.getPassengers());
        assertEquals(1, booking.getPassengers().size());
    }

    /**
     * Test setting and getting payment (normal scenario).
     */
    @Test
    void testPayment() {
        booking.setPayment(payment);
        assertEquals(payment, booking.getPayment());
    }

    /**
     * Test setting null values (edge case).
     */
    @Test
    void testNullValues() {
        booking.setFlight(null);
        booking.setBookingDate(null);
        booking.setStatus(null);
        booking.setEmail(null);
        booking.setPassengers(null);
        booking.setPayment(null);
        assertNull(booking.getFlight());
        assertNull(booking.getBookingDate());
        assertNull(booking.getStatus());
        assertNull(booking.getEmail());
        assertNull(booking.getPassengers());
        assertNull(booking.getPayment());
    }

    /**
     * Test setting empty passenger list (boundary condition).
     */
    @Test
    void testEmptyPassengers() {
        booking.setPassengers(new ArrayList<>());
        assertNotNull(booking.getPassengers());
        assertTrue(booking.getPassengers().isEmpty());
    }
}
