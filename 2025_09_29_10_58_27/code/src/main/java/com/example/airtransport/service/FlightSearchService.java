package com.example.airtransport.service;

import com.example.airtransport.model.FlightDTO;
import com.example.airtransport.model.FlightSearchRequest;
import com.example.airtransport.util.DateUtil;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to handle flight search logic and validation.
 */
@Service
@RequiredArgsConstructor
public class FlightSearchService {
    private final AirlineAPIService airlineAPIService;

    /**
     * Search for flights based on origin, destination, and date.
     * Validates input and delegates to AirlineAPIService.
     */
    @Cacheable("flights")
    public List<FlightDTO> searchFlights(FlightSearchRequest request) {
        if (request.getOrigin() == null || request.getOrigin().isEmpty()) {
            throw new ValidationException("Origin cannot be empty");
        }
        if (request.getDestination() == null || request.getDestination().isEmpty()) {
            throw new ValidationException("Destination cannot be empty");
        }
        if (!DateUtil.isFutureDate(request.getDate())) {
            throw new ValidationException("Date must be in the future");
        }
        return airlineAPIService.queryFlights(request);
    }
}
