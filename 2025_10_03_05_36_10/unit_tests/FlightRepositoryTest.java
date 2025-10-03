package com.airtransport.repository;

import com.airtransport.model.Flight;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for FlightRepository.
 * Covers custom query and edge cases.
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class FlightRepositoryTest {
    @Autowired
    private FlightRepository flightRepository;

    /**
     * Test findByDestinationAndDepartureBetween with valid input.
     * Purpose: Should return flights matching criteria.
     */
    @Test
    void testFindByDestinationAndDepartureBetween_Success() {
        Flight flight = new Flight();
        flight.setDestination("JFK");
        flight.setDeparture(LocalDateTime.of(2025, 1, 1, 10, 0));
        flight.setAirline("Delta");
        flight.setPrice(100.0);
        flightRepository.save(flight);

        List<Flight> result = flightRepository.findByDestinationAndDepartureBetween(
                "JFK",
                LocalDateTime.of(2025, 1, 1, 0, 0),
                LocalDateTime.of(2025, 1, 1, 23, 59)
        );
        assertFalse(result.isEmpty());
        assertEquals("JFK", result.get(0).getDestination());
    }

    /**
     * Test findByDestinationAndDepartureBetween with no matching flights (edge case).
     */
    @Test
    void testFindByDestinationAndDepartureBetween_NoMatch() {
        List<Flight> result = flightRepository.findByDestinationAndDepartureBetween(
                "LAX",
                LocalDateTime.of(2025, 1, 1, 0, 0),
                LocalDateTime.of(2025, 1, 1, 23, 59)
        );
        assertTrue(result.isEmpty());
    }
}
