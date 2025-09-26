package com.example.flightbooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
     * Test default constructor and setter/getter.
     */
    @Test
    void testDefaultConstructorAndSetter() {
        List<Flight> flights = Collections.singletonList(new Flight());
        response.setFlights(flights);
        assertEquals(flights, response.getFlights());
    }

    /**
     * Test parameterized constructor.
     */
    @Test
    void testParameterizedConstructor() {
        List<Flight> flights = Collections.singletonList(new Flight());
        FlightSearchResponse resp = new FlightSearchResponse(flights);
        assertEquals(flights, resp.getFlights());
    }

    /**
     * Test setting null and empty list.
     */
    @Test
    void testNullAndEmptyList() {
        response.setFlights(null);
        assertNull(response.getFlights());
        response.setFlights(Collections.emptyList());
        assertTrue(response.getFlights().isEmpty());
    }
}
