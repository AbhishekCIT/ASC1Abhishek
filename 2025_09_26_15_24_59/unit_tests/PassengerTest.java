package com.airtransport.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Passenger entity.
 */
class PassengerTest {
    /**
     * Test getters and setters for Passenger entity.
     */
    @Test
    @DisplayName("Passenger entity getters and setters work as expected")
    void testPassengerEntityGettersSetters() {
        Passenger passenger = new Passenger();
        Long passengerId = 123L;
        String bookingRef = "BR123456";
        String name = "John Doe";
        String passportNo = "P1234567";

        passenger.setPassengerId(passengerId);
        passenger.setBookingRef(bookingRef);
        passenger.setName(name);
        passenger.setPassportNo(passportNo);

        assertEquals(passengerId, passenger.getPassengerId());
        assertEquals(bookingRef, passenger.getBookingRef());
        assertEquals(name, passenger.getName());
        assertEquals(passportNo, passenger.getPassportNo());
    }

    /**
     * Test default values for Passenger entity.
     */
    @Test
    @DisplayName("Passenger entity default values are null")
    void testPassengerEntityDefaults() {
        Passenger passenger = new Passenger();
        assertNull(passenger.getPassengerId());
        assertNull(passenger.getBookingRef());
        assertNull(passenger.getName());
        assertNull(passenger.getPassportNo());
    }
}
