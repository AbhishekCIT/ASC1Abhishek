package com.example.airbooking.service;

import com.example.airbooking.model.FlightResponse;
import com.example.airbooking.model.FlightDetailResponse;
import com.example.airbooking.util.AirlineApiClient;
import com.example.airbooking.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for searching flights and fetching flight details.
 */
@Service
public class FlightService {

    @Autowired
    private AirlineApiClient airlineApiClient;

    /**
     * Search for available flights matching the criteria.
     */
    public List<FlightResponse> searchFlights(String date, String destination, String clazz) {
        ValidationUtil.validateDate(date);
        ValidationUtil.validateDestination(destination);
        // Fetch flights from airline APIs
        List<FlightResponse> flights = airlineApiClient.fetchFlights(date, destination, clazz);
        // Filter available flights
        return flights.stream()
                .filter(FlightResponse::isAvailability)
                .collect(Collectors.toList());
    }

    /**
     * Get details for a specific flight.
     */
    public FlightDetailResponse getFlightDetails(Long flightId) {
        return airlineApiClient.fetchFlightDetails(flightId);
    }

    /**
     * Check if a flight is available for booking.
     */
    public boolean isFlightAvailable(Long flightId) {
        FlightDetailResponse details = airlineApiClient.fetchFlightDetails(flightId);
        return details != null && details.isAvailability();
    }
}
