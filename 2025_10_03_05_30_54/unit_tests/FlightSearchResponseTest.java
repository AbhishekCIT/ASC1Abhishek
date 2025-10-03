package com.example.airtransport.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for FlightSearchResponse model.
 * Covers all getters, setters, and edge cases.
 */
public class FlightSearchResponseTest {
    private FlightSearchResponse response;

    @BeforeEach
    void setUp() {
        response = new FlightSearchResponse();
    }

    /**
     * Test all getters and setters for normal values.
     */
    @Test
    void testGettersAndSetters_Normal() {
        List<FlightResult> flights = Collections.singletonList(new FlightResult());
        int totalResults = 1;

        response.setFlights(flights);
        response.setTotalResults(totalResults);

        assertEquals(flights, response.getFlights());
        assertEquals(totalResults, response.getTotalResults());
    }

    /**
     * Test edge case: null and zero values.
     */
    @Test
    void testGettersAndSetters_NullAndZero() {
        response.setFlights(null);
        response.setTotalResults(0);

        assertNull(response.getFlights());
        assertEquals(0, response.getTotalResults());
    }
}
