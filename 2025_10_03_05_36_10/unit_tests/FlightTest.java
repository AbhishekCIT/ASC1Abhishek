package com.airtransport.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Flight model.
 * Covers all getters and setters, including edge and boundary cases.
 */
public class FlightTest {
    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight();
    }

    /**
     * Test setting and getting flightId.
     */
    @Test
    void testFlightId() {
        flight.setFlightId(100);
        assertEquals(100, flight.getFlightId());
        flight.setFlightId(null);
        assertNull(flight.getFlightId());
    }

    /**
     * Test setting and getting airline.
     */
    @Test
    void testAirline() {
        flight.setAirline("Delta");
        assertEquals("Delta", flight.getAirline());
        flight.setAirline("");
        assertEquals("", flight.getAirline());
        flight.setAirline(null);
        assertNull(flight.getAirline());
    }

    /**
     * Test setting and getting origin.
     */
    @Test
    void testOrigin() {
        flight.setOrigin("JFK");
        assertEquals("JFK", flight.getOrigin());
        flight.setOrigin(null);
        assertNull(flight.getOrigin());
    }

    /**
     * Test setting and getting destination.
     */
    @Test
    void testDestination() {
        flight.setDestination("LAX");
        assertEquals("LAX", flight.getDestination());
        flight.setDestination(null);
        assertNull(flight.getDestination());
    }

    /**
     * Test setting and getting departure.
     */
    @Test
    void testDeparture() {
        LocalDateTime departure = LocalDateTime.of(2025, 1, 1, 10, 0);
        flight.setDeparture(departure);
        assertEquals(departure, flight.getDeparture());
        flight.setDeparture(null);
        assertNull(flight.getDeparture());
    }

    /**
     * Test setting and getting price.
     * Purpose: Normal, zero, negative, and null cases.
     */
    @Test
    void testPrice() {
        flight.setPrice(199.99);
        assertEquals(199.99, flight.getPrice());
        flight.setPrice(0.0);
        assertEquals(0.0, flight.getPrice());
        flight.setPrice(-50.0);
        assertEquals(-50.0, flight.getPrice());
        flight.setPrice(null);
        assertNull(flight.getPrice());
    }
}
