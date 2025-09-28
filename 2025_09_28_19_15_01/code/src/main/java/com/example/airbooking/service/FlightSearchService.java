package com.example.airbooking.service;

import com.example.airbooking.model.FlightResponse;
import com.example.airbooking.model.FlightSearchRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

/**
 * Service for searching flights by integrating with airline APIs.
 */
@Service
public class FlightSearchService {
    /**
     * Search for available flights based on origin, destination, and date.
     * @param request FlightSearchRequest
     * @return List of FlightResponse
     */
    public List<FlightResponse> searchFlights(FlightSearchRequest request) {
        // TODO: Integrate with airline APIs for real-time data
        // For demonstration, return mock data
        List<FlightResponse> flights = new ArrayList<>();
        flights.add(new FlightResponse("F123", "Delta", 350, "10:00", "13:00"));
        flights.add(new FlightResponse("F124", "United", 400, "11:00", "14:00"));
        return flights;
    }
}
