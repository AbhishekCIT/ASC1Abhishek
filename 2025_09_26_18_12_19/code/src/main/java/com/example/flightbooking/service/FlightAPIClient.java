package com.example.flightbooking.service;

import com.example.flightbooking.model.Flight;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Client for integrating with third-party flight APIs.
 */
@Component
public class FlightAPIClient {
    /**
     * Fetch available flights from third-party API.
     * @param origin IATA code
     * @param destination IATA code
     * @param date Travel date
     * @return List of available flights
     */
    public List<Flight> getAvailableFlights(String origin, String destination, LocalDate date) {
        // Simulate third-party API call
        // In reality, perform HTTP requests and parse response
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("F123", origin, destination, date, 350.00, "Economy", 5));
        flights.add(new Flight("F124", origin, destination, date, 420.00, "Business", 2));
        return flights;
    }
}
