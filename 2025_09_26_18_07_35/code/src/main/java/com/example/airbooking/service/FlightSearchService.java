package com.example.airbooking.service;

import com.example.airbooking.model.SearchCriteria;
import com.example.airbooking.model.FlightDTO;
import com.example.airbooking.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for searching flights and integrating with airline APIs.
 */
@Service
public class FlightSearchService {
    @Autowired
    private ValidationUtils validationUtils;

    /**
     * Searches for flights based on criteria. Integrates with airline APIs.
     * @param criteria SearchCriteria
     * @return List of FlightDTO
     */
    public List<FlightDTO> searchFlights(SearchCriteria criteria) {
        validationUtils.validateSearchCriteria(criteria);
        // TODO: Integrate with airline APIs. Here, return mock data for demo.
        List<FlightDTO> flights = new ArrayList<>();
        FlightDTO flight = new FlightDTO();
        flight.setFlightId("UA123");
        flight.setPrice(350.00);
        flight.setDuration("6h");
        flight.setAirline("United");
        flights.add(flight);
        return flights;
    }

    /**
     * Gets flight details by flightId.
     * @param flightId String
     * @return FlightDTO
     */
    public FlightDTO getFlightDetails(String flightId) {
        // TODO: Integrate with airline APIs. Here, return mock data for demo.
        FlightDTO flight = new FlightDTO();
        flight.setFlightId(flightId);
        flight.setPrice(350.00);
        flight.setDuration("6h");
        flight.setAirline("United");
        // Add more details as needed
        return flight;
    }
}
