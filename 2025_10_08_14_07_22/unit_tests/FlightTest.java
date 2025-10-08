package com.example.airbooking.entity;

import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Flight entity.
 * Covers normal, edge, and boundary scenarios for all fields and methods.
 */
class FlightTest {
    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight();
    }

    /**
     * Test setting and getting flightId (normal scenario).
     */
    @Test
    void testFlightId_setAndGet() {
        flight.setFlightId(101L);
        assertEquals(101L, flight.getFlightId());
    }

    /**
     * Test setting and getting airline (normal scenario).
     */
    @Test
    void testAirline_setAndGet() {
        flight.setAirline("Delta");
        assertEquals("Delta", flight.getAirline());
    }

    /**
     * Test setting and getting origin (normal scenario).
     */
    @Test
    void testOrigin_setAndGet() {
        flight.setOrigin("JFK");
        assertEquals("JFK", flight.getOrigin());
    }

    /**
     * Test setting and getting destination (normal scenario).
     */
    @Test
    void testDestination_setAndGet() {
        flight.setDestination("LAX");
        assertEquals("LAX", flight.getDestination());
    }

    /**
     * Test setting and getting date (normal scenario).
     */
    @Test
    void testDate_setAndGet() {
        LocalDate date = LocalDate.of(2025, 10, 15);
        flight.setDate(date);
        assertEquals(date, flight.getDate());
    }

    /**
     * Test setting and getting time (normal scenario).
     */
    @Test
    void testTime_setAndGet() {
        LocalTime time = LocalTime.of(10, 0);
        flight.setTime(time);
        assertEquals(time, flight.getTime());
    }

    /**
     * Test setting and getting price (normal scenario).
     */
    @Test
    void testPrice_setAndGet() {
        flight.setPrice(320.00);
        assertEquals(320.00, flight.getPrice());
    }

    /**
     * Test setting null values (edge case).
     */
    @Test
    void testSetNullValues() {
        flight.setAirline(null);
        flight.setOrigin(null);
        flight.setDestination(null);
        flight.setDate(null);
        flight.setTime(null);
        assertNull(flight.getAirline());
        assertNull(flight.getOrigin());
        assertNull(flight.getDestination());
        assertNull(flight.getDate());
        assertNull(flight.getTime());
    }

    /**
     * Test boundary condition for price (edge case).
     */
    @Test
    void testPrice_boundaryValues() {
        flight.setPrice(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, flight.getPrice());
        flight.setPrice(Double.MIN_VALUE);
        assertEquals(Double.MIN_VALUE, flight.getPrice());
        flight.setPrice(Double.NEGATIVE_INFINITY);
        assertEquals(Double.NEGATIVE_INFINITY, flight.getPrice());
    }
}
