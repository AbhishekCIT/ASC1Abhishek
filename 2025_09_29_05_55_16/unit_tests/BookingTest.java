package com.example.airtransport.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Booking model.
 * Covers all getters, setters, and edge cases.
 */
class BookingTest {
    private Booking booking;

    @BeforeEach
    void setUp() {
        booking = new Booking();
    }

    @Test
    void testBookingIdGetterSetter() {
        // Purpose: Test normal and null values for bookingId
        booking.setBookingId("B123");
        assertEquals("B123", booking.getBookingId());
        booking.setBookingId(null);
        assertNull(booking.getBookingId());
    }

    @Test
    void testUserIdGetterSetter() {
        // Purpose: Test normal and null values for userId
        booking.setUserId("U001");
        assertEquals("U001", booking.getUserId());
        booking.setUserId(null);
        assertNull(booking.getUserId());
    }

    @Test
    void testFlightIdGetterSetter() {
        // Purpose: Test normal and null values for flightId
        booking.setFlightId("F456");
        assertEquals("F456", booking.getFlightId());
        booking.setFlightId(null);
        assertNull(booking.getFlightId());
    }

    @Test
    void testStatusGetterSetter() {
        // Purpose: Test normal and null values for status
        booking.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", booking.getStatus());
        booking.setStatus(null);
        assertNull(booking.getStatus());
    }

    @Test
    void testTotalPriceGetterSetter() {
        // Purpose: Test normal, zero, and negative values for totalPrice
        booking.setTotalPrice(123.45);
        assertEquals(123.45, booking.getTotalPrice());
        booking.setTotalPrice(0.0);
        assertEquals(0.0, booking.getTotalPrice());
        booking.setTotalPrice(-10.0);
        assertEquals(-10.0, booking.getTotalPrice());
    }

    @Test
    void testBookingDateGetterSetter() {
        // Purpose: Test normal and null values for bookingDate
        booking.setBookingDate("2025-12-01");
        assertEquals("2025-12-01", booking.getBookingDate());
        booking.setBookingDate(null);
        assertNull(booking.getBookingDate());
    }

    @AfterEach
    void tearDown() {
        // Purpose: Clean up after each test if needed
    }
}
