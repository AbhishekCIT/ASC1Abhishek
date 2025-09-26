package com.example.flightsearch.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Flight POJO (getters/setters and edge cases).
 */
class FlightTest {
    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight();
    }

    /**
     * Test all getters and setters for normal values.
     */
    @Test
    void testGettersAndSetters_NormalValues() {
        flight.setFlightId(123L);
        flight.setAirline("Delta");
        flight.setOrigin("JFK");
        flight.setDestination("LAX");
        LocalDateTime dep = LocalDateTime.now();
        LocalDateTime arr = dep.plusHours(6);
        flight.setDepartureTime(dep);
        flight.setArrivalTime(arr);
        flight.setPrice(299.99);
        flight.setAvailableSeats(150);
        flight.setFlightClass("Economy");
        flight.setDuration("6h");
        flight.setLayovers(1);

        assertEquals(123L, flight.getFlightId());
        assertEquals("Delta", flight.getAirline());
        assertEquals("JFK", flight.getOrigin());
        assertEquals("LAX", flight.getDestination());
        assertEquals(dep, flight.getDepartureTime());
        assertEquals(arr, flight.getArrivalTime());
        assertEquals(299.99, flight.getPrice());
        assertEquals(150, flight.getAvailableSeats());
        assertEquals("Economy", flight.getFlightClass());
        assertEquals("6h", flight.getDuration());
        assertEquals(1, flight.getLayovers());
    }

    /**
     * Test edge case: negative price and available seats.
     */
    @Test
    void testSetters_NegativeValues() {
        flight.setPrice(-100.0);
        flight.setAvailableSeats(-5);
        assertEquals(-100.0, flight.getPrice());
        assertEquals(-5, flight.getAvailableSeats());
    }

    /**
     * Test boundary case: zero available seats and layovers.
     */
    @Test
    void testSetters_ZeroValues() {
        flight.setAvailableSeats(0);
        flight.setLayovers(0);
        assertEquals(0, flight.getAvailableSeats());
        assertEquals(0, flight.getLayovers());
    }

    /**
     * Test null values for String fields.
     */
    @Test
    void testSetters_NullStrings() {
        flight.setAirline(null);
        flight.setOrigin(null);
        flight.setDestination(null);
        flight.setFlightClass(null);
        flight.setDuration(null);
        assertNull(flight.getAirline());
        assertNull(flight.getOrigin());
        assertNull(flight.getDestination());
        assertNull(flight.getFlightClass());
        assertNull(flight.getDuration());
    }

    /**
     * Test null values for LocalDateTime fields.
     */
    @Test
    void testSetters_NullDateTimes() {
        flight.setDepartureTime(null);
        flight.setArrivalTime(null);
        assertNull(flight.getDepartureTime());
        assertNull(flight.getArrivalTime());
    }
}
