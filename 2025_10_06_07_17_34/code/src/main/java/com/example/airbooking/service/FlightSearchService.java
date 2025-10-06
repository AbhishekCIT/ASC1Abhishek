package com.example.airbooking.service;

import com.example.airbooking.model.Flight;
import com.example.airbooking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service for searching available flights.
 */
@Service
public class FlightSearchService {
    @Autowired
    private FlightRepository flightRepository;

    /**
     * Searches flights by origin, destination, date, and number of passengers.
     * @param origin Origin airport code
     * @param destination Destination airport code
     * @param date Travel date
     * @param passengers Number of passengers
     * @return List of available flights
     */
    public List<Flight> searchFlights(String origin, String destination, LocalDate date, int passengers) {
        // Validate inputs
        if (origin == null || origin.isEmpty() || destination == null || destination.isEmpty()) {
            throw new IllegalArgumentException("Origin and destination must be valid codes.");
        }
        if (date == null || date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid travel date.");
        }
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        // Query repository for matching flights
        return flightRepository.findByOriginAndDestinationAndDepartureBetween(origin, destination, start, end);
    }
}