package com.example.airbooking.service;

import com.example.airbooking.model.Flight;
import com.example.airbooking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service for searching flights.
 */
@Service
public class FlightSearchService {
    private final FlightRepository flightRepository;
    private final AirlineApiClient airlineApiClient;

    @Autowired
    public FlightSearchService(FlightRepository flightRepository, AirlineApiClient airlineApiClient) {
        this.flightRepository = flightRepository;
        this.airlineApiClient = airlineApiClient;
    }

    /**
     * Finds available flights for given origin, destination, and date.
     * @param origin Origin airport code
     * @param destination Destination airport code
     * @param date Flight date
     * @return List of available flights
     */
    public List<Flight> findFlights(String origin, String destination, LocalDate date) {
        // Validate input
        if (origin == null || origin.isEmpty()) {
            throw new IllegalArgumentException("Origin is required");
        }
        if (destination == null || destination.isEmpty()) {
            throw new IllegalArgumentException("Destination is required");
        }
        if (date == null || date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be in the past");
        }
        // Optionally fetch from external airline APIs and update DB/cache
        List<Flight> externalFlights = airlineApiClient.getFlights(origin, destination, date);
        // Save or update flights in DB as needed (not shown here)
        // Return merged list (for simplicity, just returning externalFlights)
        return externalFlights;
    }
}
