package com.airtransport.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Passenger model.
 * Covers all getters and setters, including edge and boundary cases.
 */
public class PassengerTest {
    private Passenger passenger;

    @BeforeEach
    void setUp() {
        passenger = new Passenger();
    }

    /**
     * Test setting and getting id.
     */
    @Test
    void testId() {
        passenger.setId(1L);
        assertEquals(1L, passenger.getId());
        passenger.setId(null);
        assertNull(passenger.getId());
    }

    /**
     * Test setting and getting name.
     */
    @Test
    void testName() {
        passenger.setName("Alice");
        assertEquals("Alice", passenger.getName());
        passenger.setName("");
        assertEquals("", passenger.getName());
        passenger.setName(null);
        assertNull(passenger.getName());
    }

    /**
     * Test setting and getting passportNumber.
     */
    @Test
    void testPassportNumber() {
        passenger.setPassportNumber("A1234567");
        assertEquals("A1234567", passenger.getPassportNumber());
        passenger.setPassportNumber(null);
        assertNull(passenger.getPassportNumber());
    }

    /**
     * Test setting and getting booking.
     */
    @Test
    void testBooking() {
        Booking booking = new Booking();
        booking.setBookingId("B123");
        passenger.setBooking(booking);
        assertEquals(booking, passenger.getBooking());
        passenger.setBooking(null);
        assertNull(passenger.getBooking());
    }
}
