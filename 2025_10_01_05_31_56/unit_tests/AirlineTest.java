package com.example.airbooking.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Airline entity.
 * Covers normal, edge, and boundary cases for getters and setters.
 */
class AirlineTest {

    /**
     * Test setting and getting airlineId and name (normal scenario).
     */
    @Test
    void testAirline_GettersSetters_Normal() {
        Airline airline = new Airline();
        airline.setAirlineId(10L);
        airline.setName("Test Airline");
        assertEquals(10L, airline.getAirlineId());
        assertEquals("Test Airline", airline.getName());
    }

    /**
     * Test setting airlineId to null (edge case).
     */
    @Test
    void testAirline_SetAirlineId_Null() {
        Airline airline = new Airline();
        airline.setAirlineId(null);
        assertNull(airline.getAirlineId());
    }

    /**
     * Test setting name to null and empty string (boundary cases).
     */
    @Test
    void testAirline_SetName_NullAndEmpty() {
        Airline airline = new Airline();
        airline.setName(null);
        assertNull(airline.getName());
        airline.setName("");
        assertEquals("", airline.getName());
    }
}
