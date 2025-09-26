package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightEntity (POJO).
 */
class FlightEntityTest {
    private FlightEntity flightEntity;

    @BeforeEach
    void setUp() {
        flightEntity = new FlightEntity();
    }

    /**
     * Test setting and getting flightId.
     */
    @Test
    @DisplayName("Set and get flightId")
    void testFlightId() {
        flightEntity.setFlightId(1L);
        assertEquals(1L, flightEntity.getFlightId());
    }

    /**
     * Test setting and getting airlineId.
     */
    @Test
    @DisplayName("Set and get airlineId")
    void testAirlineId() {
        flightEntity.setAirlineId(2L);
        assertEquals(2L, flightEntity.getAirlineId());
    }

    /**
     * Test setting and getting origin.
     */
    @Test
    @DisplayName("Set and get origin")
    void testOrigin() {
        flightEntity.setOrigin("JFK");
        assertEquals("JFK", flightEntity.getOrigin());
    }

    /**
     * Test setting and getting destination.
     */
    @Test
    @DisplayName("Set and get destination")
    void testDestination() {
        flightEntity.setDestination("LAX");
        assertEquals("LAX", flightEntity.getDestination());
    }

    /**
     * Test setting and getting departureTime.
     */
    @Test
    @DisplayName("Set and get departureTime")
    void testDepartureTime() {
        flightEntity.setDepartureTime("10:00");
        assertEquals("10:00", flightEntity.getDepartureTime());
    }

    /**
     * Test setting and getting arrivalTime.
     */
    @Test
    @DisplayName("Set and get arrivalTime")
    void testArrivalTime() {
        flightEntity.setArrivalTime("13:00");
        assertEquals("13:00", flightEntity.getArrivalTime());
    }

    /**
     * Test setting and getting price.
     */
    @Test
    @DisplayName("Set and get price")
    void testPrice() {
        flightEntity.setPrice(200.0);
        assertEquals(200.0, flightEntity.getPrice());
    }

    /**
     * Test setting and getting seatsAvailable.
     */
    @Test
    @DisplayName("Set and get seatsAvailable")
    void testSeatsAvailable() {
        flightEntity.setSeatsAvailable(10);
        assertEquals(10, flightEntity.getSeatsAvailable());
    }

    /**
     * Test default values (should be null).
     */
    @Test
    @DisplayName("Default values are null")
    void testDefaultValues() {
        assertNull(flightEntity.getFlightId());
        assertNull(flightEntity.getAirlineId());
        assertNull(flightEntity.getOrigin());
        assertNull(flightEntity.getDestination());
        assertNull(flightEntity.getDepartureTime());
        assertNull(flightEntity.getArrivalTime());
        assertNull(flightEntity.getPrice());
        assertNull(flightEntity.getSeatsAvailable());
    }
}
