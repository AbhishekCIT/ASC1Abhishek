package com.airtransport.service;

import com.airtransport.client.ExternalFlightAPIClient;
import com.airtransport.exception.InvalidInputException;
import com.airtransport.exception.NoFlightsFoundException;
import com.airtransport.model.FlightSearchRequest;
import com.airtransport.model.FlightSearchResponse;
import com.airtransport.util.IATACodeUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Service for managing flight search logic and integration with external APIs.
 */
@Service
@RequiredArgsConstructor
public class FlightSearchService {
    private static final Logger logger = LoggerFactory.getLogger(FlightSearchService.class);
    private final ExternalFlightAPIClient externalFlightAPIClient;
    private final IATACodeUtil iataCodeUtil;

    /**
     * Search for available flights using criteria.
     * @param request Flight search request
     * @return Flight search response
     */
    @Cacheable(value = "flightSearchCache", key = "#request.origin + '-' + #request.destination + '-' + #request.date + '-' + #request.passengers")
    public FlightSearchResponse searchFlights(@Valid FlightSearchRequest request) {
        validateRequest(request);
        FlightSearchResponse response = externalFlightAPIClient.fetchAvailableFlights(request);
        if (response == null || response.getFlights().isEmpty()) {
            logger.warn("No flights found for {} to {} on {}", request.getOrigin(), request.getDestination(), request.getDate());
            throw new NoFlightsFoundException("No flights available for given criteria");
        }
        return response;
    }

    /**
     * Validates the flight search request.
     * @param request Flight search request
     */
    private void validateRequest(FlightSearchRequest request) {
        if (!iataCodeUtil.isValidIATACode(request.getOrigin())) {
            throw new InvalidInputException("Invalid origin airport code");
        }
        if (!iataCodeUtil.isValidIATACode(request.getDestination())) {
            throw new InvalidInputException("Invalid destination airport code");
        }
        try {
            LocalDate date = LocalDate.parse(request.getDate());
            if (date.isBefore(LocalDate.now())) {
                throw new InvalidInputException("Invalid or past travel date");
            }
        } catch (DateTimeParseException ex) {
            throw new InvalidInputException("Invalid or past travel date");
        }
    }
}
