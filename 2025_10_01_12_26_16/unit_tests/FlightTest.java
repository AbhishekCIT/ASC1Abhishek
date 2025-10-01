package com.example.airlinebooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Flight entity.
 * Covers all getters, setters, and edge cases.
 */
class FlightTest {
    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight();
    }

    @Test
    @DisplayName("Set and get flightId")
    void testFlightId() {
        flight.setFlightId("F123");
        assertEquals("F123", flight.getFlightId());
    }

    @Test
    @DisplayName("Set and get departure")
    void testDeparture() {
        flight.setDeparture("LAX");
        assertEquals("LAX", flight.getDeparture());
    }

    @Test
    @DisplayName("Set and get destination")
    void testDestination() {
        flight.setDestination("NYC");
        assertEquals("NYC", flight.getDestination());
    }

    @Test
    @DisplayName("Set and get flightClass")
    void testFlightClass() {
        flight.setFlightClass("Economy");
        assertEquals("Economy", flight.getFlightClass());
        flight.setFlightClass(null);
        assertNull(flight.getFlightClass());
    }

    @Test
    @DisplayName("Set and get seatsAvailable")
    void testSeatsAvailable() {
        flight.setSeatsAvailable(100);
        assertEquals(100, flight.getSeatsAvailable());
        flight.setSeatsAvailable(0);
        assertEquals(0, flight.getSeatsAvailable());
        flight.setSeatsAvailable(-1);
        assertEquals(-1, flight.getSeatsAvailable());
    }

    @Test
    @DisplayName("Edge case: All fields null/default")
    void testAllFieldsDefault() {
        Flight empty = new Flight();
        assertNull(empty.getFlightId());
        assertNull(empty.getDeparture());
        assertNull(empty.getDestination());
        assertNull(empty.getFlightClass());
        assertEquals(0, empty.getSeatsAvailable());
    }
}
