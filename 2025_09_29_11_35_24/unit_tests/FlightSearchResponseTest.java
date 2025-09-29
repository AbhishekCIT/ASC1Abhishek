package com.airtransport.model;

import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    void tearDown() {
        response = null;
    }

    @Test
    @DisplayName("Test default constructor and setters/getters")
    void testDefaultConstructorAndSettersGetters() {
        List<Flight> flights = Arrays.asList(
                new Flight("Delta", "DL123", "10:00", "13:00", "3h", 450),
                new Flight("United", "UA456", "11:00", "14:30", "3h 30m", 470)
        );
        response.setFlights(flights);
        response.setError("No error");
        assertEquals(flights, response.getFlights());
        assertEquals("No error", response.getError());
    }

    @Test
    @DisplayName("Test constructor with flights")
    void testConstructorWithFlights() {
        List<Flight> flights = Collections.singletonList(new Flight("Delta", "DL123", "10:00", "13:00", "3h", 450));
        FlightSearchResponse resp = new FlightSearchResponse(flights);
        assertEquals(flights, resp.getFlights());
        assertNull(resp.getError());
    }

    @Test
    @DisplayName("Test constructor with error")
    void testConstructorWithError() {
        FlightSearchResponse resp = new FlightSearchResponse("Some error");
        assertEquals("Some error", resp.getError());
        assertNull(resp.getFlights());
    }

    @Test
    @DisplayName("Test setters and getters with edge cases (nulls and empty lists)")
    void testSettersGetters_EdgeCases() {
        response.setFlights(null);
        response.setError(null);
        assertNull(response.getFlights());
        assertNull(response.getError());
        response.setFlights(Collections.emptyList());
        assertTrue(response.getFlights().isEmpty());
    }
}
