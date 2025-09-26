package com.example.flightsearch.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightSearchRequest model.
 */
public class FlightSearchRequestTest {
    private FlightSearchRequest request;

    @BeforeEach
    void setUp() {
        request = new FlightSearchRequest();
    }

    @Test
    @DisplayName("Should set and get all properties correctly")
    // Tests normal scenario for all getters and setters
    void testGettersAndSetters() {
        FlightSearchFilters filters = new FlightSearchFilters();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDepartureDate("2024-07-01");
        request.setReturnDate("2024-07-10");
        request.setFilters(filters);

        assertEquals("JFK", request.getOrigin());
        assertEquals("LAX", request.getDestination());
        assertEquals("2024-07-01", request.getDepartureDate());
        assertEquals("2024-07-10", request.getReturnDate());
        assertSame(filters, request.getFilters());
    }

    @Test
    @DisplayName("Should handle null values in setters and getters")
    // Tests edge case where properties are set to null
    void testNullValues() {
        request.setOrigin(null);
        request.setDestination(null);
        request.setDepartureDate(null);
        request.setReturnDate(null);
        request.setFilters(null);

        assertNull(request.getOrigin());
        assertNull(request.getDestination());
        assertNull(request.getDepartureDate());
        assertNull(request.getReturnDate());
        assertNull(request.getFilters());
    }

    @Test
    @DisplayName("Should handle empty strings for required fields")
    // Tests edge case with empty strings for required fields
    void testEmptyStrings() {
        request.setOrigin("");
        request.setDestination("");
        request.setDepartureDate("");
        request.setReturnDate("");

        assertEquals("", request.getOrigin());
        assertEquals("", request.getDestination());
        assertEquals("", request.getDepartureDate());
        assertEquals("", request.getReturnDate());
    }
}
