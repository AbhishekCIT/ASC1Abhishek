package com.airtransport.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightEntity getters and setters.
 */
public class FlightEntityTest {
    /**
     * Test all getters and setters for normal and edge cases.
     */
    @Test
    void testGettersAndSetters() {
        FlightEntity entity = new FlightEntity();
        entity.setFlightId("F123");
        entity.setAirline("Delta");
        entity.setOrigin("JFK");
        entity.setDestination("LAX");
        entity.setDepartureTime("2025-12-01T08:00:00");
        entity.setArrivalTime("2025-12-01T11:00:00");
        entity.setPrice(350.0);
        entity.setFlightNumber("DL123");
        entity.setDurationMinutes(180);

        assertEquals("F123", entity.getFlightId());
        assertEquals("Delta", entity.getAirline());
        assertEquals("JFK", entity.getOrigin());
        assertEquals("LAX", entity.getDestination());
        assertEquals("2025-12-01T08:00:00", entity.getDepartureTime());
        assertEquals("2025-12-01T11:00:00", entity.getArrivalTime());
        assertEquals(350.0, entity.getPrice());
        assertEquals("DL123", entity.getFlightNumber());
        assertEquals(180, entity.getDurationMinutes());
    }

    /**
     * Test edge cases: null and negative values.
     */
    @Test
    void testEdgeCases() {
        FlightEntity entity = new FlightEntity();
        entity.setFlightId(null);
        entity.setAirline(null);
        entity.setOrigin(null);
        entity.setDestination(null);
        entity.setDepartureTime(null);
        entity.setArrivalTime(null);
        entity.setPrice(-1.0);
        entity.setFlightNumber(null);
        entity.setDurationMinutes(-10);

        assertNull(entity.getFlightId());
        assertNull(entity.getAirline());
        assertNull(entity.getOrigin());
        assertNull(entity.getDestination());
        assertNull(entity.getDepartureTime());
        assertNull(entity.getArrivalTime());
        assertEquals(-1.0, entity.getPrice());
        assertNull(entity.getFlightNumber());
        assertEquals(-10, entity.getDurationMinutes());
    }
}
