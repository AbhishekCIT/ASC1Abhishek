package com.example.flightbooking.service;

import com.example.flightbooking.model.Flight;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Client for integrating with Airline GDS APIs.
 */
@Component
public class GDSClient {

    /**
     * Fetches available flights from GDS API.
     * @param origin the origin airport
     * @param destination the destination airport
     * @param date the date of travel
     * @return list of available flights
     */
    public List<Flight> getAvailableFlights(String origin, String destination, LocalDate date) {
        // TODO: Integrate with real GDS API
        // For now, return mock data
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("F123", "AA", origin, destination, date.atTime(9, 0), 5, 300.0));
        flights.add(new Flight("F124", "UA", origin, destination, date.atTime(13, 30), 3, 320.0));
        return flights;
    }

    /**
     * Reserves a seat for a given flight via GDS API.
     * @param flightId the flight ID
     * @return true if seat reserved, false otherwise
     */
    public boolean reserveSeat(String flightId) {
        // TODO: Integrate with real GDS API
        // For now, always return true
        return true;
    }
}
