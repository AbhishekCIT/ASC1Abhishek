package com.airtransport.repository;

import com.airtransport.entity.FlightEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for FlightRepository.
 * Covers CRUD operations, edge cases, and error scenarios.
 */
@DataJpaTest
class FlightRepositoryTest {
    @Autowired
    private FlightRepository flightRepository;

    /**
     * Test saving and retrieving a flight (normal case).
     */
    @Test
    void testSaveAndFindById_Normal() {
        FlightEntity flight = new FlightEntity();
        flight.setFlightId("F123");
        flight.setAirline("Delta");
        flight.setOrigin("JFK");
        flight.setDestination("LAX");
        flight.setDate("2025-11-01");
        flight.setTime("10:00");
        flight.setPrice(200.0);
        flight.setSeatsAvailable(50);
        flightRepository.save(flight);
        Optional<FlightEntity> found = flightRepository.findById("F123");
        assertTrue(found.isPresent());
        assertEquals("Delta", found.get().getAirline());
    }

    /**
     * Test finding a non-existent flight (edge case).
     */
    @Test
    void testFindById_NotFound() {
        Optional<FlightEntity> found = flightRepository.findById("NON_EXISTENT");
        assertFalse(found.isPresent());
    }

    /**
     * Test deleting a flight (normal case).
     */
    @Test
    void testDeleteById_Normal() {
        FlightEntity flight = new FlightEntity();
        flight.setFlightId("F456");
        flightRepository.save(flight);
        flightRepository.deleteById("F456");
        Optional<FlightEntity> found = flightRepository.findById("F456");
        assertFalse(found.isPresent());
    }

    /**
     * Test saving flight with null ID (error scenario).
     */
    @Test
    void testSave_NullId() {
        FlightEntity flight = new FlightEntity();
        flight.setFlightId(null);
        assertThrows(Exception.class, () -> flightRepository.save(flight));
    }
}
