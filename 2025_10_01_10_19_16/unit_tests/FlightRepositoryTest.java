package com.airtransport.repository;

import com.airtransport.model.Flight;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightRepository.
 */
@DataJpaTest
class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    /**
     * Test saving and retrieving a Flight entity.
     */
    @Test
    void testSaveAndFindFlight() {
        Flight flight = new Flight("F123", "Delta", "10:00", "16:00", "JFK", "LAX", 350.0, 20);
        flightRepository.save(flight);

        Flight found = flightRepository.findById("F123").orElse(null);
        assertNotNull(found);
        assertEquals("Delta", found.getAirline());
        assertEquals("JFK", found.getOrigin());
        assertEquals("LAX", found.getDestination());
        assertEquals("10:00", found.getTime());
        assertEquals("16:00", found.getArrivalTime());
        assertEquals(350.0, found.getPrice());
        assertEquals(20, found.getSeatsAvailable());
    }
}
