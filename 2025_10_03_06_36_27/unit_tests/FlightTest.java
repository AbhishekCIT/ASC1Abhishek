package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Flight entity
 */
public class FlightTest {
    private Flight flight;
    private LocalDateTime departure;
    private LocalDateTime arrival;

    @BeforeEach
    void setUp() {
        flight = new Flight();
        departure = LocalDateTime.of(2023, 10, 1, 10, 0);
        arrival = LocalDateTime.of(2023, 10, 1, 14, 0);
    }

    /**
     * Test setting and getting flightId
     */
    @Test
    void testFlightId() {
        flight.setFlightId(42L);
        assertEquals(42L, flight.getFlightId());
    }

    /**
     * Test setting and getting airline
     */
    @Test
    void testAirline() {
        flight.setAirline("TestAir");
        assertEquals("TestAir", flight.getAirline());
    }

    /**
     * Test setting and getting origin
     */
    @Test
    void testOrigin() {
        flight.setOrigin("NYC");
        assertEquals("NYC", flight.getOrigin());
    }

    /**
     * Test setting and getting destination
     */
    @Test
    void testDestination() {
        flight.setDestination("LAX");
        assertEquals("LAX", flight.getDestination());
    }

    /**
     * Test setting and getting departureTime
     */
    @Test
    void testDepartureTime() {
        flight.setDepartureTime(departure);
        assertEquals(departure, flight.getDepartureTime());
    }

    /**
     * Test setting and getting arrivalTime
     */
    @Test
    void testArrivalTime() {
        flight.setArrivalTime(arrival);
        assertEquals(arrival, flight.getArrivalTime());
    }

    /**
     * Test setting and getting price
     */
    @Test
    void testPrice() {
        flight.setPrice(199.99);
        assertEquals(199.99, flight.getPrice());
    }

    /**
     * Test edge case: negative price
     */
    @Test
    void testNegativePrice() {
        flight.setPrice(-50.0);
        assertEquals(-50.0, flight.getPrice());
    }

    /**
     * Test edge case: null values
     */
    @Test
    void testNullValues() {
        flight.setAirline(null);
        flight.setOrigin(null);
        flight.setDestination(null);
        flight.setDepartureTime(null);
        flight.setArrivalTime(null);
        assertNull(flight.getAirline());
        assertNull(flight.getOrigin());
        assertNull(flight.getDestination());
        assertNull(flight.getDepartureTime());
        assertNull(flight.getArrivalTime());
    }
}
