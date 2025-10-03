package com.example.airtransport.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Flight entity.
 * Covers all getters, setters, and edge/boundary cases.
 */
public class FlightEntityTest {

    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight();
    }

    /**
     * Test all getters and setters with normal values.
     */
    @Test
    void testGettersAndSetters_NormalValues() {
        Integer id = 1;
        String flightNumber = "AI101";
        String airline = "Air India";
        String origin = "DEL";
        String destination = "JFK";
        LocalDateTime departure = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime arrival = LocalDateTime.of(2025, 1, 1, 20, 0);
        BigDecimal price = new BigDecimal("999.99");
        Integer layovers = 1;
        Integer seatsAvailable = 100;

        flight.setId(id);
        flight.setFlightNumber(flightNumber);
        flight.setAirline(airline);
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setDepartureTime(departure);
        flight.setArrivalTime(arrival);
        flight.setPrice(price);
        flight.setLayovers(layovers);
        flight.setSeatsAvailable(seatsAvailable);

        assertEquals(id, flight.getId());
        assertEquals(flightNumber, flight.getFlightNumber());
        assertEquals(airline, flight.getAirline());
        assertEquals(origin, flight.getOrigin());
        assertEquals(destination, flight.getDestination());
        assertEquals(departure, flight.getDepartureTime());
        assertEquals(arrival, flight.getArrivalTime());
        assertEquals(price, flight.getPrice());
        assertEquals(layovers, flight.getLayovers());
        assertEquals(seatsAvailable, flight.getSeatsAvailable());
    }

    /**
     * Test setters and getters with null values (edge case).
     */
    @Test
    void testSettersAndGetters_NullValues() {
        flight.setId(null);
        flight.setFlightNumber(null);
        flight.setAirline(null);
        flight.setOrigin(null);
        flight.setDestination(null);
        flight.setDepartureTime(null);
        flight.setArrivalTime(null);
        flight.setPrice(null);
        flight.setLayovers(null);
        flight.setSeatsAvailable(null);

        assertNull(flight.getId());
        assertNull(flight.getFlightNumber());
        assertNull(flight.getAirline());
        assertNull(flight.getOrigin());
        assertNull(flight.getDestination());
        assertNull(flight.getDepartureTime());
        assertNull(flight.getArrivalTime());
        assertNull(flight.getPrice());
        assertNull(flight.getLayovers());
        assertNull(flight.getSeatsAvailable());
    }

    /**
     * Test boundary values for layovers and seatsAvailable.
     */
    @Test
    void testBoundaryValues() {
        flight.setLayovers(0);
        flight.setSeatsAvailable(0);
        assertEquals(0, flight.getLayovers());
        assertEquals(0, flight.getSeatsAvailable());

        flight.setLayovers(Integer.MAX_VALUE);
        flight.setSeatsAvailable(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, flight.getLayovers());
        assertEquals(Integer.MAX_VALUE, flight.getSeatsAvailable());
    }
}
