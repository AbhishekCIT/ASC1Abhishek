package com.example.flightsearch.service;

import com.example.flightsearch.model.Flight;
import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import com.example.flightsearch.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for querying the internal flight inventory database.
 */
@Service
public class FlightInventoryService {
    @Autowired
    private FlightRepository flightRepository;

    /**
     * Find flights in the internal DB matching the search criteria.
     * @param request Flight search request
     * @return List of matching flights
     */
    public List<FlightSearchResponse> findFlights(FlightSearchRequest request) {
        List<Flight> flights = flightRepository.findAvailableFlights(
                request.getOrigin(),
                request.getDestination(),
                request.getDate(),
                request.getPassengers(),
                request.getFlightClass()
        );
        // Map Flight entity to FlightSearchResponse
        return flights.stream().map(FlightSearchResponse::fromEntity).collect(Collectors.toList());
    }
}
