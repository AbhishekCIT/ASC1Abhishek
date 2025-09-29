package com.airtransport.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SearchQuery entity.
 */
public class SearchQueryTest {
    private SearchQuery searchQuery;

    @BeforeEach
    void setUp() {
        searchQuery = new SearchQuery();
    }

    @AfterEach
    void tearDown() {
        searchQuery = null;
    }

    @Test
    @DisplayName("Test all getters and setters for normal values")
    void testGettersAndSetters_NormalValues() {
        LocalDate travelDate = LocalDate.of(2025, 12, 1);
        LocalDateTime timestamp = LocalDateTime.now();
        searchQuery.setId("id123");
        searchQuery.setUserId("user456");
        searchQuery.setSource("JFK");
        searchQuery.setDestination("LAX");
        searchQuery.setTravelDate(travelDate);
        searchQuery.setPassengerCount(2);
        searchQuery.setTimestamp(timestamp);

        assertEquals("id123", searchQuery.getId());
        assertEquals("user456", searchQuery.getUserId());
        assertEquals("JFK", searchQuery.getSource());
        assertEquals("LAX", searchQuery.getDestination());
        assertEquals(travelDate, searchQuery.getTravelDate());
        assertEquals(2, searchQuery.getPassengerCount());
        assertEquals(timestamp, searchQuery.getTimestamp());
    }

    @Test
    @DisplayName("Test setters and getters with edge cases (nulls and boundary values)")
    void testGettersAndSetters_EdgeCases() {
        searchQuery.setId(null);
        searchQuery.setUserId("");
        searchQuery.setSource(null);
        searchQuery.setDestination("");
        searchQuery.setTravelDate(null);
        searchQuery.setPassengerCount(0);
        searchQuery.setTimestamp(null);

        assertNull(searchQuery.getId());
        assertEquals("", searchQuery.getUserId());
        assertNull(searchQuery.getSource());
        assertEquals("", searchQuery.getDestination());
        assertNull(searchQuery.getTravelDate());
        assertEquals(0, searchQuery.getPassengerCount());
        assertNull(searchQuery.getTimestamp());
    }

    @Test
    @DisplayName("Test passengerCount setter with negative value (boundary condition)")
    void testSetPassengerCount_NegativeValue() {
        searchQuery.setPassengerCount(-5);
        assertEquals(-5, searchQuery.getPassengerCount(), "Passenger count should accept negative values (no validation in entity)");
    }
}
