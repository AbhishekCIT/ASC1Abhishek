package com.example.flightsearch.repository;

import com.example.flightsearch.model.Flight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightRepository (custom query).
 * Uses @DataJpaTest for in-memory DB.
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class FlightRepositoryTest {
    @Autowired
    private FlightRepository flightRepository;

    /**
     * Test findAvailableFlights returns correct flights for normal scenario.
     */
    @Test
    @DisplayName("findAvailableFlights returns matching flights")
    void testFindAvailableFlights_Normal() {
        // Setup test data
        Flight flight = new Flight();
        flight.setAirline("Delta");
        flight.setOrigin("JFK");
        flight.setDestination("LAX");
        flight.setDepartureTime(LocalDateTime.now().plusDays(1).withHour(10));
        flight.setArrivalTime(LocalDateTime.now().plusDays(1).withHour(16));
        flight.setAvailableSeats(100);
        flight.setFlightClass("Economy");
        flight.setPrice(200.0);
        flight.setDuration("6h");
        flight.setLayovers(0);
        flightRepository.save(flight);

        List<Flight> result = flightRepository.findAvailableFlights(
                "JFK", "LAX", LocalDate.now().plusDays(1), 1, "Economy");
        assertFalse(result.isEmpty());
        assertEquals("Delta", result.get(0).getAirline());
    }

    /**
     * Test edge case: no flights found.
     */
    @Test
    @DisplayName("findAvailableFlights returns empty when no match")
    void testFindAvailableFlights_NoMatch() {
        List<Flight> result = flightRepository.findAvailableFlights(
                "ORD", "SFO", LocalDate.now().plusDays(2), 1, "Business");
        assertTrue(result.isEmpty());
    }

    /**
     * Test boundary condition: availableSeats exactly equals passengers.
     */
    @Test
    @DisplayName("findAvailableFlights returns flight when seats equal passengers")
    void testFindAvailableFlights_SeatsEqualsPassengers() {
        Flight flight = new Flight();
        flight.setAirline("United");
        flight.setOrigin("JFK");
        flight.setDestination("LAX");
        flight.setDepartureTime(LocalDateTime.now().plusDays(3).withHour(8));
        flight.setArrivalTime(LocalDateTime.now().plusDays(3).withHour(14));
        flight.setAvailableSeats(2);
        flight.setFlightClass("Economy");
        flight.setPrice(300.0);
        flight.setDuration("6h");
        flight.setLayovers(1);
        flightRepository.save(flight);

        List<Flight> result = flightRepository.findAvailableFlights(
                "JFK", "LAX", LocalDate.now().plusDays(3), 2, "Economy");
        assertFalse(result.isEmpty());
        assertEquals(2, result.get(0).getAvailableSeats());
    }

    /**
     * Test error scenario: invalid class (should return empty).
     */
    @Test
    @DisplayName("findAvailableFlights returns empty for invalid class")
    void testFindAvailableFlights_InvalidClass() {
        Flight flight = new Flight();
        flight.setAirline("Delta");
        flight.setOrigin("JFK");
        flight.setDestination("LAX");
        flight.setDepartureTime(LocalDateTime.now().plusDays(1).withHour(10));
        flight.setArrivalTime(LocalDateTime.now().plusDays(1).withHour(16));
        flight.setAvailableSeats(100);
        flight.setFlightClass("Economy");
        flight.setPrice(200.0);
        flight.setDuration("6h");
        flight.setLayovers(0);
        flightRepository.save(flight);

        List<Flight> result = flightRepository.findAvailableFlights(
                "JFK", "LAX", LocalDate.now().plusDays(1), 1, "First");
        assertTrue(result.isEmpty());
    }
}
