package com.example.flightsearch.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightResult entity.
 */
public class FlightResultTest {
    private FlightResult flightResult;

    @BeforeEach
    void setUp() {
        flightResult = new FlightResult();
    }

    @Test
    @DisplayName("Should set and get all properties correctly")
    // Tests normal scenario for all getters and setters
    void testGettersAndSetters() {
        SearchCriteria criteria = new SearchCriteria();
        LocalDateTime departure = LocalDateTime.of(2024, 7, 1, 8, 0);
        LocalDateTime arrival = LocalDateTime.of(2024, 7, 1, 11, 0);

        flightResult.setId(1L);
        flightResult.setSearchCriteria(criteria);
        flightResult.setFlightId("DL123");
        flightResult.setAirline("Delta");
        flightResult.setDeparture(departure);
        flightResult.setArrival(arrival);
        flightResult.setPrice(350.0);
        flightResult.setDuration("3h");
        flightResult.setStops(0);

        assertEquals(1L, flightResult.getId());
        assertSame(criteria, flightResult.getSearchCriteria());
        assertEquals("DL123", flightResult.getFlightId());
        assertEquals("Delta", flightResult.getAirline());
        assertEquals(departure, flightResult.getDeparture());
        assertEquals(arrival, flightResult.getArrival());
        assertEquals(350.0, flightResult.getPrice());
        assertEquals("3h", flightResult.getDuration());
        assertEquals(0, flightResult.getStops());
    }

    @Test
    @DisplayName("Should handle null values in setters and getters")
    // Tests edge case where properties are set to null
    void testNullValues() {
        flightResult.setSearchCriteria(null);
        flightResult.setFlightId(null);
        flightResult.setAirline(null);
        flightResult.setDeparture(null);
        flightResult.setArrival(null);
        flightResult.setDuration(null);

        assertNull(flightResult.getSearchCriteria());
        assertNull(flightResult.getFlightId());
        assertNull(flightResult.getAirline());
        assertNull(flightResult.getDeparture());
        assertNull(flightResult.getArrival());
        assertNull(flightResult.getDuration());
    }

    @Test
    @DisplayName("Should handle boundary values for price and stops")
    // Tests boundary conditions for price and stops
    void testBoundaryValues() {
        flightResult.setPrice(0.0);
        flightResult.setStops(0);
        assertEquals(0.0, flightResult.getPrice());
        assertEquals(0, flightResult.getStops());

        flightResult.setPrice(Double.MAX_VALUE);
        flightResult.setStops(Integer.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, flightResult.getPrice());
        assertEquals(Integer.MAX_VALUE, flightResult.getStops());
    }
}
