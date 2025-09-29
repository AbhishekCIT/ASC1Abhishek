package com.example.flightsearch.service;

import com.example.flightsearch.client.ExternalFlightAPIClient;
import com.example.flightsearch.exception.InvalidAirportCodeException;
import com.example.flightsearch.exception.InvalidDateException;
import com.example.flightsearch.exception.InvalidPassengerCountException;
import com.example.flightsearch.model.Flight;
import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Service for flight search business logic and validations.
 */
@Service
public class FlightSearchService {
    private static final Logger logger = LoggerFactory.getLogger(FlightSearchService.class);

    @Autowired
    private ExternalFlightAPIClient externalFlightAPIClient;

    /**
     * Finds flights matching the given criteria.
     * @param request Search criteria
     * @return Response containing list of flights
     */
    public FlightSearchResponse findFlights(FlightSearchRequest request) {
        validateRequest(request);
        List<Flight> flights = externalFlightAPIClient.fetchFlights(request);
        return new FlightSearchResponse(flights);
    }

    /**
     * Validates the search request fields.
     * @param request Search criteria
     */
    private void validateRequest(FlightSearchRequest request) {
        if (!isValidIATACode(request.getOrigin())) {
            logger.error("Invalid origin airport code: {}", request.getOrigin());
            throw new InvalidAirportCodeException("Invalid origin airport code");
        }
        if (!isValidIATACode(request.getDestination())) {
            logger.error("Invalid destination airport code: {}", request.getDestination());
            throw new InvalidAirportCodeException("Invalid destination airport code");
        }
        try {
            LocalDate date = LocalDate.parse(request.getDate());
            if (date.isBefore(LocalDate.now())) {
                logger.error("Date cannot be in the past: {}", request.getDate());
                throw new InvalidDateException("Date cannot be in the past");
            }
        } catch (DateTimeParseException e) {
            logger.error("Invalid date format: {}", request.getDate());
            throw new InvalidDateException("Invalid date format");
        }
        if (request.getPassengers() == null || request.getPassengers() <= 0) {
            logger.error("Number of passengers must be positive: {}", request.getPassengers());
            throw new InvalidPassengerCountException("Number of passengers must be positive");
        }
    }

    /**
     * Checks if the provided airport code is a valid IATA code (3 uppercase letters).
     * @param code Airport code
     * @return true if valid
     */
    private boolean isValidIATACode(String code) {
        return code != null && code.matches("^[A-Z]{3}$");
    }
}
