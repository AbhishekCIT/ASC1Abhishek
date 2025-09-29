package com.example.airtransport.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingResponse model.
 * Covers constructor, getters, setters, and edge cases.
 */
class BookingResponseTest {
    private Booking booking;
    private BookingResponse response;

    @BeforeEach
    void setUp() {
        booking = new Booking();
        booking.setBookingId("B123");
        booking.setStatus("CONFIRMED");
        response = new BookingResponse("B123", "CONFIRMED", booking);
    }

    @Test
    void testConstructorAndGetters() {
        // Purpose: Test constructor and getters
        assertEquals("B123", response.getBookingId());
        assertEquals("CONFIRMED", response.getStatus());
        assertEquals(booking, response.getDetails());
    }

    @Test
    void testSetters() {
        // Purpose: Test setters and null values
        response.setBookingId("B456");
        assertEquals("B456", response.getBookingId());
        response.setStatus("CANCELLED");
        assertEquals("CANCELLED", response.getStatus());
        response.setDetails(null);
        assertNull(response.getDetails());
    }

    @Test
    void testNullConstructorArgs() {
        // Purpose: Test constructor with null arguments
        BookingResponse resp = new BookingResponse(null, null, null);
        assertNull(resp.getBookingId());
        assertNull(resp.getStatus());
        assertNull(resp.getDetails());
    }

    @AfterEach
    void tearDown() {
        // Purpose: Clean up after each test if needed
    }
}
