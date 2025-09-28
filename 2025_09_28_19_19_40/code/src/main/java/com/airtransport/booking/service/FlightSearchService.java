package com.airtransport.booking.service;

import com.airtransport.booking.entity.Flight;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for searching flights via airline APIs.
 */
@Service
public class FlightSearchService {
    /**
     * Searches for flights matching the given criteria.
     * @param origin Origin airport code or name
     * @param destination Destination airport code or name
     * @param date Travel date (must be in the future)
     * @param flightClass Class of service (e.g., Economy, Business)
     * @return List of available flights
     */
    public List<Flight> searchFlights(String origin, String destination, LocalDate date, String flightClass) {
        // Validation
        if (origin == null || origin.isBlank() || destination == null || destination.isBlank()) {
            throw new IllegalArgumentException("Invalid origin or destination");
        }
        if (date == null || date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Travel date must be in the future");
        }
        // TODO: Integrate with airline APIs for real data
        // For demo purposes, return a static list
        List<Flight> flights = new ArrayList<>();
        Flight flight = new Flight();
        flight.setFlightId(123L);
        flight.setAirline("Delta");
        flight.setDeparture(LocalDateTime.of(date, LocalTime.of(10, 0)));
        flight.setArrival(LocalDateTime.of(date, LocalTime.of(13, 0)));
        flight.setPrice(350.0);
        flight.setFlightClass(flightClass);
        flights.add(flight);
        return flights;
    }
}
