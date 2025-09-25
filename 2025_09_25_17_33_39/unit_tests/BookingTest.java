package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Booking entity.
 */
class BookingTest {
    private Booking booking;

    @BeforeEach
    void setUp() {
        booking = new Booking();
    }

    @Test
    @DisplayName("Should set and get id correctly")
    void testIdGetterSetter() {
        booking.setId(101L);
        assertEquals(101L, booking.getId());
    }

    @Test
    @DisplayName("Should set and get userId correctly")
    void testUserIdGetterSetter() {
        booking.setUserId(202L);
        assertEquals(202L, booking.getUserId());
    }

    @Test
    @DisplayName("Should set and get flightId correctly")
    void testFlightIdGetterSetter() {
        booking.setFlightId(303L);
        assertEquals(303L, booking.getFlightId());
    }

    @Test
    @DisplayName("Should set and get status correctly")
    void testStatusGetterSetter() {
        booking.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", booking.getStatus());
    }

    @Test
    @DisplayName("Should set and get bookingDate correctly")
    void testBookingDateGetterSetter() {
        LocalDate date = LocalDate.of(2025, 10, 1);
        booking.setBookingDate(date);
        assertEquals(date, booking.getBookingDate());
    }

    @Test
    @DisplayName("Should handle null values for fields")
    void testNullFields() {
        booking.setStatus(null);
        booking.setBookingDate(null);
        assertNull(booking.getStatus());
        assertNull(booking.getBookingDate());
    }
}
