package com.example.airtransport.service;

import com.example.airtransport.entity.Flight;
import com.example.airtransport.model.FlightSearchRequest;
import com.example.airtransport.model.FlightSearchResponse;
import com.example.airtransport.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for flight search business logic and validation.
 */
@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;

    /**
     * Searches for available flights based on search criteria.
     * @param request Flight search request
     * @return List of available flights
     */
    @Cacheable("flights")
    public List<FlightSearchResponse> searchFlights(FlightSearchRequest request) {
        validateSearchRequest(request);
        LocalDateTime start = request.getDate().atStartOfDay();
        LocalDateTime end = request.getDate().atTime(23, 59, 59);
        List<Flight> flights = flightRepository.findByDestinationAndDepartureTimeBetweenAndFlightClass(
                request.getDestination(), start, end, request.getFlightClass()
        );
        return flights.stream().map(flight -> FlightSearchResponse.builder()
                .flightId(flight.getFlightId())
                .airline(flight.getAirline())
                .origin(flight.getOrigin())
                .destination(flight.getDestination())
                .departure(flight.getDepartureTime())
                .arrival(flight.getArrivalTime())
                .price(flight.getBaseFare())
                .flightClass(flight.getFlightClass())
                .build()
        ).collect(Collectors.toList());
    }

    /**
     * Validates the flight search request.
     * @param request FlightSearchRequest
     */
    private void validateSearchRequest(FlightSearchRequest request) {
        if (request == null || !StringUtils.hasText(request.getDestination())) {
            throw new IllegalArgumentException("Invalid destination");
        }
        if (request.getDate() == null || request.getDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid date");
        }
        if (!StringUtils.hasText(request.getFlightClass()) ||
                !(request.getFlightClass().equalsIgnoreCase("Economy") || request.getFlightClass().equalsIgnoreCase("Business"))) {
            throw new IllegalArgumentException("Invalid class");
        }
    }
}
