package com.airtransport.client;

import com.airtransport.model.Flight;
import com.airtransport.model.FlightSearchRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Client for integrating with third-party flight data APIs (e.g., Amadeus, Sabre).
 */
@Component
public class FlightAPIClient {

    /**
     * Fetch flights from external APIs based on search criteria.
     * This is a placeholder for actual integration.
     * @param request Flight search request
     * @return List of flights
     */
    public List<Flight> fetchFlights(FlightSearchRequest request) {
        // TODO: Integrate with Amadeus/Sabre API
        // For now, return a mock list
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("DL123", "Delta", request.getOrigin(), request.getDestination(),
                request.getDepartureDate() + "T08:00:00", request.getDepartureDate() + "T11:00:00", 350.00, "DL123", 180));
        return flights;
    }
}
