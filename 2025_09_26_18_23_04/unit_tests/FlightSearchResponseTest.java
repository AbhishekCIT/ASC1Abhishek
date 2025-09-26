package com.example.flightsearch.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightSearchResponse POJO (getters/setters, fromEntity, edge cases).
 */
class FlightSearchResponseTest {
    private FlightSearchResponse response;

    @BeforeEach
    void setUp() {
        response = new FlightSearchResponse();
    }

    /**
     * Test all getters and setters for normal values.
     */
    @Test
    void testGettersAndSetters_NormalValues() {
        response.setFlightId(10L);
        response.setAirline("United");
        LocalDateTime dep = LocalDateTime.now();
        LocalDateTime arr = dep.plusHours(5);
        response.setDeparture(dep);
        response.setArrival(arr);
        response.setDuration("5h");
        response.setLayovers(2);
        response.setPrice(450.50);

        assertEquals(10L, response.getFlightId());
        assertEquals("United", response.getAirline());
        assertEquals(dep, response.getDeparture());
        assertEquals(arr, response.getArrival());
        assertEquals("5h", response.getDuration());
        assertEquals(2, response.getLayovers());
        assertEquals(450.50, response.getPrice());
    }

    /**
     * Test edge case: negative price and layovers.
     */
    @Test
    void testSetters_NegativeValues() {
        response.setPrice(-200.0);
        response.setLayovers(-1);
        assertEquals(-200.0, response.getPrice());
        assertEquals(-1, response.getLayovers());
    }

    /**
     * Test boundary case: zero layovers and price.
     */
    @Test
    void testSetters_ZeroValues() {
        response.setLayovers(0);
        response.setPrice(0.0);
        assertEquals(0, response.getLayovers());
        assertEquals(0.0, response.getPrice());
    }

    /**
     * Test null values for String fields and date-times.
     */
    @Test
    void testSetters_NullValues() {
        response.setAirline(null);
        response.setDeparture(null);
        response.setArrival(null);
        response.setDuration(null);
        assertNull(response.getAirline());
        assertNull(response.getDeparture());
        assertNull(response.getArrival());
        assertNull(response.getDuration());
    }

    /**
     * Test fromEntity static method for normal mapping.
     */
    @Test
    void testFromEntity_NormalMapping() {
        Flight flight = new Flight();
        flight.setFlightId(99L);
        flight.setAirline("Delta");
        LocalDateTime dep = LocalDateTime.now();
        LocalDateTime arr = dep.plusHours(3);
        flight.setDepartureTime(dep);
        flight.setArrivalTime(arr);
        flight.setDuration("3h");
        flight.setLayovers(0);
        flight.setPrice(123.45);

        FlightSearchResponse resp = FlightSearchResponse.fromEntity(flight);
        assertEquals(99L, resp.getFlightId());
        assertEquals("Delta", resp.getAirline());
        assertEquals(dep, resp.getDeparture());
        assertEquals(arr, resp.getArrival());
        assertEquals("3h", resp.getDuration());
        assertEquals(0, resp.getLayovers());
        assertEquals(123.45, resp.getPrice());
    }

    /**
     * Test fromEntity with null fields in Flight.
     */
    @Test
    void testFromEntity_NullFields() {
        Flight flight = new Flight();
        FlightSearchResponse resp = FlightSearchResponse.fromEntity(flight);
        assertEquals(0L, resp.getFlightId());
        assertNull(resp.getAirline());
        assertNull(resp.getDeparture());
        assertNull(resp.getArrival());
        assertNull(resp.getDuration());
        assertEquals(0, resp.getLayovers());
        assertEquals(0.0, resp.getPrice());
    }
}
