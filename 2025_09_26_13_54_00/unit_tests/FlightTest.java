package com.airtransport.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Flight entity.
 */
public class FlightTest {
    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight();
    }

    /**
     * Test setting and getting all fields.
     */
    @Test
    void testSettersAndGetters() {
        Long id = 10L;
        String airline = "Delta";
        String origin = "JFK";
        String destination = "LAX";
        LocalDateTime departureTime = LocalDateTime.of(2025, 10, 1, 10, 0);
        Double price = 350.0;

        flight.setId(id);
        flight.setAirline(airline);
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setDepartureTime(departureTime);
        flight.setPrice(price);

        assertEquals(id, flight.getId());
        assertEquals(airline, flight.getAirline());
        assertEquals(origin, flight.getOrigin());
        assertEquals(destination, flight.getDestination());
        assertEquals(departureTime, flight.getDepartureTime());
        assertEquals(price, flight.getPrice());
    }

    /**
     * Test setting null values for optional fields.
     */
    @Test
    void testSettersWithNulls() {
        flight.setAirline(null);
        flight.setOrigin(null);
        flight.setDestination(null);
        flight.setDepartureTime(null);
        flight.setPrice(null);
        assertNull(flight.getAirline());
        assertNull(flight.getOrigin());
        assertNull(flight.getDestination());
        assertNull(flight.getDepartureTime());
        assertNull(flight.getPrice());
    }

    /**
     * Test default values after construction.
     */
    @Test
    void testDefaultValues() {
        assertNull(flight.getId());
        assertNull(flight.getAirline());
        assertNull(flight.getOrigin());
        assertNull(flight.getDestination());
        assertNull(flight.getDepartureTime());
        assertNull(flight.getPrice());
    }
}
