package com.example.airtransport.repository;

import com.example.airtransport.model.Flight;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for accessing flight data.
 * In a real application, this would extend JpaRepository or similar.
 */
@Repository
public class FlightRepository {
    /**
     * Find available flights matching the search criteria.
     * @param origin Origin airport code
     * @param destination Destination airport code
     * @param departureDate Departure date
     * @param returnDate Return date (optional)
     * @param passengers Number of passengers
     * @return List of available flights
     */
    public List<Flight> findFlights(String origin, String destination, String departureDate, String returnDate, int passengers) {
        // For demonstration, return a static list
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("F123", origin, destination, "2025-10-01T10:00", "2025-10-01T13:00", 3, 300.00));
        return flights;
    }
}
