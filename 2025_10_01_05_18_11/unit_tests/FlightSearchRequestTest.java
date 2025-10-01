package com.example.flightsearch.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightSearchRequest model.
 */
class FlightSearchRequestTest {

    private FlightSearchRequest request;

    @BeforeEach
    void setUp() {
        request = new FlightSearchRequest();
    }

    /**
     * Test normal scenario: setting and getting all fields.
     */
    @Test
    @DisplayName("Set and get all fields")
    void testSetAndGetFields() {
        String origin = "JFK";
        String destination = "LAX";
        String departureDate = "2025-12-01";
        String returnDate = "2025-12-10";
        FlightFilters filters = new FlightFilters();
        String sortBy = "price";
        Long userId = 42L;

        request.setOrigin(origin);
        request.setDestination(destination);
        request.setDepartureDate(departureDate);
        request.setReturnDate(returnDate);
        request.setFilters(filters);
        request.setSortBy(sortBy);
        request.setUserId(userId);

        assertEquals(origin, request.getOrigin());
        assertEquals(destination, request.getDestination());
        assertEquals(departureDate, request.getDepartureDate());
        assertEquals(returnDate, request.getReturnDate());
        assertEquals(filters, request.getFilters());
        assertEquals(sortBy, request.getSortBy());
        assertEquals(userId, request.getUserId());
    }

    /**
     * Test edge case: setting fields to null.
     */
    @Test
    @DisplayName("Set fields to null and verify getters")
    void testSetFieldsToNull() {
        request.setOrigin(null);
        request.setDestination(null);
        request.setDepartureDate(null);
        request.setReturnDate(null);
        request.setFilters(null);
        request.setSortBy(null);
        request.setUserId(null);

        assertNull(request.getOrigin());
        assertNull(request.getDestination());
        assertNull(request.getDepartureDate());
        assertNull(request.getReturnDate());
        assertNull(request.getFilters());
        assertNull(request.getSortBy());
        assertNull(request.getUserId());
    }

    /**
     * Test boundary condition: empty strings for string fields.
     */
    @Test
    @DisplayName("Set empty strings for string fields")
    void testSetEmptyStrings() {
        request.setOrigin("");
        request.setDestination("");
        request.setDepartureDate("");
        request.setReturnDate("");
        request.setSortBy("");

        assertEquals("", request.getOrigin());
        assertEquals("", request.getDestination());
        assertEquals("", request.getDepartureDate());
        assertEquals("", request.getReturnDate());
        assertEquals("", request.getSortBy());
    }
}
