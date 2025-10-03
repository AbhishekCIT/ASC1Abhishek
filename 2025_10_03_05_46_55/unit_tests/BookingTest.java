package com.asc.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Booking entity.
 * Covers constructors, getters/setters, equals/hashCode, and edge cases.
 */
class BookingTest {

    private Booking booking;
    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight();
        booking = new Booking("CONF123", flight, "John Doe", "john@example.com", LocalDateTime.now(), "success");
    }

    /**
     * Purpose: Test all-args constructor and getters.
     */
    @Test
    void testAllArgsConstructorAndGetters() {
        assertEquals("CONF123", booking.getConfirmationId());
        assertEquals(flight, booking.getFlight());
        assertEquals("John Doe", booking.getPassengerName());
        assertEquals("john@example.com", booking.getPassengerEmail());
        assertEquals("success", booking.getPaymentStatus());
        assertNotNull(booking.getBookingDate());
    }

    /**
     * Purpose: Test no-args constructor and setters.
     */
    @Test
    void testNoArgsConstructorAndSetters() {
        Booking b = new Booking();
        b.setConfirmationId("CONF456");
        b.setFlight(flight);
        b.setPassengerName("Jane Doe");
        b.setPassengerEmail("jane@example.com");
        b.setBookingDate(LocalDateTime.of(2025, 1, 1, 10, 0));
        b.setPaymentStatus("failed");
        assertEquals("CONF456", b.getConfirmationId());
        assertEquals(flight, b.getFlight());
        assertEquals("Jane Doe", b.getPassengerName());
        assertEquals("jane@example.com", b.getPassengerEmail());
        assertEquals(LocalDateTime.of(2025, 1, 1, 10, 0), b.getBookingDate());
        assertEquals("failed", b.getPaymentStatus());
    }

    /**
     * Purpose: Test equals and hashCode for identical objects.
     */
    @Test
    void testEqualsAndHashCode() {
        Booking b2 = new Booking("CONF123", flight, "John Doe", "john@example.com", booking.getBookingDate(), "success");
        assertEquals(booking, b2);
        assertEquals(booking.hashCode(), b2.hashCode());
    }

    /**
     * Purpose: Test equals for different objects.
     */
    @Test
    void testEqualsDifferentObjects() {
        Booking b2 = new Booking("CONF999", flight, "Jane Doe", "jane@example.com", LocalDateTime.now(), "failed");
        assertNotEquals(booking, b2);
    }

    /**
     * Purpose: Test edge case with null values.
     */
    @Test
    void testNullValues() {
        Booking b = new Booking(null, null, null, null, null, null);
        assertNull(b.getConfirmationId());
        assertNull(b.getFlight());
        assertNull(b.getPassengerName());
        assertNull(b.getPassengerEmail());
        assertNull(b.getBookingDate());
        assertNull(b.getPaymentStatus());
    }
}
