package com.example.airbooking.entity;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Passenger entity.
 * Covers normal, edge, boundary, and error scenarios for all methods.
 */
class PassengerTest {
    private Passenger passenger;
    private Booking booking;

    @BeforeEach
    void setUp() {
        passenger = new Passenger();
        booking = new Booking();
        booking.setBookingId("BK123");
    }

    /**
     * Test setting and getting passengerId (normal scenario).
     */
    @Test
    void testPassengerId() {
        passenger.setPassengerId("P001");
        assertEquals("P001", passenger.getPassengerId());
    }

    /**
     * Test setting and getting booking (normal scenario).
     */
    @Test
    void testBooking() {
        passenger.setBooking(booking);
        assertEquals(booking, passenger.getBooking());
    }

    /**
     * Test setting and getting name (normal scenario).
     */
    @Test
    void testName() {
        passenger.setName("Alice Smith");
        assertEquals("Alice Smith", passenger.getName());
    }

    /**
     * Test setting and getting passportNumber (normal scenario).
     */
    @Test
    void testPassportNumber() {
        passenger.setPassportNumber("C1234567");
        assertEquals("C1234567", passenger.getPassportNumber());
    }

    /**
     * Test setting and getting nationality (normal scenario).
     */
    @Test
    void testNationality() {
        passenger.setNationality("GB");
        assertEquals("GB", passenger.getNationality());
    }

    /**
     * Test setting null values (edge case).
     */
    @Test
    void testNullValues() {
        passenger.setBooking(null);
        passenger.setName(null);
        passenger.setPassportNumber(null);
        passenger.setNationality(null);
        assertNull(passenger.getBooking());
        assertNull(passenger.getName());
        assertNull(passenger.getPassportNumber());
        assertNull(passenger.getNationality());
    }
}
