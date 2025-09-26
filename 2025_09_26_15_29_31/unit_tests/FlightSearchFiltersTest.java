package com.example.flightsearch.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightSearchFilters model.
 */
public class FlightSearchFiltersTest {
    private FlightSearchFilters filters;

    @BeforeEach
    void setUp() {
        filters = new FlightSearchFilters();
    }

    @Test
    @DisplayName("Should set and get all properties correctly")
    // Tests normal scenario for all getters and setters
    void testGettersAndSetters() {
        List<String> airlines = Arrays.asList("Delta", "United");
        Integer[] priceRange = {100, 500};
        filters.setAirlines(airlines);
        filters.setPriceRange(priceRange);
        filters.setStops(1);

        assertEquals(airlines, filters.getAirlines());
        assertArrayEquals(priceRange, filters.getPriceRange());
        assertEquals(1, filters.getStops());
    }

    @Test
    @DisplayName("Should handle null values in setters and getters")
    // Tests edge case where properties are set to null
    void testNullValues() {
        filters.setAirlines(null);
        filters.setPriceRange(null);
        filters.setStops(null);

        assertNull(filters.getAirlines());
        assertNull(filters.getPriceRange());
        assertNull(filters.getStops());
    }

    @Test
    @DisplayName("Should handle empty airlines list and boundary price range")
    // Tests edge case with empty airlines and boundary price range
    void testEmptyAndBoundaryValues() {
        filters.setAirlines(Collections.emptyList());
        Integer[] priceRange = {0, Integer.MAX_VALUE};
        filters.setPriceRange(priceRange);
        filters.setStops(0);

        assertTrue(filters.getAirlines().isEmpty());
        assertArrayEquals(priceRange, filters.getPriceRange());
        assertEquals(0, filters.getStops());
    }
}
