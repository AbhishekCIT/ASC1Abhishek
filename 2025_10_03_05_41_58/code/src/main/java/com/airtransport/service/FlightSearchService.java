package com.airtransport.service;

import com.airtransport.entity.Flight;
import com.airtransport.integration.AirlineApiClient;
import com.airtransport.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service for searching flights and checking availability.
 */
@Service
@RequiredArgsConstructor
public class FlightSearchService {
    private final FlightRepository flightRepository;
    private final AirlineApiClient airlineApiClient;

    /**
     * Search for flights based on parameters.
     * @param date Travel date
     * @param destination Destination airport code
     * @param airline Airline name (optional)
     * @return List of flights
     */
    public List<Flight> searchFlights(LocalDate date, String destination, String airline) {
        if (date == null) {
            throw new IllegalArgumentException("Date is required and must be valid");
        }
        if (!StringUtils.hasText(destination)) {
            throw new IllegalArgumentException("Destination is required");
        }
        // Optionally, integrate with airline APIs for real-time data
        List<Flight> flights = airlineApiClient.getAvailableFlights(date.toString(), destination, airline);
        // Fallback to DB if API returns empty
        if (flights == null || flights.isEmpty()) {
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.atTime(23, 59, 59);
            if (StringUtils.hasText(airline)) {
                flights = flightRepository.findByDestinationAndDepartureBetweenAndAirline(destination, start, end, airline);
            } else {
                flights = flightRepository.findByDestinationAndDepartureBetween(destination, start, end);
            }
        }
        if (flights == null || flights.isEmpty()) {
            throw new RuntimeException("No flights found for criteria");
        }
        return flights;
    }

    /**
     * Check availability of a flight.
     * @param flightId Flight identifier
     * @return true if available, false otherwise
     */
    public boolean checkAvailability(String flightId) {
        return airlineApiClient.checkAvailability(flightId);
    }
}
