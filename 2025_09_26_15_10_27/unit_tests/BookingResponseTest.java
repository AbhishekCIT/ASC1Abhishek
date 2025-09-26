package com.example.airtransport.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingResponse model.
 */
class BookingResponseTest {

    @Test
    @DisplayName("Test getters and setters for BookingResponse")
    void testGettersAndSetters() {
        BookingResponse response = new BookingResponse();
        response.setBookingRef("BR123456");
        response.setStatus("CONFIRMED");
        Booking booking = new Booking();
        booking.setFlightId("F123");
        response.setTicket(booking);

        assertEquals("BR123456", response.getBookingRef());
        assertEquals("CONFIRMED", response.getStatus());
        assertEquals(booking, response.getTicket());
    }

    @Test
    @DisplayName("Test BookingResponse with null fields")
    void testNullFields() {
        BookingResponse response = new BookingResponse();
        assertNull(response.getBookingRef());
        assertNull(response.getStatus());
        assertNull(response.getTicket());
    }
}
