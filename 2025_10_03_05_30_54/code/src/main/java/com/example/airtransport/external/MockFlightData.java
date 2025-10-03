package com.example.airtransport.external;

import com.example.airtransport.model.*;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Utility class for providing mock flight data for demonstration/testing.
 */
public class MockFlightData {
    public static FlightSearchResponse getMockResponse(FlightSearchRequest request) {
        List<FlightResult> flights = new ArrayList<>();
        // Example mock flight
        FlightResult flight = new FlightResult();
        flight.setFlightId("DL100");
        flight.setAirline("Delta");
        flight.setDepartureTime(ZonedDateTime.parse(request.getDepartureDate() + "T09:00:00Z"));
        flight.setArrivalTime(ZonedDateTime.parse(request.getDepartureDate() + "T17:00:00Z"));
        flight.setPrice(500);
        flight.setStops(1);
        flight.setLayovers(Collections.singletonList("CDG"));
        flights.add(flight);
        FlightSearchResponse response = new FlightSearchResponse();
        response.setFlights(flights);
        response.setTotalResults(flights.size());
        return response;
    }
}
