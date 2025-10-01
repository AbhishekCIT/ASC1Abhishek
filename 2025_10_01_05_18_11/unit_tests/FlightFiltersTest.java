package com.example.flightsearch.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightFilters model.
 */
class FlightFiltersTest {

    private FlightFilters filters;

    @BeforeEach
    void setUp() {
        filters = new FlightFilters();
    }

    /**
     * Test normal scenario: setting and getting all fields.
     */
    @Test
    @DisplayName("Set and get all fields")
    void testSetAndGetFields() {
        String airline = "Delta";
        Double maxPrice = 500.0;
        Integer stops = 1;

        filters.setAirline(airline);
        filters.setMaxPrice(maxPrice);
        filters.setStops(stops);

        assertEquals(airline, filters.getAirline());
        assertEquals(maxPrice, filters.getMaxPrice());
        assertEquals(stops, filters.getStops());
    }

    /**
     * Test edge case: setting fields to null.
     */
    @Test
    @DisplayName("Set fields to null and verify getters")
    void testSetFieldsToNull() {
        filters.setAirline(null);
        filters.setMaxPrice(null);
        filters.setStops(null);

        assertNull(filters.getAirline());
        assertNull(filters.getMaxPrice());
        assertNull(filters.getStops());
    }

    /**
     * Test boundary condition: toString with all fields set.
     */
    @Test
    @DisplayName("toString returns correct JSON string with all fields set")
    void testToString_AllFieldsSet() {
        filters.setAirline("Delta");
        filters.setMaxPrice(500.0);
        filters.setStops(1);
        String expected = "{\"airline\":\"Delta\",\"maxPrice\":500.0,\"stops\":1}";
        assertEquals(expected, filters.toString());
    }

    /**
     * Test boundary condition: toString with null fields.
     */
    @Test
    @DisplayName("toString returns correct JSON string with null fields")
    void testToString_NullFields() {
        filters.setAirline(null);
        filters.setMaxPrice(null);
        filters.setStops(null);
        String expected = "{\"airline\":\"null\",\"maxPrice\":null,\"stops\":null}";
        assertEquals(expected, filters.toString());
    }
}
