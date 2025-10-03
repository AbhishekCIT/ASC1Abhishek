package com.example.airtransport.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for FlightSearchRequest model.
 * Covers all getters, setters, and edge cases.
 */
public class FlightSearchRequestTest {
    private FlightSearchRequest request;

    @BeforeEach
    void setUp() {
        request = new FlightSearchRequest();
    }

    /**
     * Test all getters and setters for normal values.
     */
    @Test
    void testGettersAndSetters_Normal() {
        String userId = "user1";
        String origin = "JFK";
        String destination = "LHR";
        String departureDate = "2099-12-31";
        String returnDate = "2100-01-10";
        Map<String, Object> filters = new HashMap<>();
        filters.put("airline", "Delta");
        String sortBy = "price";

        request.setUserId(userId);
        request.setOrigin(origin);
        request.setDestination(destination);
        request.setDepartureDate(departureDate);
        request.setReturnDate(returnDate);
        request.setFilters(filters);
        request.setSortBy(sortBy);

        assertEquals(userId, request.getUserId());
        assertEquals(origin, request.getOrigin());
        assertEquals(destination, request.getDestination());
        assertEquals(departureDate, request.getDepartureDate());
        assertEquals(returnDate, request.getReturnDate());
        assertEquals(filters, request.getFilters());
        assertEquals(sortBy, request.getSortBy());
    }

    /**
     * Test edge case: null and empty values.
     */
    @Test
    void testGettersAndSetters_NullAndEmpty() {
        request.setUserId(null);
        request.setOrigin(null);
        request.setDestination(null);
        request.setDepartureDate(null);
        request.setReturnDate(null);
        request.setFilters(null);
        request.setSortBy(null);

        assertNull(request.getUserId());
        assertNull(request.getOrigin());
        assertNull(request.getDestination());
        assertNull(request.getDepartureDate());
        assertNull(request.getReturnDate());
        assertNull(request.getFilters());
        assertNull(request.getSortBy());
    }
}
