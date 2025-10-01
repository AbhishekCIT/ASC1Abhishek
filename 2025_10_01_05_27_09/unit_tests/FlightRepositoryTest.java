package com.example.flightsearch.repository;

import com.example.flightsearch.entity.Flight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for FlightRepository.
 */
@DataJpaTest
class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    /**
     * Test findByOriginAndDestinationAndDate returns correct flights.
     */
    @Test
    @DisplayName("findByOriginAndDestinationAndDate returns matching flights")
    void testFindByOriginAndDestinationAndDate() {
        // Setup
        Flight flight = new Flight();
        flight.setFlightId("F100");
        flight.setAirline("Delta");
        flight.setOrigin("JFK");
        flight.setDestination("LAX");
        flight.setDepartureTime(LocalDateTime.of(2024, 10, 1, 8, 0));
        flight.setArrivalTime(LocalDateTime.of(2024, 10, 1, 11, 0));
        flight.setPrice(300.0);
        flight.setDuration("6h");
        flightRepository.save(flight);

        // Should match
        List<Flight> found = flightRepository.findByOriginAndDestinationAndDate("JFK", "LAX", LocalDate.of(2024, 10, 1));
        assertFalse(found.isEmpty());
        assertEquals("F100", found.get(0).getFlightId());
    }

    /**
     * Test no flights found for non-matching criteria.
     */
    @Test
    @DisplayName("findByOriginAndDestinationAndDate returns empty for no match")
    void testFindByOriginAndDestinationAndDate_NoMatch() {
        List<Flight> found = flightRepository.findByOriginAndDestinationAndDate("XXX", "YYY", LocalDate.of(2025, 1, 1));
        assertTrue(found.isEmpty());
    }
}
