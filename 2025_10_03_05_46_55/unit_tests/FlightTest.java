package com.asc.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Flight entity.
 * Covers constructors, getters/setters, equals/hashCode, and edge cases.
 */
class FlightTest {

    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight("FL123", "Delta", "JFK", "LAX", LocalDateTime.of(2025, 11, 1, 10, 0), 320.00);
    }

    /**
     * Purpose: Test all-args constructor and getters.
     */
    @Test
    void testAllArgsConstructorAndGetters() {
        assertEquals("FL123", flight.getFlightId());
        assertEquals("Delta", flight.getAirline());
        assertEquals("JFK", flight.getOrigin());
        assertEquals("LAX", flight.getDestination());
        assertEquals(LocalDateTime.of(2025, 11, 1, 10, 0), flight.getDepartureTime());
        assertEquals(320.00, flight.getPrice());
    }

    /**
     * Purpose: Test no-args constructor and setters.
     */
    @Test
    void testNoArgsConstructorAndSetters() {
        Flight f = new Flight();
        f.setFlightId("FL456");
        f.setAirline("United");
        f.setOrigin("SFO");
        f.setDestination("ORD");
        f.setDepartureTime(LocalDateTime.of(2025, 12, 1, 15, 30));
        f.setPrice(150.50);
        assertEquals("FL456", f.getFlightId());
        assertEquals("United", f.getAirline());
        assertEquals("SFO", f.getOrigin());
        assertEquals("ORD", f.getDestination());
        assertEquals(LocalDateTime.of(2025, 12, 1, 15, 30), f.getDepartureTime());
        assertEquals(150.50, f.getPrice());
    }

    /**
     * Purpose: Test equals and hashCode for identical objects.
     */
    @Test
    void testEqualsAndHashCode() {
        Flight f2 = new Flight("FL123", "Delta", "JFK", "LAX", flight.getDepartureTime(), 320.00);
        assertEquals(flight, f2);
        assertEquals(flight.hashCode(), f2.hashCode());
    }

    /**
     * Purpose: Test equals for different objects.
     */
    @Test
    void testEqualsDifferentObjects() {
        Flight f2 = new Flight("FL999", "United", "SFO", "ORD", LocalDateTime.now(), 100.00);
        assertNotEquals(flight, f2);
    }

    /**
     * Purpose: Test edge case with null values.
     */
    @Test
    void testNullValues() {
        Flight f = new Flight(null, null, null, null, null, 0.0);
        assertNull(f.getFlightId());
        assertNull(f.getAirline());
        assertNull(f.getOrigin());
        assertNull(f.getDestination());
        assertNull(f.getDepartureTime());
        assertEquals(0.0, f.getPrice());
    }

    /**
     * Purpose: Test boundary condition for price (zero and negative).
     */
    @Test
    void testPriceBoundaryConditions() {
        Flight fZero = new Flight("FL000", "Test", "AAA", "BBB", LocalDateTime.now(), 0.0);
        assertEquals(0.0, fZero.getPrice());
        Flight fNegative = new Flight("FLNEG", "Test", "AAA", "BBB", LocalDateTime.now(), -100.0);
        assertEquals(-100.0, fNegative.getPrice());
    }
}
