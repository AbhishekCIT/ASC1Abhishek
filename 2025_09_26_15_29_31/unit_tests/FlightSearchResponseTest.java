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
public class FlightSearchResponseTest {
    private FlightSearchResponse response;

    @BeforeEach
    void setUp() {
        response = new FlightSearchResponse();
    }

    @Test
    @DisplayName("Should set and get flights list correctly")
    // Tests normal scenario for flights list
    void testGettersAndSetters() {
        List<Flight> flights = Arrays.asList(
                new Flight("DL123", "Delta", "2024-07-01T08:00:00", "2024-07-01T11:00:00", 350.0, "3h", 0),
                new Flight("UA456", "United", "2024-07-01T09:00:00", "2024-07-01T12:00:00", 400.0, "3h", 0)
        );
        response.setFlights(flights);
        assertEquals(flights, response.getFlights());
    }

    @Test
    @DisplayName("Should handle null flights list")
    // Tests edge case where flights list is null
    void testNullFlightsList() {
        response.setFlights(null);
        assertNull(response.getFlights());
    }

    @Test
    @DisplayName("Should handle empty flights list")
    // Tests edge case where flights list is empty
    void testEmptyFlightsList() {
        response.setFlights(Collections.emptyList());
        assertNotNull(response.getFlights());
        assertTrue(response.getFlights().isEmpty());
    }
}
