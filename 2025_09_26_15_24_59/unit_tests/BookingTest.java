package com.airtransport.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Booking entity.
 */
class BookingTest {
    /**
     * Test getters and setters for Booking entity.
     */
    @Test
    @DisplayName("Booking entity getters and setters work as expected")
    void testBookingEntityGettersSetters() {
        Booking booking = new Booking();
        String bookingRef = "BR123456";
        String userId = "U789";
        String flightId = "F456";
        LocalDateTime bookingDate = LocalDateTime.now();
        String status = "CONFIRMED";

        booking.setBookingRef(bookingRef);
        booking.setUserId(userId);
        booking.setFlightId(flightId);
        booking.setBookingDate(bookingDate);
        booking.setStatus(status);

        assertEquals(bookingRef, booking.getBookingRef());
        assertEquals(userId, booking.getUserId());
        assertEquals(flightId, booking.getFlightId());
        assertEquals(bookingDate, booking.getBookingDate());
        assertEquals(status, booking.getStatus());
    }

    /**
     * Test default values for Booking entity.
     */
    @Test
    @DisplayName("Booking entity default values are null")
    void testBookingEntityDefaults() {
        Booking booking = new Booking();
        assertNull(booking.getBookingRef());
        assertNull(booking.getUserId());
        assertNull(booking.getFlightId());
        assertNull(booking.getBookingDate());
        assertNull(booking.getStatus());
    }
}
