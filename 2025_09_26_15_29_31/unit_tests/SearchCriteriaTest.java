package com.example.flightsearch.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SearchCriteria entity.
 */
public class SearchCriteriaTest {
    private SearchCriteria searchCriteria;

    @BeforeEach
    void setUp() {
        searchCriteria = new SearchCriteria();
    }

    @Test
    @DisplayName("Should set and get all properties correctly")
    // Tests normal scenario for all getters and setters
    void testGettersAndSetters() {
        LocalDate departureDate = LocalDate.of(2024, 7, 1);
        LocalDate returnDate = LocalDate.of(2024, 7, 10);
        LocalDateTime timestamp = LocalDateTime.of(2024, 6, 1, 12, 0);

        searchCriteria.setId(1L);
        searchCriteria.setUserId("user123");
        searchCriteria.setOrigin("JFK");
        searchCriteria.setDestination("LAX");
        searchCriteria.setDepartureDate(departureDate);
        searchCriteria.setReturnDate(returnDate);
        searchCriteria.setFilters("{\"airline\":\"Delta\"}");
        searchCriteria.setSearchTimestamp(timestamp);

        assertEquals(1L, searchCriteria.getId());
        assertEquals("user123", searchCriteria.getUserId());
        assertEquals("JFK", searchCriteria.getOrigin());
        assertEquals("LAX", searchCriteria.getDestination());
        assertEquals(departureDate, searchCriteria.getDepartureDate());
        assertEquals(returnDate, searchCriteria.getReturnDate());
        assertEquals("{\"airline\":\"Delta\"}", searchCriteria.getFilters());
        assertEquals(timestamp, searchCriteria.getSearchTimestamp());
    }

    @Test
    @DisplayName("Should handle null values in setters and getters")
    // Tests edge case where properties are set to null
    void testNullValues() {
        searchCriteria.setUserId(null);
        searchCriteria.setOrigin(null);
        searchCriteria.setDestination(null);
        searchCriteria.setDepartureDate(null);
        searchCriteria.setReturnDate(null);
        searchCriteria.setFilters(null);
        searchCriteria.setSearchTimestamp(null);

        assertNull(searchCriteria.getUserId());
        assertNull(searchCriteria.getOrigin());
        assertNull(searchCriteria.getDestination());
        assertNull(searchCriteria.getDepartureDate());
        assertNull(searchCriteria.getReturnDate());
        assertNull(searchCriteria.getFilters());
        assertNull(searchCriteria.getSearchTimestamp());
    }

    @Test
    @DisplayName("Should handle boundary values for id")
    // Tests boundary conditions for id
    void testBoundaryValues() {
        searchCriteria.setId(0L);
        assertEquals(0L, searchCriteria.getId());
        searchCriteria.setId(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, searchCriteria.getId());
    }
}
