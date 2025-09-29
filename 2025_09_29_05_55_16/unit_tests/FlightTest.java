package com.example.airtransport.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Flight model.
 * Covers constructors, getters, setters, and edge cases.
 */
class FlightTest {
    @Test
    void testDefaultConstructor() {
        // Purpose: Test default constructor
        Flight flight = new Flight();
        assertNull(flight.getFlightId());
        assertNull(flight.getAirline());
        assertNull(flight.getDestination());
        assertNull(flight.getDepartureDate());
        assertEquals(0.0, flight.getPrice());
        assertNull(flight.getDuration());
        assertEquals(0, flight.getSeatsAvailable());
    }

    @Test
    void testAllArgsConstructor() {
        // Purpose: Test all-args constructor
        Flight flight = new Flight("F123", "Delta", "JFK", "2025-12-01", 350.0, "3h", 5);
        assertEquals("F123", flight.getFlightId());
        assertEquals("Delta", flight.getAirline());
        assertEquals("JFK", flight.getDestination());
        assertEquals("2025-12-01", flight.getDepartureDate());
        assertEquals(350.0, flight.getPrice());
        assertEquals("3h", flight.getDuration());
        assertEquals(5, flight.getSeatsAvailable());
    }

    @Test
    void testSettersAndGetters() {
        // Purpose: Test all setters and getters, including edge cases
        Flight flight = new Flight();
        flight.setFlightId("F456");
        assertEquals("F456", flight.getFlightId());
        flight.setAirline("United");
        assertEquals("United", flight.getAirline());
        flight.setDestination("LAX");
        assertEquals("LAX", flight.getDestination());
        flight.setDepartureDate("2025-12-02");
        assertEquals("2025-12-02", flight.getDepartureDate());
        flight.setPrice(0.0);
        assertEquals(0.0, flight.getPrice());
        flight.setPrice(-100.0);
        assertEquals(-100.0, flight.getPrice());
        flight.setDuration("5h");
        assertEquals("5h", flight.getDuration());
        flight.setDuration(null);
        assertNull(flight.getDuration());
        flight.setSeatsAvailable(0);
        assertEquals(0, flight.getSeatsAvailable());
        flight.setSeatsAvailable(-1);
        assertEquals(-1, flight.getSeatsAvailable());
    }

    @AfterEach
    void tearDown() {
        // Purpose: Clean up after each test if needed
    }
}
