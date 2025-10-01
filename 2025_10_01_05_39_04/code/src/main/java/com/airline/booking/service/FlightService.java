package com.airline.booking.service;

import com.airline.booking.model.Flight;
import com.airline.booking.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service for flight search and related business logic.
 */
@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;

    /**
     * Search for available flights based on origin, destination, date, and class.
     * @param origin origin airport code
     * @param destination destination airport code
     * @param date date of travel
     * @param flightClass class (e.g., Economy, Business)
     * @return list of available flights
     */
    @Cacheable("flights")
    public List<Flight> searchFlights(String origin, String destination, LocalDate date, String flightClass) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        List<Flight> flights = flightRepository.findByOriginAndDestinationAndDepartureBetweenAndFlightClass(
                origin, destination, start, end, flightClass);
        if (flights == null || flights.isEmpty()) {
            throw new RuntimeException("No flights found for the given criteria");
        }
        return flights;
    }

    /**
     * Find a flight by ID.
     * @param flightId flight ID
     * @return Flight
     */
    public Flight getFlightById(String flightId) {
        return flightRepository.findById(flightId).orElseThrow(() ->
                new RuntimeException("Invalid flight or no seats available"));
    }
}
