package com.example.airbooking.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Passenger entity.
 */
public class PassengerTest {
    /**
     * Test setting and getting fields.
     */
    @Test
    void testFields() {
        Passenger passenger = new Passenger();
        Booking booking = new Booking();
        passenger.setId(1L);
        passenger.setBooking(booking);
        passenger.setName("John Doe");
        passenger.setEmail("john@example.com");
        passenger.setPhone("1234567890");
        passenger.setDocumentNumber("A1234567");

        assertEquals(1L, passenger.getId());
        assertEquals(booking, passenger.getBooking());
        assertEquals("John Doe", passenger.getName());
        assertEquals("john@example.com", passenger.getEmail());
        assertEquals("1234567890", passenger.getPhone());
        assertEquals("A1234567", passenger.getDocumentNumber());
    }

    /**
     * Test edge case: null booking.
     */
    @Test
    void testNullBooking() {
        Passenger passenger = new Passenger();
        passenger.setBooking(null);
        assertNull(passenger.getBooking());
    }
}
