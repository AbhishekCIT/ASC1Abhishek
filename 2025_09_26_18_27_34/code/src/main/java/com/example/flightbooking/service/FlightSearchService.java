package com.example.flightbooking.service;

import com.example.flightbooking.model.Flight;
import com.example.flightbooking.model.SearchCriteria;
import com.example.flightbooking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service for searching available flights based on user criteria.
 */
@Service
public class FlightSearchService {

    @Autowired
    private FlightRepository flightRepository;

    /**
     * Searches for flights matching the given criteria.
     * @param criteria Search criteria
     * @return List of matching flights
     */
    public List<Flight> searchFlights(SearchCriteria criteria) {
        // Business logic: filter flights by origin, destination, dates, class, passengers
        // For demonstration, we assume repository method exists
        return flightRepository.findAvailableFlights(
                criteria.getOrigin(),
                criteria.getDestination(),
                criteria.getDepartureDate(),
                criteria.getReturnDate(),
                criteria.getFlightClass(),
                criteria.getPassengers()
        );
    }
}
