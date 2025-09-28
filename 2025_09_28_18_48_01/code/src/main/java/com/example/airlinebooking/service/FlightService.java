package com.example.airlinebooking.service;

import com.example.airlinebooking.entity.Flight;
import com.example.airlinebooking.exception.FlightNotFoundException;
import com.example.airlinebooking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service for flight-related business logic.
 */
@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    /**
     * Searches for available flights by origin, destination, and date.
     * @param origin flight origin
     * @param destination flight destination
     * @param date flight date
     * @return list of available flights
     */
    @Cacheable("flights")
    public List<Flight> searchFlights(String origin, String destination, LocalDate date) {
        if (origin == null || destination == null || date == null) {
            throw new IllegalArgumentException("All search fields are required");
        }
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(23, 59, 59);
        List<Flight> flights = flightRepository.findByOriginAndDestinationAndDepartureTimeBetween(origin, destination, start, end);
        if (flights.isEmpty()) {
            throw new FlightNotFoundException("No flights found for the given criteria");
        }
        return flights;
    }

    /**
     * Checks if seats are available for a given flight.
     * @param flightId flight id
     * @param requestedSeats number of seats requested
     * @return true if available, false otherwise
     */
    public boolean checkSeatAvailability(Integer flightId, int requestedSeats) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found"));
        return flight.getSeatsAvailable() >= requestedSeats;
    }

    /**
     * Decrements seat availability for a flight.
     * @param flightId flight id
     * @param seats number of seats to decrement
     */
    public void decrementSeats(Integer flightId, int seats) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found"));
        int available = flight.getSeatsAvailable();
        if (available < seats) {
            throw new IllegalStateException("Not enough seats available");
        }
        flight.setSeatsAvailable(available - seats);
        flightRepository.save(flight);
    }
}
