package com.asc.airbooking.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for FlightSearchRequest.
 * Covers setters, getters, and edge cases.
 */
class FlightSearchRequestTest {

    /**
     * Purpose: Test setting and getting all fields.
     */
    @Test
    void testSettersAndGetters() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.of(2025, 11, 1));
        assertEquals("JFK", request.getOrigin());
        assertEquals("LAX", request.getDestination());
        assertEquals(LocalDate.of(2025, 11, 1), request.getDate());
    }

    /**
     * Purpose: Test edge case with null values.
     */
    @Test
    void testNullValues() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin(null);
        request.setDestination(null);
        request.setDate(null);
        assertNull(request.getOrigin());
        assertNull(request.getDestination());
        assertNull(request.getDate());
    }

    /**
     * Purpose: Test edge case with blank values for origin and destination.
     */
    @Test
    void testBlankValues() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("");
        request.setDestination("");
        assertEquals("", request.getOrigin());
        assertEquals("", request.getDestination());
    }

    /**
     * Purpose: Test boundary condition for date (today and past date).
     */
    @Test
    void testDateBoundaryConditions() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setDate(LocalDate.now());
        assertEquals(LocalDate.now(), request.getDate());
        request.setDate(LocalDate.of(2000, 1, 1));
        assertEquals(LocalDate.of(2000, 1, 1), request.getDate());
    }
}
