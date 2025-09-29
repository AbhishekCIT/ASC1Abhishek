package com.example.airline.service;

import com.example.airline.dto.FlightSearchRequest;
import com.example.airline.dto.FlightSearchResponse;
import com.example.airline.model.Flight;
import com.example.airline.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightSearchService {
    @Autowired
    private FlightRepository flightRepository;

    /**
     * Searches for flights based on origin, destination, and date.
     * @param request Flight search criteria
     * @return List of matching flights
     */
    public List<FlightSearchResponse> searchFlights(FlightSearchRequest request) {
        // Validate input
        if (request.getOrigin() == null || request.getOrigin().isEmpty()) {
            throw new IllegalArgumentException("Origin cannot be empty");
        }
        if (request.getDestination() == null || request.getDestination().isEmpty()) {
            throw new IllegalArgumentException("Destination cannot be empty");
        }
        LocalDate date = LocalDate.parse(request.getDate(), DateTimeFormatter.ISO_DATE);
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Date must be in the future");
        }
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        List<Flight> flights = flightRepository.findByOriginAndDestinationAndDepartureTimeBetween(
                request.getOrigin(), request.getDestination(), start, end);
        return flights.stream().map(f -> {
            FlightSearchResponse resp = new FlightSearchResponse();
            resp.setFlightId(f.getFlightId());
            resp.setPrice(f.getPrice());
            resp.setDepartureTime(f.getDepartureTime().toString());
            resp.setArrivalTime(f.getArrivalTime().toString());
            return resp;
        }).collect(Collectors.toList());
    }
}