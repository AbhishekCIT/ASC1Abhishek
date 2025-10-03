package com.example.airbooking.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Flight entity.
 */
public class FlightTest {
    /**
     * Test setting and getting fields.
     */
    @Test
    void testFields() {
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setAirline("AA");
        flight.setOrigin("JFK");
        flight.setDestination("LAX");
        LocalDateTime dep = LocalDateTime.now();
        LocalDateTime arr = dep.plusHours(5);
        flight.setDeparture(dep);
        flight.setArrival(arr);
        flight.setSeatCapacity(200);
        flight.setSeatsAvailable(150);
        flight.setPrice(350.0);

        assertEquals(1L, flight.getId());
        assertEquals("AA", flight.getAirline());
        assertEquals("JFK", flight.getOrigin());
        assertEquals("LAX", flight.getDestination());
        assertEquals(dep, flight.getDeparture());
        assertEquals(arr, flight.getArrival());
        assertEquals(200, flight.getSeatCapacity());
        assertEquals(150, flight.getSeatsAvailable());
        assertEquals(350.0, flight.getPrice());
    }

    /**
     * Test edge case: negative seats available.
     */
    @Test
    void testNegativeSeatsAvailable() {
        Flight flight = new Flight();
        flight.setSeatsAvailable(-1);
        assertEquals(-1, flight.getSeatsAvailable());
    }
}
