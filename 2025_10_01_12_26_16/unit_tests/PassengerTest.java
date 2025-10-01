package com.example.airlinebooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Passenger entity.
 * Covers all getters, setters, and edge cases.
 */
class PassengerTest {
    private Passenger passenger;

    @BeforeEach
    void setUp() {
        passenger = new Passenger();
    }

    @Test
    @DisplayName("Set and get passengerId")
    void testPassengerId() {
        passenger.setPassengerId("P123");
        assertEquals("P123", passenger.getPassengerId());
    }

    @Test
    @DisplayName("Set and get name")
    void testName() {
        passenger.setName("John Doe");
        assertEquals("John Doe", passenger.getName());
        passenger.setName(null);
        assertNull(passenger.getName());
    }

    @Test
    @DisplayName("Set and get email")
    void testEmail() {
        passenger.setEmail("john@example.com");
        assertEquals("john@example.com", passenger.getEmail());
        passenger.setEmail(null);
        assertNull(passenger.getEmail());
    }

    @Test
    @DisplayName("Set and get phone")
    void testPhone() {
        passenger.setPhone("1234567890");
        assertEquals("1234567890", passenger.getPhone());
        passenger.setPhone(null);
        assertNull(passenger.getPhone());
    }

    @Test
    @DisplayName("Edge case: All fields null")
    void testAllFieldsNull() {
        Passenger empty = new Passenger();
        assertNull(empty.getPassengerId());
        assertNull(empty.getName());
        assertNull(empty.getEmail());
        assertNull(empty.getPhone());
    }
}
