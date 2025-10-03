package com.airline.flightbooking.service;

import com.airline.flightbooking.exception.InvalidDateException;
import com.airline.flightbooking.model.Flight;
import com.airline.flightbooking.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service for searching available flights.
 */
@Service
@RequiredArgsConstructor
public class FlightSearchService {
    private final FlightRepository flightRepository;

    /**
     * Search for available flights by origin, destination, and date.
     * @param origin airport code
     * @param destination airport code
     * @param date travel date
     * @return list of available flights
     * @throws InvalidDateException if date is in the past
     */
    public List<Flight> searchFlights(String origin, String destination, LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
            throw new InvalidDateException("Invalid travel date: must not be in the past");
        }
        return flightRepository.findByOriginAndDestinationAndDate(origin, destination, date);
    }
}
