package com.example.flightsearch.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Flight model.
 */
public class FlightTest {
    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight();
    }

    @Test
    @DisplayName("Should set and get all properties correctly")
    // Tests normal scenario for all getters and setters
    void testGettersAndSetters() {
        flight.setFlightId("DL123");
        flight.setAirline("Delta");
        flight.setDeparture("2024-07-01T08:00:00");
        flight.setArrival("2024-07-01T11:00:00");
        flight.setPrice(350.0);
        flight.setDuration("3h");
        flight.setStops(0);

        assertEquals("DL123", flight.getFlightId());
        assertEquals("Delta", flight.getAirline());
        assertEquals("2024-07-01T08:00:00", flight.getDeparture());
        assertEquals("2024-07-01T11:00:00", flight.getArrival());
        assertEquals(350.0, flight.getPrice());
        assertEquals("3h", flight.getDuration());
        assertEquals(0, flight.getStops());
    }

    @Test
    @DisplayName("Should create Flight using all-args constructor")
    // Tests all-args constructor
    void testAllArgsConstructor() {
        Flight f = new Flight("DL123", "Delta", "2024-07-01T08:00:00", "2024-07-01T11:00:00", 350.0, "3h", 0);
        assertEquals("DL123", f.getFlightId());
        assertEquals("Delta", f.getAirline());
        assertEquals("2024-07-01T08:00:00", f.getDeparture());
        assertEquals("2024-07-01T11:00:00", f.getArrival());
        assertEquals(350.0, f.getPrice());
        assertEquals("3h", f.getDuration());
        assertEquals(0, f.getStops());
    }

    @Test
    @DisplayName("Should handle null values in setters and getters")
    // Tests edge case where properties are set to null
    void testNullValues() {
        flight.setFlightId(null);
        flight.setAirline(null);
        flight.setDeparture(null);
        flight.setArrival(null);
        flight.setDuration(null);
        flight.setStops(null);

        assertNull(flight.getFlightId());
        assertNull(flight.getAirline());
        assertNull(flight.getDeparture());
        assertNull(flight.getArrival());
        assertNull(flight.getDuration());
        assertNull(flight.getStops());
    }

    @Test
    @DisplayName("Should handle boundary values for price and stops")
    // Tests boundary conditions for price and stops
    void testBoundaryValues() {
        flight.setPrice(0.0);
        flight.setStops(Integer.MIN_VALUE);
        assertEquals(0.0, flight.getPrice());
        assertEquals(Integer.MIN_VALUE, flight.getStops());

        flight.setPrice(Double.MAX_VALUE);
        flight.setStops(Integer.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, flight.getPrice());
        assertEquals(Integer.MAX_VALUE, flight.getStops());
    }
}
