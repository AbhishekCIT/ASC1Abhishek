package com.example.airtransport.util;

import com.example.airtransport.model.Flight;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility component for connecting to third-party flight APIs (Amadeus, Sabre).
 * This is a stub implementation; actual API integration logic should be added.
 */
@Component
public class ExternalFlightAPIClient {

    /**
     * Fetch flights from external APIs.
     * @param destination Destination airport code.
     * @param date Date of travel.
     * @param passengers Number of passengers.
     * @return List of available flights.
     */
    public List<Flight> getFlights(String destination, String date, int passengers) {
        // TODO: Integrate with Amadeus/Sabre APIs
        // Stub: Return dummy data
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("F123", "Delta", destination, date, 350.0, "3h", 10));
        flights.add(new Flight("F124", "United", destination, date, 370.0, "3h 15m", 5));
        return flights;
    }

    /**
     * Fetch a single flight by ID from external APIs.
     * @param flightId Flight ID.
     * @return Flight object or null if not found.
     */
    public Flight getFlightById(String flightId) {
        // TODO: Integrate with Amadeus/Sabre APIs
        // Stub: Return dummy data
        if ("F123".equals(flightId)) {
            return new Flight("F123", "Delta", "NYC", "2025-10-01", 350.0, "3h", 10);
        }
        if ("F124".equals(flightId)) {
            return new Flight("F124", "United", "NYC", "2025-10-01", 370.0, "3h 15m", 5);
        }
        return null;
    }
}
