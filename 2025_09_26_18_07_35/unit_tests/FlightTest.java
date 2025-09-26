package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
     * Test all getters and setters for normal values.
     */
    @Test
    @DisplayName("Test getters and setters with normal values")
    void testGettersAndSettersNormal() {
        String flightId = "UA123";
        String origin = "JFK";
        String destination = "LAX";
        LocalDateTime dep = LocalDateTime.now();
        LocalDateTime arr = dep.plusHours(6);
        String airline = "United";
        double price = 350.0;

        flight.setFlightId(flightId);
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setDepartureDate(dep);
        flight.setArrivalDate(arr);
        flight.setAirline(airline);
        flight.setPrice(price);

        assertEquals(flightId, flight.getFlightId());
        assertEquals(origin, flight.getOrigin());
        assertEquals(destination, flight.getDestination());
        assertEquals(dep, flight.getDepartureDate());
        assertEquals(arr, flight.getArrivalDate());
        assertEquals(airline, flight.getAirline());
        assertEquals(price, flight.getPrice());
    }

    /**
     * Test setters and getters with null values.
     */
    @Test
    @DisplayName("Test setters and getters with null values")
    void testNullValues() {
        flight.setFlightId(null);
        flight.setOrigin(null);
        flight.setDestination(null);
        flight.setDepartureDate(null);
        flight.setArrivalDate(null);
        flight.setAirline(null);

        assertNull(flight.getFlightId());
        assertNull(flight.getOrigin());
        assertNull(flight.getDestination());
        assertNull(flight.getDepartureDate());
        assertNull(flight.getArrivalDate());
        assertNull(flight.getAirline());
    }

    /**
     * Test setters and getters with empty strings.
     */
    @Test
    @DisplayName("Test setters and getters with empty strings")
    void testEmptyStrings() {
        flight.setFlightId("");
        flight.setOrigin("");
        flight.setDestination("");
        flight.setAirline("");

        assertEquals("", flight.getFlightId());
        assertEquals("", flight.getOrigin());
        assertEquals("", flight.getDestination());
        assertEquals("", flight.getAirline());
    }

    /**
     * Test price with zero, negative, and large values.
     */
    @Test
    @DisplayName("Test price edge cases")
    void testPriceEdgeCases() {
        flight.setPrice(0.0);
        assertEquals(0.0, flight.getPrice());
        flight.setPrice(-100.0);
        assertEquals(-100.0, flight.getPrice());
        flight.setPrice(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, flight.getPrice());
    }

    /**
     * Test boundary values for departureDate and arrivalDate.
     */
    @Test
    @DisplayName("Test boundary values for dates")
    void testDateBoundaries() {
        LocalDateTime min = LocalDateTime.MIN;
        LocalDateTime max = LocalDateTime.MAX;
        flight.setDepartureDate(min);
        assertEquals(min, flight.getDepartureDate());
        flight.setArrivalDate(max);
        assertEquals(max, flight.getArrivalDate());
    }
}
