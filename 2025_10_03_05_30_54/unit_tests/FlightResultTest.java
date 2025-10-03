package com.example.airtransport.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for FlightResult model.
 * Covers all getters, setters, and edge cases.
 */
public class FlightResultTest {
    private FlightResult result;

    @BeforeEach
    void setUp() {
        result = new FlightResult();
    }

    /**
     * Test all getters and setters for normal values.
     */
    @Test
    void testGettersAndSetters_Normal() {
        String flightId = "DL100";
        String airline = "Delta";
        ZonedDateTime dep = ZonedDateTime.parse("2099-12-31T09:00:00Z");
        ZonedDateTime arr = ZonedDateTime.parse("2099-12-31T17:00:00Z");
        double price = 500.0;
        int stops = 1;
        var layovers = Arrays.asList("CDG", "AMS");

        result.setFlightId(flightId);
        result.setAirline(airline);
        result.setDepartureTime(dep);
        result.setArrivalTime(arr);
        result.setPrice(price);
        result.setStops(stops);
        result.setLayovers(layovers);

        assertEquals(flightId, result.getFlightId());
        assertEquals(airline, result.getAirline());
        assertEquals(dep, result.getDepartureTime());
        assertEquals(arr, result.getArrivalTime());
        assertEquals(price, result.getPrice());
        assertEquals(stops, result.getStops());
        assertEquals(layovers, result.getLayovers());
    }

    /**
     * Test edge case: null and empty values.
     */
    @Test
    void testGettersAndSetters_NullAndEmpty() {
        result.setFlightId(null);
        result.setAirline(null);
        result.setDepartureTime(null);
        result.setArrivalTime(null);
        result.setPrice(0.0);
        result.setStops(0);
        result.setLayovers(null);

        assertNull(result.getFlightId());
        assertNull(result.getAirline());
        assertNull(result.getDepartureTime());
        assertNull(result.getArrivalTime());
        assertEquals(0.0, result.getPrice());
        assertEquals(0, result.getStops());
        assertNull(result.getLayovers());
    }
}
