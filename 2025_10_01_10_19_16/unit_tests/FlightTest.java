package com.airtransport.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Flight entity.
 */
class FlightTest {

    /**
     * Test constructors, getters, and setters for Flight.
     */
    @Test
    void testFlightGettersSetters() {
        Flight flight = new Flight();
        flight.setFlightId("F123");
        flight.setAirline("Delta");
        flight.setOrigin("JFK");
        flight.setDestination("LAX");
        flight.setTime("10:00");
        flight.setArrivalTime("16:00");
        flight.setPrice(350.0);
        flight.setSeatsAvailable(20);

        assertEquals("F123", flight.getFlightId());
        assertEquals("Delta", flight.getAirline());
        assertEquals("JFK", flight.getOrigin());
        assertEquals("LAX", flight.getDestination());
        assertEquals("10:00", flight.getTime());
        assertEquals("16:00", flight.getArrivalTime());
        assertEquals(350.0, flight.getPrice());
        assertEquals(20, flight.getSeatsAvailable());
    }

    /**
     * Test parameterized constructor for Flight.
     */
    @Test
    void testFlightParameterizedConstructor() {
        Flight flight = new Flight("F123", "Delta", "10:00", "16:00", "JFK", "LAX", 350.0, 20);
        assertEquals("F123", flight.getFlightId());
        assertEquals("Delta", flight.getAirline());
        assertEquals("JFK", flight.getOrigin());
        assertEquals("LAX", flight.getDestination());
        assertEquals("10:00", flight.getTime());
        assertEquals("16:00", flight.getArrivalTime());
        assertEquals(350.0, flight.getPrice());
        assertEquals(20, flight.getSeatsAvailable());
    }
}
