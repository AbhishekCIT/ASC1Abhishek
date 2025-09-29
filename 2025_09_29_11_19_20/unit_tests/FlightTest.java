package com.example.airbooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Flight entity.
 */
class FlightTest {
    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight();
    }

    @Test
    @DisplayName("Test setting and getting id")
    void testId() {
        flight.setId(10L);
        assertEquals(10L, flight.getId());
    }

    @Test
    @DisplayName("Test setting and getting origin")
    void testOrigin() {
        flight.setOrigin("JFK");
        assertEquals("JFK", flight.getOrigin());
    }

    @Test
    @DisplayName("Test setting and getting destination")
    void testDestination() {
        flight.setDestination("LAX");
        assertEquals("LAX", flight.getDestination());
    }

    @Test
    @DisplayName("Test setting and getting departureTime")
    void testDepartureTime() {
        LocalDateTime dt = LocalDateTime.now();
        flight.setDepartureTime(dt);
        assertEquals(dt, flight.getDepartureTime());
    }

    @Test
    @DisplayName("Test setting and getting arrivalTime")
    void testArrivalTime() {
        LocalDateTime at = LocalDateTime.now();
        flight.setArrivalTime(at);
        assertEquals(at, flight.getArrivalTime());
    }

    @Test
    @DisplayName("Test setting and getting price")
    void testPrice() {
        flight.setPrice(199.99);
        assertEquals(199.99, flight.getPrice());
    }

    @Test
    @DisplayName("Test setting and getting airline")
    void testAirline() {
        flight.setAirline("Delta");
        assertEquals("Delta", flight.getAirline());
    }

    @Test
    @DisplayName("Test null values for fields")
    void testNullFields() {
        flight.setOrigin(null);
        flight.setDestination(null);
        flight.setDepartureTime(null);
        flight.setArrivalTime(null);
        flight.setPrice(null);
        flight.setAirline(null);
        assertNull(flight.getOrigin());
        assertNull(flight.getDestination());
        assertNull(flight.getDepartureTime());
        assertNull(flight.getArrivalTime());
        assertNull(flight.getPrice());
        assertNull(flight.getAirline());
    }
}
