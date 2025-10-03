package com.asc.airbooking.repository;

import com.asc.airbooking.entity.Flight;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for FlightRepository.
 * Covers normal, edge, and boundary scenarios for findByOriginAndDestinationAndDepartureTimeBetween.
 */
@DataJpaTest
class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    /**
     * Purpose: Test finding flights by origin, destination, and departure time range.
     */
    @Test
    void testFindByOriginAndDestinationAndDepartureTimeBetween_Success() {
        Flight flight = new Flight("FL123", "Delta", "JFK", "LAX", LocalDateTime.of(2025, 11, 1, 10, 0), 320.00);
        flightRepository.save(flight);
        List<Flight> found = flightRepository.findByOriginAndDestinationAndDepartureTimeBetween(
                "JFK", "LAX", LocalDateTime.of(2025, 11, 1, 0, 0), LocalDateTime.of(2025, 11, 1, 23, 59));
        assertFalse(found.isEmpty());
        assertEquals("FL123", found.get(0).getFlightId());
    }

    /**
     * Purpose: Test finding flights with no match (should return empty list).
     */
    @Test
    void testFindByOriginAndDestinationAndDepartureTimeBetween_NoMatch() {
        List<Flight> found = flightRepository.findByOriginAndDestinationAndDepartureTimeBetween(
                "AAA", "BBB", LocalDateTime.of(2025, 1, 1, 0, 0), LocalDateTime.of(2025, 1, 1, 23, 59));
        assertTrue(found.isEmpty());
    }

    /**
     * Purpose: Test boundary condition for departure time (exact match at start/end).
     */
    @Test
    void testDepartureTimeBoundaryConditions() {
        Flight flight = new Flight("FL456", "United", "SFO", "ORD", LocalDateTime.of(2025, 12, 1, 15, 30), 150.50);
        flightRepository.save(flight);
        List<Flight> foundStart = flightRepository.findByOriginAndDestinationAndDepartureTimeBetween(
                "SFO", "ORD", LocalDateTime.of(2025, 12, 1, 15, 30), LocalDateTime.of(2025, 12, 1, 16, 0));
        assertFalse(foundStart.isEmpty());
        List<Flight> foundEnd = flightRepository.findByOriginAndDestinationAndDepartureTimeBetween(
                "SFO", "ORD", LocalDateTime.of(2025, 12, 1, 15, 0), LocalDateTime.of(2025, 12, 1, 15, 30));
        assertFalse(foundEnd.isEmpty());
    }
}
