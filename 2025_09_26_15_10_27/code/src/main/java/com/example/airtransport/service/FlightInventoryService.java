package com.example.airtransport.service;

import com.example.airtransport.model.Flight;
import com.example.airtransport.model.Seat;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

/**
 * Service for querying flight data and seat availability.
 */
@Service
public class FlightInventoryService {

    /**
     * Search for available flights based on origin, destination, and date.
     * This would typically integrate with a third-party flight API.
     */
    public List<Flight> searchFlights(String origin, String destination, LocalDate date) {
        // TODO: Integrate with third-party flight API
        // For demo, return a static list
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("F123", origin, destination, date.atTime(10, 0), 350.0, 5));
        flights.add(new Flight("F456", origin, destination, date.atTime(15, 30), 420.0, 3));
        return flights;
    }

    /**
     * Check if a seat is available for a given flight.
     */
    public boolean isSeatAvailable(String flightId) {
        // TODO: Check real-time seat availability from DB or API
        // For demo, always return true
        return true;
    }
}
