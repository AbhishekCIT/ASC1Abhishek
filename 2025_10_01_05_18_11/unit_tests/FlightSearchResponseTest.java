package com.example.flightsearch.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightSearchResponse model.
 */
class FlightSearchResponseTest {

    private FlightSearchResponse response;

    @BeforeEach
    void setUp() {
        response = new FlightSearchResponse();
    }

    /**
     * Test normal scenario: set and get flights list.
     */
    @Test
    @DisplayName("Set and get flights list")
    void testSetAndGetFlights() {
        Flight f1 = new Flight();
        Flight f2 = new Flight();
        List<Flight> flights = Arrays.asList(f1, f2);
        response.setFlights(flights);
        assertEquals(flights, response.getFlights());
    }

    /**
     * Test edge case: set flights to null.
     */
    @Test
    @DisplayName("Set flights to null")
    void testSetFlightsToNull() {
        response.setFlights(null);
        assertNull(response.getFlights());
    }

    /**
     * Test boundary condition: set flights to empty list.
     */
    @Test
    @DisplayName("Set flights to empty list")
    void testSetFlightsToEmptyList() {
        response.setFlights(Collections.emptyList());
        assertNotNull(response.getFlights());
        assertTrue(response.getFlights().isEmpty());
    }
}
