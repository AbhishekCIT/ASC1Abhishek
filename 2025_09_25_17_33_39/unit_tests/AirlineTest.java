package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Airline entity.
 */
class AirlineTest {
    private Airline airline;

    @BeforeEach
    void setUp() {
        airline = new Airline();
    }

    @Test
    @DisplayName("Should set and get id correctly")
    void testIdGetterSetter() {
        airline.setId(100L);
        assertEquals(100L, airline.getId());
    }

    @Test
    @DisplayName("Should set and get name correctly")
    void testNameGetterSetter() {
        airline.setName("Delta");
        assertEquals("Delta", airline.getName());
    }

    @Test
    @DisplayName("Should set and get apiEndpoint correctly")
    void testApiEndpointGetterSetter() {
        airline.setApiEndpoint("https://api.delta.com");
        assertEquals("https://api.delta.com", airline.getApiEndpoint());
    }

    @Test
    @DisplayName("Should handle null values for fields")
    void testNullFields() {
        airline.setName(null);
        airline.setApiEndpoint(null);
        assertNull(airline.getName());
        assertNull(airline.getApiEndpoint());
    }
}
