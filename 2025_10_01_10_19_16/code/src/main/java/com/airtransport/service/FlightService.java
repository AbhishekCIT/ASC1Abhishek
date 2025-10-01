package com.airtransport.service;

import com.airtransport.model.Flight;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for flight search logic and validations.
 */
@Service
public class FlightService {

    /**
     * Search for available flights based on origin, destination, and date.
     * @param origin Origin airport code
     * @param destination Destination airport code
     * @param date Travel date (yyyy-MM-dd)
     * @return List of available flights
     */
    public List<Flight> searchFlights(String origin, String destination, String date) {
        // Validate input
        if (origin == null || origin.isEmpty() || !isValidAirportCode(origin)) {
            throw new IllegalArgumentException("Invalid origin airport code");
        }
        if (destination == null || destination.isEmpty() || !isValidAirportCode(destination)) {
            throw new IllegalArgumentException("Invalid destination airport code");
        }
        LocalDate travelDate;
        try {
            travelDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid travel date");
        }
        if (travelDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid travel date");
        }
        // TODO: Integrate with airline inventory system for real data
        // For demo, return a mock list
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("F123", "Delta", "10:00", "16:00", "JFK", "LAX", 350.00, 20));
        return flights;
    }

    // Validate airport code (simple check: length == 3 and uppercase)
    private boolean isValidAirportCode(String code) {
        return code != null && code.matches("[A-Z]{3}");
    }
}
