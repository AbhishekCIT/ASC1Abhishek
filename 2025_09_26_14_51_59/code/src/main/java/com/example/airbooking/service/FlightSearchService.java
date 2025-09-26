package com.example.airbooking.service;

import com.example.airbooking.model.Flight;
import com.example.airbooking.util.AirlineApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

/**
 * Business logic for searching flights.
 */
@Service
public class FlightSearchService {
    @Autowired
    private AirlineApiClient airlineApiClient;

    /**
     * Search flights by origin, destination, and date.
     * Validates input and fetches flights from airline API.
     */
    public List<Flight> searchFlights(String origin, String destination, String date) {
        // Validate origin and destination
        if (origin == null || origin.length() != 3) {
            throw new IllegalArgumentException("Origin is required and must be a valid airport code.");
        }
        if (destination == null || destination.length() != 3) {
            throw new IllegalArgumentException("Destination is required and must be a valid airport code.");
        }
        // Validate date
        LocalDate flightDate;
        try {
            flightDate = LocalDate.parse(date);
        } catch (Exception e) {
            throw new IllegalArgumentException("Date is required and must be valid.");
        }
        if (flightDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Date must not be in the past.");
        }
        // Fetch flights from airline API
        List<Flight> flights = airlineApiClient.fetchFlights(origin, destination, flightDate);
        if (flights == null || flights.isEmpty()) {
            throw new RuntimeException("No flights found for the given criteria.");
        }
        return flights;
    }
}
