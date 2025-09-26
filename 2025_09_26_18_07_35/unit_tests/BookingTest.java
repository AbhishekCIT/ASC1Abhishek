package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
     * Test all getters and setters for normal values.
     */
    @Test
    @DisplayName("Test getters and setters with normal values")
    void testGettersAndSettersNormal() {
        String bookingRef = "BR123";
        String userId = "U001";
        String flightId = "F001";
        LocalDateTime now = LocalDateTime.now();
        String status = "CONFIRMED";

        booking.setBookingRef(bookingRef);
        booking.setUserId(userId);
        booking.setFlightId(flightId);
        booking.setBookingDate(now);
        booking.setStatus(status);

        assertEquals(bookingRef, booking.getBookingRef());
        assertEquals(userId, booking.getUserId());
        assertEquals(flightId, booking.getFlightId());
        assertEquals(now, booking.getBookingDate());
        assertEquals(status, booking.getStatus());
    }

    /**
     * Test setters and getters with null values.
     */
    @Test
    @DisplayName("Test setters and getters with null values")
    void testNullValues() {
        booking.setBookingRef(null);
        booking.setUserId(null);
        booking.setFlightId(null);
        booking.setBookingDate(null);
        booking.setStatus(null);

        assertNull(booking.getBookingRef());
        assertNull(booking.getUserId());
        assertNull(booking.getFlightId());
        assertNull(booking.getBookingDate());
        assertNull(booking.getStatus());
    }

    /**
     * Test setters and getters with empty strings.
     */
    @Test
    @DisplayName("Test setters and getters with empty strings")
    void testEmptyStrings() {
        booking.setBookingRef("");
        booking.setUserId("");
        booking.setFlightId("");
        booking.setStatus("");

        assertEquals("", booking.getBookingRef());
        assertEquals("", booking.getUserId());
        assertEquals("", booking.getFlightId());
        assertEquals("", booking.getStatus());
    }

    /**
     * Test boundary values for bookingDate (far past and far future).
     */
    @Test
    @DisplayName("Test boundary values for bookingDate")
    void testBookingDateBoundaries() {
        LocalDateTime past = LocalDateTime.MIN;
        LocalDateTime future = LocalDateTime.MAX;
        booking.setBookingDate(past);
        assertEquals(past, booking.getBookingDate());
        booking.setBookingDate(future);
        assertEquals(future, booking.getBookingDate());
    }
}
