package com.airline.flightbooking.dto;

import com.airline.flightbooking.model.Booking;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for BookingResponse DTO.
 * Covers normal and edge cases for data transfer object.
 */
class BookingResponseTest {
    /**
     * Test all-args constructor and getters.
     */
    @Test
    void testAllArgsConstructor() {
        Booking booking = new Booking();
        BookingResponse response = new BookingResponse("BK456", "CONFIRMED", booking);
        assertEquals("BK456", response.getBookingId());
        assertEquals("CONFIRMED", response.getStatus());
        assertEquals(booking, response.getDetails());
    }

    /**
     * Test no-args constructor and setters.
     */
    @Test
    void testNoArgsConstructorAndSetters() {
        BookingResponse response = new BookingResponse();
        Booking booking = new Booking();
        response.setBookingId("BK789");
        response.setStatus("PENDING");
        response.setDetails(booking);
        assertEquals("BK789", response.getBookingId());
        assertEquals("PENDING", response.getStatus());
        assertEquals(booking, response.getDetails());
    }

    /**
     * Test edge case: null details.
     */
    @Test
    void testNullDetails() {
        BookingResponse response = new BookingResponse("BK000", "CANCELLED", null);
        assertNull(response.getDetails());
    }
}
