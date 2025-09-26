package com.example.airtransport.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Flight model.
 */
class FlightTest {

    @Test
    @DisplayName("Test all-args constructor and getters for Flight")
    void testAllArgsConstructorAndGetters() {
        LocalDateTime dt = LocalDateTime.of(2025, 10, 1, 10, 0);
        Flight flight = new Flight("F123", "JFK", "LAX", dt, 350.0, 5);
        assertEquals("F123", flight.getFlightId());
        assertEquals("JFK", flight.getOrigin());
        assertEquals("LAX", flight.getDestination());
        assertEquals(dt, flight.getDepartureTime());
        assertEquals(350.0, flight.getPrice());
        assertEquals(5, flight.getSeatsAvailable());
    }

    @Test
    @DisplayName("Test setters and getters for Flight")
    void testSettersAndGetters() {
        Flight flight = new Flight();
        flight.setFlightId("F456");
        flight.setOrigin("SFO");
        flight.setDestination("ORD");
        LocalDateTime dt = LocalDateTime.of(2025, 12, 25, 15, 30);
        flight.setDepartureTime(dt);
        flight.setPrice(420.0);
        flight.setSeatsAvailable(3);

        assertEquals("F456", flight.getFlightId());
        assertEquals("SFO", flight.getOrigin());
        assertEquals("ORD", flight.getDestination());
        assertEquals(dt, flight.getDepartureTime());
        assertEquals(420.0, flight.getPrice());
        assertEquals(3, flight.getSeatsAvailable());
    }

    @Test
    @DisplayName("Test Flight with null and zero fields")
    void testNullAndZeroFields() {
        Flight flight = new Flight();
        assertNull(flight.getFlightId());
        assertNull(flight.getOrigin());
        assertNull(flight.getDestination());
        assertNull(flight.getDepartureTime());
        assertEquals(0.0, flight.getPrice());
        assertEquals(0, flight.getSeatsAvailable());
    }
}
