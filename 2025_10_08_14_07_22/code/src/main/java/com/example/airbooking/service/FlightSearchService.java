package com.example.airbooking.service;

import com.example.airbooking.model.FlightSearchRequest;
import com.example.airbooking.model.FlightSearchResponse;
import com.example.airbooking.exception.FlightNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for searching flights using airline APIs.
 */
@Service
public class FlightSearchService {

    /**
     * Search for flights by origin, destination, and date.
     * @param request the search request
     * @return search response with available flights
     */
    public FlightSearchResponse searchFlights(FlightSearchRequest request) {
        // Validate input fields
        if (request.getOrigin() == null || request.getOrigin().isEmpty() ||
            request.getDestination() == null || request.getDestination().isEmpty() ||
            request.getDate() == null) {
            throw new IllegalArgumentException("Search fields cannot be empty");
        }
        if (!request.getDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date must be in the future");
        }
        // TODO: Integrate with airline APIs to fetch flight data
        // Mock response for demonstration
        List<FlightSearchResponse.FlightInfo> flights = new ArrayList<>();
        flights.add(new FlightSearchResponse.FlightInfo("Delta", "10:00", 320.00, "F123"));
        return new FlightSearchResponse(flights);
    }
}
