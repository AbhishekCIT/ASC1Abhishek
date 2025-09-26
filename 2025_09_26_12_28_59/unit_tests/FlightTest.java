package com.example.flightbooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Flight entity.
 */
class FlightTest {
    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight();
    }

    @Test
    @DisplayName("Should set and get flightId correctly")
    void testFlightId() {
        flight.setFlightId("F123");
        assertEquals("F123", flight.getFlightId(), "FlightId should be set and retrieved correctly");
    }

    @Test
    @DisplayName("Should set and get airline correctly")
    void testAirline() {
        flight.setAirline("AA");
        assertEquals("AA", flight.getAirline(), "Airline should be set and retrieved correctly");
    }

    @Test
    @DisplayName("Should set and get origin correctly")
    void testOrigin() {
        flight.setOrigin("JFK");
        assertEquals("JFK", flight.getOrigin(), "Origin should be set and retrieved correctly");
    }

    @Test
    @DisplayName("Should set and get destination correctly")
    void testDestination() {
        flight.setDestination("LAX");
        assertEquals("LAX", flight.getDestination(), "Destination should be set and retrieved correctly");
    }

    @Test
    @DisplayName("Should set and get departureTime correctly")
    void testDepartureTime() {
        LocalDateTime dt = LocalDateTime.now();
        flight.setDepartureTime(dt);
        assertEquals(dt, flight.getDepartureTime(), "DepartureTime should be set and retrieved correctly");
    }

    @Test
    @DisplayName("Should set and get seatsAvailable correctly")
    void testSeatsAvailable() {
        flight.setSeatsAvailable(10);
        assertEquals(10, flight.getSeatsAvailable(), "SeatsAvailable should be set and retrieved correctly");
    }

    @Test
    @DisplayName("Should set and get price correctly")
    void testPrice() {
        flight.setPrice(299.99);
        assertEquals(299.99, flight.getPrice(), 0.0001, "Price should be set and retrieved correctly");
    }

    @Test
    @DisplayName("Should handle null values for all fields")
    void testNullValues() {
        flight.setFlightId(null);
        flight.setAirline(null);
        flight.setOrigin(null);
        flight.setDestination(null);
        flight.setDepartureTime(null);
        assertNull(flight.getFlightId());
        assertNull(flight.getAirline());
        assertNull(flight.getOrigin());
        assertNull(flight.getDestination());
        assertNull(flight.getDepartureTime());
    }

    @Test
    @DisplayName("Should use parameterized constructor correctly")
    void testParameterizedConstructor() {
        LocalDateTime dt = LocalDateTime.now();
        Flight f = new Flight("F1", "UA", "ORD", "SFO", dt, 3, 450.0);
        assertEquals("F1", f.getFlightId());
        assertEquals("UA", f.getAirline());
        assertEquals("ORD", f.getOrigin());
        assertEquals("SFO", f.getDestination());
        assertEquals(dt, f.getDepartureTime());
        assertEquals(3, f.getSeatsAvailable());
        assertEquals(450.0, f.getPrice(), 0.0001);
    }
}
