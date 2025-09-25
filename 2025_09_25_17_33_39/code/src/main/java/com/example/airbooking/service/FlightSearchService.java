package com.example.airbooking.service;

import com.example.airbooking.model.FlightResponse;
import com.example.airbooking.util.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * FlightSearchService handles business logic for searching flights.
 */
@Service
public class FlightSearchService {
    @Autowired
    private AirlineIntegrationService airlineIntegrationService;

    /**
     * Searches for available flights based on criteria.
     * @param from source airport code
     * @param to destination airport code
     * @param date date of travel
     * @param passengers number of passengers
     * @return list of available flights
     */
    @Cacheable("flightSearch")
    public List<FlightResponse> searchFlights(String from, String to, String date, int passengers) {
        // Validate input
        if (from == null || to == null || date == null || passengers <= 0) {
            throw new InvalidInputException("All search fields are required.");
        }
        return airlineIntegrationService.fetchFlights(from, to, date, passengers);
    }
}
