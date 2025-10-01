package com.example.flightsearch.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightDTO.
 */
class FlightDTOTest {

    /**
     * Test all getters and setters for FlightDTO.
     */
    @Test
    @DisplayName("Test getters and setters")
    void testGettersAndSetters() {
        FlightDTO dto = new FlightDTO();
        dto.setFlightId("F001");
        dto.setAirline("Delta");
        dto.setOrigin("JFK");
        dto.setDestination("LAX");
        dto.setDepartureTime("2024-10-01T08:00:00");
        dto.setArrivalTime("2024-10-01T11:00:00");
        dto.setPrice(250.5);
        dto.setDuration("6h");

        assertEquals("F001", dto.getFlightId());
        assertEquals("Delta", dto.getAirline());
        assertEquals("JFK", dto.getOrigin());
        assertEquals("LAX", dto.getDestination());
        assertEquals("2024-10-01T08:00:00", dto.getDepartureTime());
        assertEquals("2024-10-01T11:00:00", dto.getArrivalTime());
        assertEquals(250.5, dto.getPrice());
        assertEquals("6h", dto.getDuration());
    }

    /**
     * Test edge cases for null and empty values.
     */
    @Test
    @DisplayName("Test edge cases for null and empty values")
    void testEdgeCases() {
        FlightDTO dto = new FlightDTO();
        dto.setFlightId(null);
        dto.setAirline("");
        dto.setOrigin(null);
        dto.setDestination("");
        dto.setDepartureTime(null);
        dto.setArrivalTime("");
        dto.setPrice(0.0);
        dto.setDuration(null);

        assertNull(dto.getFlightId());
        assertEquals("", dto.getAirline());
        assertNull(dto.getOrigin());
        assertEquals("", dto.getDestination());
        assertNull(dto.getDepartureTime());
        assertEquals("", dto.getArrivalTime());
        assertEquals(0.0, dto.getPrice());
        assertNull(dto.getDuration());
    }
}
