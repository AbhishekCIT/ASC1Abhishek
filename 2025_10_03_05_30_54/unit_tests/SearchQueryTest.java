package com.example.airtransport.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for SearchQuery entity.
 * Covers all getters, setters, and edge cases.
 */
public class SearchQueryTest {
    private SearchQuery query;

    @BeforeEach
    void setUp() {
        query = new SearchQuery();
    }

    /**
     * Test all getters and setters for normal values.
     */
    @Test
    void testGettersAndSetters_Normal() {
        Long id = 123L;
        String userId = "user1";
        String origin = "JFK";
        String destination = "LHR";
        String departureDate = "2099-12-31";
        String returnDate = "2100-01-10";
        LocalDateTime timestamp = LocalDateTime.now();

        query.setId(id);
        query.setUserId(userId);
        query.setOrigin(origin);
        query.setDestination(destination);
        query.setDepartureDate(departureDate);
        query.setReturnDate(returnDate);
        query.setTimestamp(timestamp);

        assertEquals(id, query.getId());
        assertEquals(userId, query.getUserId());
        assertEquals(origin, query.getOrigin());
        assertEquals(destination, query.getDestination());
        assertEquals(departureDate, query.getDepartureDate());
        assertEquals(returnDate, query.getReturnDate());
        assertEquals(timestamp, query.getTimestamp());
    }

    /**
     * Test edge case: null values for all fields.
     */
    @Test
    void testGettersAndSetters_NullValues() {
        query.setId(null);
        query.setUserId(null);
        query.setOrigin(null);
        query.setDestination(null);
        query.setDepartureDate(null);
        query.setReturnDate(null);
        query.setTimestamp(null);

        assertNull(query.getId());
        assertNull(query.getUserId());
        assertNull(query.getOrigin());
        assertNull(query.getDestination());
        assertNull(query.getDepartureDate());
        assertNull(query.getReturnDate());
        assertNull(query.getTimestamp());
    }
}
