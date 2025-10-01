package com.airtransport.booking.service;

import com.airtransport.booking.entity.Flight;
import com.airtransport.booking.model.FlightSearchRequest;
import com.airtransport.booking.model.FlightSearchResponse;
import com.airtransport.booking.repository.FlightRepository;
import com.airtransport.booking.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final ValidationUtil validationUtil;

    /**
     * Searches for available flights based on criteria.
     * Validates input and throws IllegalArgumentException on error.
     */
    @Cacheable("flights")
    public List<FlightSearchResponse> searchFlights(FlightSearchRequest request) {
        if (!StringUtils.hasText(request.getOrigin()) || !validationUtil.isValidAirportCode(request.getOrigin())) {
            throw new IllegalArgumentException("Invalid origin code");
        }
        if (!StringUtils.hasText(request.getDestination()) || !validationUtil.isValidAirportCode(request.getDestination())) {
            throw new IllegalArgumentException("Invalid destination code");
        }
        if (!validationUtil.isFutureDate(request.getDate())) {
            throw new IllegalArgumentException("Travel date must be in the future");
        }
        LocalDateTime start = request.getDate().atStartOfDay();
        LocalDateTime end = request.getDate().atTime(23, 59, 59);
        List<Flight> flights = flightRepository.findByOriginAndDestinationAndDepartureBetween(
                request.getOrigin(), request.getDestination(), start, end);
        return flights.stream().map(flight -> {
            FlightSearchResponse resp = new FlightSearchResponse();
            resp.setFlightId(flight.getId());
            resp.setAirline(flight.getAirline());
            resp.setDeparture(flight.getDeparture().toString());
            resp.setArrival(flight.getArrival().toString());
            resp.setPrice(flight.getPrice());
            return resp;
        }).collect(Collectors.toList());
    }
}
