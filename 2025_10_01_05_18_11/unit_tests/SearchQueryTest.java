package com.example.flightsearch.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SearchQuery entity.
 */
class SearchQueryTest {

    private SearchQuery query;

    @BeforeEach
    void setUp() {
        query = new SearchQuery();
    }

    /**
     * Test normal scenario: setting and getting all fields.
     */
    @Test
    @DisplayName("Set and get all fields")
    void testSetAndGetFields() {
        Long id = 1L;
        Long userId = 42L;
        String origin = "JFK";
        String destination = "LAX";
        String departureDate = "2025-12-01";
        String returnDate = "2025-12-10";
        String filters = "{\"airline\":\"Delta\"}";
        LocalDateTime searchTime = LocalDateTime.now();

        query.setId(id);
        query.setUserId(userId);
        query.setOrigin(origin);
        query.setDestination(destination);
        query.setDepartureDate(departureDate);
        query.setReturnDate(returnDate);
        query.setFilters(filters);
        query.setSearchTime(searchTime);

        assertEquals(id, query.getId());
        assertEquals(userId, query.getUserId());
        assertEquals(origin, query.getOrigin());
        assertEquals(destination, query.getDestination());
        assertEquals(departureDate, query.getDepartureDate());
        assertEquals(returnDate, query.getReturnDate());
        assertEquals(filters, query.getFilters());
        assertEquals(searchTime, query.getSearchTime());
    }

    /**
     * Test edge case: setting fields to null.
     */
    @Test
    @DisplayName("Set fields to null and verify getters")
    void testSetFieldsToNull() {
        query.setId(null);
        query.setUserId(null);
        query.setOrigin(null);
        query.setDestination(null);
        query.setDepartureDate(null);
        query.setReturnDate(null);
        query.setFilters(null);
        query.setSearchTime(null);

        assertNull(query.getId());
        assertNull(query.getUserId());
        assertNull(query.getOrigin());
        assertNull(query.getDestination());
        assertNull(query.getDepartureDate());
        assertNull(query.getReturnDate());
        assertNull(query.getFilters());
        assertNull(query.getSearchTime());
    }

    /**
     * Test boundary condition: empty strings for string fields.
     */
    @Test
    @DisplayName("Set empty strings for string fields")
    void testSetEmptyStrings() {
        query.setOrigin("");
        query.setDestination("");
        query.setDepartureDate("");
        query.setReturnDate("");
        query.setFilters("");

        assertEquals("", query.getOrigin());
        assertEquals("", query.getDestination());
        assertEquals("", query.getDepartureDate());
        assertEquals("", query.getReturnDate());
        assertEquals("", query.getFilters());
    }
}
