package com.example.airtransport.service;

import com.example.airtransport.model.Flight;
import com.example.airtransport.model.FlightSearchRequest;
import com.example.airtransport.repository.FlightRepository;
import com.example.airtransport.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service for flight search business logic.
 */
@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ValidationUtil validationUtil;

    /**
     * Search for available flights based on search criteria.
     * @param request Flight search request
     * @return List of available flights
     */
    public List<Flight> searchFlights(FlightSearchRequest request) {
        // Validate input
        validationUtil.validateAirportCode(request.getOrigin(), "origin");
        validationUtil.validateAirportCode(request.getDestination(), "destination");
        validationUtil.validateTravelDates(request.getDepartureDate(), request.getReturnDate());
        // Query repository for flights
        return flightRepository.findFlights(
                request.getOrigin(),
                request.getDestination(),
                request.getDepartureDate(),
                request.getReturnDate(),
                request.getPassengers()
        );
    }
}
