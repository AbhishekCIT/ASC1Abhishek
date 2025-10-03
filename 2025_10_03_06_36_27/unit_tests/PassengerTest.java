package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Passenger entity
 */
public class PassengerTest {
    private Passenger passenger;
    private Booking booking;

    @BeforeEach
    void setUp() {
        passenger = new Passenger();
        booking = new Booking();
    }

    /**
     * Test setting and getting passengerId
     */
    @Test
    void testPassengerId() {
        passenger.setPassengerId(7L);
        assertEquals(7L, passenger.getPassengerId());
    }

    /**
     * Test setting and getting booking
     */
    @Test
    void testBooking() {
        passenger.setBooking(booking);
        assertEquals(booking, passenger.getBooking());
    }

    /**
     * Test setting and getting name
     */
    @Test
    void testName() {
        passenger.setName("Alice");
        assertEquals("Alice", passenger.getName());
    }

    /**
     * Test setting and getting age
     */
    @Test
    void testAge() {
        passenger.setAge(30);
        assertEquals(30, passenger.getAge());
    }

    /**
     * Test setting and getting passportNumber
     */
    @Test
    void testPassportNumber() {
        passenger.setPassportNumber("P1234567");
        assertEquals("P1234567", passenger.getPassportNumber());
    }

    /**
     * Test edge case: negative age
     */
    @Test
    void testNegativeAge() {
        passenger.setAge(-5);
        assertEquals(-5, passenger.getAge());
    }

    /**
     * Test edge case: null values
     */
    @Test
    void testNullValues() {
        passenger.setBooking(null);
        passenger.setName(null);
        passenger.setPassportNumber(null);
        assertNull(passenger.getBooking());
        assertNull(passenger.getName());
        assertNull(passenger.getPassportNumber());
    }
}
