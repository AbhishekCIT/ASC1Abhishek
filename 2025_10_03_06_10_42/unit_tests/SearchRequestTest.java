package com.airtransport.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SearchRequest DTO.
 * Covers normal, edge, and boundary cases for getters, setters, and toString.
 */
class SearchRequestTest {
    private SearchRequest searchRequest;

    @BeforeEach
    void setUp() {
        searchRequest = new SearchRequest();
    }

    /**
     * Test setting and getting all fields with normal values.
     */
    @Test
    @DisplayName("SearchRequest: should set and get all fields correctly")
    void testSettersAndGetters_Normal() {
        String origin = "JFK";
        String destination = "LAX";
        String date = "2025-12-01";
        Map<String, Object> preferences = new HashMap<>();
        preferences.put("stops", 0);

        searchRequest.setOrigin(origin);
        searchRequest.setDestination(destination);
        searchRequest.setDate(date);
        searchRequest.setPreferences(preferences);

        assertEquals(origin, searchRequest.getOrigin());
        assertEquals(destination, searchRequest.getDestination());
        assertEquals(date, searchRequest.getDate());
        assertEquals(preferences, searchRequest.getPreferences());
    }

    /**
     * Test setting fields to null (edge case).
     */
    @Test
    @DisplayName("SearchRequest: should handle null values in setters")
    void testSetters_NullValues() {
        searchRequest.setOrigin(null);
        searchRequest.setDestination(null);
        searchRequest.setDate(null);
        searchRequest.setPreferences(null);

        assertNull(searchRequest.getOrigin());
        assertNull(searchRequest.getDestination());
        assertNull(searchRequest.getDate());
        assertNull(searchRequest.getPreferences());
    }

    /**
     * Test empty string values for origin, destination, and date (boundary condition).
     */
    @Test
    @DisplayName("SearchRequest: should handle empty string values")
    void testSetters_EmptyStrings() {
        searchRequest.setOrigin("");
        searchRequest.setDestination("");
        searchRequest.setDate("");

        assertEquals("", searchRequest.getOrigin());
        assertEquals("", searchRequest.getDestination());
        assertEquals("", searchRequest.getDate());
    }

    /**
     * Test empty preferences map (boundary condition).
     */
    @Test
    @DisplayName("SearchRequest: should handle empty preferences map")
    void testSetters_EmptyPreferences() {
        searchRequest.setPreferences(new HashMap<>());
        assertTrue(searchRequest.getPreferences().isEmpty());
    }

    /**
     * Test toString method for non-null and null fields.
     */
    @Test
    @DisplayName("SearchRequest: toString should include all fields")
    void testToString() {
        searchRequest.setOrigin("JFK");
        searchRequest.setDestination("LAX");
        searchRequest.setDate("2025-12-01");
        Map<String, Object> preferences = new HashMap<>();
        preferences.put("stops", 0);
        searchRequest.setPreferences(preferences);
        String str = searchRequest.toString();
        assertTrue(str.contains("JFK"));
        assertTrue(str.contains("LAX"));
        assertTrue(str.contains("2025-12-01"));
        assertTrue(str.contains("stops"));
    }
}
