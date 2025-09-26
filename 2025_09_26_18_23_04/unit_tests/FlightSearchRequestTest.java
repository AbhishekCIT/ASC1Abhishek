package com.example.flightsearch.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightSearchRequest POJO (getters/setters, toString, edge cases).
 */
class FlightSearchRequestTest {
    private FlightSearchRequest request;

    @BeforeEach
    void setUp() {
        request = new FlightSearchRequest();
    }

    /**
     * Test all getters and setters for normal values.
     */
    @Test
    void testGettersAndSetters_NormalValues() {
        request.setOrigin("JFK");
        request.setDestination("LAX");
        LocalDate date = LocalDate.now().plusDays(1);
        request.setDate(date);
        request.setPassengers(2);
        request.setFlightClass("Economy");

        assertEquals("JFK", request.getOrigin());
        assertEquals("LAX", request.getDestination());
        assertEquals(date, request.getDate());
        assertEquals(2, request.getPassengers());
        assertEquals("Economy", request.getFlightClass());
    }

    /**
     * Test edge case: negative and zero passengers.
     */
    @Test
    void testSetters_NegativeAndZeroPassengers() {
        request.setPassengers(-1);
        assertEquals(-1, request.getPassengers());
        request.setPassengers(0);
        assertEquals(0, request.getPassengers());
    }

    /**
     * Test null values for String fields and date.
     */
    @Test
    void testSetters_NullValues() {
        request.setOrigin(null);
        request.setDestination(null);
        request.setDate(null);
        request.setFlightClass(null);
        assertNull(request.getOrigin());
        assertNull(request.getDestination());
        assertNull(request.getDate());
        assertNull(request.getFlightClass());
    }

    /**
     * Test toString method for non-null and null fields.
     */
    @Test
    void testToString() {
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.of(2025, 1, 1));
        request.setPassengers(3);
        request.setFlightClass("Business");
        String str = request.toString();
        assertTrue(str.contains("JFK"));
        assertTrue(str.contains("LAX"));
        assertTrue(str.contains("2025-01-01"));
        assertTrue(str.contains("3"));
        assertTrue(str.contains("Business"));

        // Null fields
        FlightSearchRequest req2 = new FlightSearchRequest();
        String str2 = req2.toString();
        assertTrue(str2.contains("origin='null'"));
        assertTrue(str2.contains("destination='null'"));
    }
}
