package com.example.flightsearch.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightSearchRequest.
 */
class FlightSearchRequestTest {

    /**
     * Test all getters and setters for FlightSearchRequest.
     */
    @Test
    @DisplayName("Test getters and setters")
    void testGettersAndSetters() {
        FlightSearchRequest req = new FlightSearchRequest();
        req.setOrigin("JFK");
        req.setDestination("LAX");
        LocalDate date = LocalDate.of(2024, 10, 1);
        req.setDate(date);
        req.setPassengers(3);
        Map<String, String> filters = new HashMap<>();
        filters.put("airline", "Delta");
        req.setFilters(filters);
        req.setPage(2);
        req.setSize(50);

        assertEquals("JFK", req.getOrigin());
        assertEquals("LAX", req.getDestination());
        assertEquals(date, req.getDate());
        assertEquals(3, req.getPassengers());
        assertEquals(filters, req.getFilters());
        assertEquals(2, req.getPage());
        assertEquals(50, req.getSize());
    }

    /**
     * Test edge cases for null and boundary values.
     */
    @Test
    @DisplayName("Test edge and boundary cases")
    void testEdgeCases() {
        FlightSearchRequest req = new FlightSearchRequest();
        req.setOrigin(null);
        req.setDestination("");
        req.setDate(null);
        req.setPassengers(0);
        req.setFilters(null);
        req.setPage(0);
        req.setSize(-1);

        assertNull(req.getOrigin());
        assertEquals("", req.getDestination());
        assertNull(req.getDate());
        assertEquals(0, req.getPassengers());
        assertNull(req.getFilters());
        assertEquals(0, req.getPage());
        assertEquals(-1, req.getSize());
    }
}
