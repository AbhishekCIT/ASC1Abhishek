package com.example.flightsearch.repository;

import com.example.flightsearch.model.Flight;
import com.example.flightsearch.model.Seat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SeatRepository (custom query).
 * Uses @DataJpaTest for in-memory DB.
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class SeatRepositoryTest {
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private FlightRepository flightRepository;

    /**
     * Test findByFlight_FlightId returns correct seats for a flight.
     */
    @Test
    @DisplayName("findByFlight_FlightId returns matching seats")
    void testFindByFlightId_Normal() {
        Flight flight = new Flight();
        flight.setAirline("Delta");
        flight.setOrigin("JFK");
        flight.setDestination("LAX");
        flight.setFlightClass("Economy");
        flight.setAvailableSeats(100);
        flightRepository.save(flight);

        Seat seat1 = new Seat();
        seat1.setFlight(flight);
        seat1.setSeatNumber("1A");
        seat1.setSeatClass("Economy");
        seat1.setAvailable(true);
        seatRepository.save(seat1);

        Seat seat2 = new Seat();
        seat2.setFlight(flight);
        seat2.setSeatNumber("1B");
        seat2.setSeatClass("Economy");
        seat2.setAvailable(false);
        seatRepository.save(seat2);

        List<Seat> seats = seatRepository.findByFlight_FlightId(flight.getFlightId());
        assertEquals(2, seats.size());
        assertTrue(seats.stream().anyMatch(s -> "1A".equals(s.getSeatNumber())));
        assertTrue(seats.stream().anyMatch(s -> "1B".equals(s.getSeatNumber())));
    }

    /**
     * Test edge case: no seats for flight.
     */
    @Test
    @DisplayName("findByFlight_FlightId returns empty for unknown flight")
    void testFindByFlightId_NoSeats() {
        List<Seat> seats = seatRepository.findByFlight_FlightId(9999L);
        assertTrue(seats.isEmpty());
    }
}
