package com.example.airbooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Passenger entity.
 */
class PassengerTest {
    private Passenger passenger;

    @BeforeEach
    void setUp() {
        passenger = new Passenger();
    }

    @Test
    @DisplayName("Test setting and getting id")
    void testId() {
        passenger.setId(5L);
        assertEquals(5L, passenger.getId());
    }

    @Test
    @DisplayName("Test setting and getting name")
    void testName() {
        passenger.setName("John Doe");
        assertEquals("John Doe", passenger.getName());
    }

    @Test
    @DisplayName("Test setting and getting email")
    void testEmail() {
        passenger.setEmail("john@example.com");
        assertEquals("john@example.com", passenger.getEmail());
    }

    @Test
    @DisplayName("Test setting and getting phone")
    void testPhone() {
        passenger.setPhone("1234567890");
        assertEquals("1234567890", passenger.getPhone());
    }

    @Test
    @DisplayName("Test setting and getting documentType")
    void testDocumentType() {
        passenger.setDocumentType("Passport");
        assertEquals("Passport", passenger.getDocumentType());
    }

    @Test
    @DisplayName("Test setting and getting documentNumber")
    void testDocumentNumber() {
        passenger.setDocumentNumber("A1234567");
        assertEquals("A1234567", passenger.getDocumentNumber());
    }

    @Test
    @DisplayName("Test null values for fields")
    void testNullFields() {
        passenger.setName(null);
        passenger.setEmail(null);
        passenger.setPhone(null);
        passenger.setDocumentType(null);
        passenger.setDocumentNumber(null);
        assertNull(passenger.getName());
        assertNull(passenger.getEmail());
        assertNull(passenger.getPhone());
        assertNull(passenger.getDocumentType());
        assertNull(passenger.getDocumentNumber());
    }
}
