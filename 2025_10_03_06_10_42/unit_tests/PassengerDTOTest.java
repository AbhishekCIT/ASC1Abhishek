package com.airtransport.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PassengerDTO DTO.
 * Covers normal, edge, and boundary cases for getters and setters.
 */
class PassengerDTOTest {
    private PassengerDTO passengerDTO;

    @BeforeEach
    void setUp() {
        passengerDTO = new PassengerDTO();
    }

    /**
     * Test setting and getting all fields with normal values.
     */
    @Test
    @DisplayName("PassengerDTO: should set and get all fields correctly")
    void testSettersAndGetters_Normal() {
        String name = "John Doe";
        LocalDate dob = LocalDate.of(1990, 1, 1);
        String email = "john@example.com";

        passengerDTO.setName(name);
        passengerDTO.setDob(dob);
        passengerDTO.setEmail(email);

        assertEquals(name, passengerDTO.getName());
        assertEquals(dob, passengerDTO.getDob());
        assertEquals(email, passengerDTO.getEmail());
    }

    /**
     * Test setting fields to null (edge case).
     */
    @Test
    @DisplayName("PassengerDTO: should handle null values in setters")
    void testSetters_NullValues() {
        passengerDTO.setName(null);
        passengerDTO.setDob(null);
        passengerDTO.setEmail(null);

        assertNull(passengerDTO.getName());
        assertNull(passengerDTO.getDob());
        assertNull(passengerDTO.getEmail());
    }

    /**
     * Test setting empty string for name and email (boundary condition).
     */
    @Test
    @DisplayName("PassengerDTO: should handle empty string values")
    void testSetters_EmptyStrings() {
        passengerDTO.setName("");
        passengerDTO.setEmail("");

        assertEquals("", passengerDTO.getName());
        assertEquals("", passengerDTO.getEmail());
    }
}
