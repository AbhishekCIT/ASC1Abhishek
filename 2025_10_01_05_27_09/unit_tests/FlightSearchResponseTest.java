package com.example.flightsearch.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightSearchResponse.
 */
class FlightSearchResponseTest {

    /**
     * Test default constructor and all setters/getters.
     */
    @Test
    @DisplayName("Test default constructor, setters, and getters")
    void testDefaultConstructorAndSettersGetters() {
        FlightSearchResponse resp = new FlightSearchResponse();
        List<FlightDTO> flights = Arrays.asList(new FlightDTO(), new FlightDTO());
        resp.setFlights(flights);
        resp.setTotalPages(5);
        resp.setCurrentPage(2);
        resp.setError("none");

        assertEquals(flights, resp.getFlights());
        assertEquals(5, resp.getTotalPages());
        assertEquals(2, resp.getCurrentPage());
        assertEquals("none", resp.getError());
    }

    /**
     * Test error constructor.
     */
    @Test
    @DisplayName("Test error constructor")
    void testErrorConstructor() {
        FlightSearchResponse resp = new FlightSearchResponse("error message");
        assertEquals("error message", resp.getError());
        assertNull(resp.getFlights());
    }

    /**
     * Test edge cases for null and boundary values.
     */
    @Test
    @DisplayName("Test edge cases for null and boundary values")
    void testEdgeCases() {
        FlightSearchResponse resp = new FlightSearchResponse();
        resp.setFlights(null);
        resp.setError(null);
        resp.setTotalPages(0);
        resp.setCurrentPage(-1);

        assertNull(resp.getFlights());
        assertNull(resp.getError());
        assertEquals(0, resp.getTotalPages());
        assertEquals(-1, resp.getCurrentPage());
    }
}
