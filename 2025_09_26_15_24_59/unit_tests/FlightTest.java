package com.airtransport.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Flight entity.
 */
class FlightTest {
    /**
     * Test getters and setters for Flight entity.
     */
    @Test
    @DisplayName("Flight entity getters and setters work as expected")
    void testFlightEntityGettersSetters() {
        Flight flight = new Flight();
        String flightId = "F123";
        String airlineId = "A1";
        String origin = "JFK";
        String destination = "LAX";
        LocalDateTime departure = LocalDateTime.now();
        LocalDateTime arrival = departure.plusHours(5);
        double price = 350.0;

        flight.setFlightId(flightId);
        flight.setAirlineId(airlineId);
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setDeparture(departure);
        flight.setArrival(arrival);
        flight.setPrice(price);

        assertEquals(flightId, flight.getFlightId());
        assertEquals(airlineId, flight.getAirlineId());
        assertEquals(origin, flight.getOrigin());
        assertEquals(destination, flight.getDestination());
        assertEquals(departure, flight.getDeparture());
        assertEquals(arrival, flight.getArrival());
        assertEquals(price, flight.getPrice());
    }

    /**
     * Test default values for Flight entity.
     */
    @Test
    @DisplayName("Flight entity default values are null/zero")
    void testFlightEntityDefaults() {
        Flight flight = new Flight();
        assertNull(flight.getFlightId());
        assertNull(flight.getAirlineId());
        assertNull(flight.getOrigin());
        assertNull(flight.getDestination());
        assertNull(flight.getDeparture());
        assertNull(flight.getArrival());
        assertEquals(0.0, flight.getPrice());
    }
}
