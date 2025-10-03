package com.example.airbooking.service;

import com.example.airbooking.entity.Flight;
import com.example.airbooking.exception.InvalidInputException;
import com.example.airbooking.model.FlightSearchRequest;
import com.example.airbooking.model.FlightSearchResponse;
import com.example.airbooking.repository.FlightRepository;
import com.example.airbooking.util.AirlineApiClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for searching flights.
 */
@Service
@RequiredArgsConstructor
public class FlightSearchService {
    private final FlightRepository flightRepository;
    private final AirlineApiClientUtil airlineApiClientUtil;

    /**
     * Search for available flights based on request criteria.
     */
    @Transactional(readOnly = true)
    public List<FlightSearchResponse> searchFlights(FlightSearchRequest request) {
        if (request.getDate() == null || request.getDate().isBefore(java.time.LocalDate.now())) {
            throw new InvalidInputException("Invalid date");
        }
        if (request.getFrom() == null || request.getTo() == null || request.getFrom().length() != 3 || request.getTo().length() != 3) {
            throw new InvalidInputException("Invalid location");
        }
        // Fetch from local DB for demo, in production use airlineApiClientUtil
        LocalDateTime start = request.getDate().atStartOfDay();
        LocalDateTime end = start.plusDays(1);
        List<Flight> flights = flightRepository.findByFromAirportAndToAirportAndDepartureBetweenAndSeatsAvailableGreaterThan(
                request.getFrom(), request.getTo(), start, end, 0);
        return flights.stream().map(flight -> FlightSearchResponse.builder()
                .flightId(flight.getId())
                .airline(flight.getAirline())
                .price(flight.getPrice())
                .seatsAvailable(flight.getSeatsAvailable())
                .departure(flight.getDeparture().toString())
                .arrival(flight.getArrival().toString())
                .build()).collect(Collectors.toList());
    }
}
