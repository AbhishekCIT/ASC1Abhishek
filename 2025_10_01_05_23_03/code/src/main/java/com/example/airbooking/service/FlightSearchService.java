package com.example.airbooking.service;

import com.example.airbooking.dto.FlightSearchRequest;
import com.example.airbooking.dto.FlightSearchResponse;
import com.example.airbooking.entity.Flight;
import com.example.airbooking.entity.Seat;
import com.example.airbooking.exception.FlightNotFoundException;
import com.example.airbooking.repository.FlightRepository;
import com.example.airbooking.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for searching available flights.
 */
@Service
public class FlightSearchService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private SeatRepository seatRepository;

    /**
     * Searches for available flights based on search criteria.
     * @param request Flight search request
     * @return List of available flights
     */
    public List<FlightSearchResponse> searchFlights(FlightSearchRequest request) {
        // Validate input
        if (request.getOrigin() == null || request.getOrigin().isEmpty()) {
            throw new FlightNotFoundException("Origin field cannot be empty");
        }
        if (request.getDestination() == null || request.getDestination().isEmpty()) {
            throw new FlightNotFoundException("Destination field cannot be empty");
        }
        LocalDate departureDate = LocalDate.parse(request.getDepartureDate());
        List<Flight> flights = flightRepository.findByOriginAndDestinationAndDepartureDate(
                request.getOrigin(), request.getDestination(), departureDate);
        if (flights.isEmpty()) {
            throw new FlightNotFoundException("No flights found for the given criteria");
        }
        List<FlightSearchResponse> responses = new ArrayList<>();
        for (Flight flight : flights) {
            List<Seat> availableSeats = seatRepository.findByFlightAndAvailableTrue(flight);
            FlightSearchResponse resp = new FlightSearchResponse();
            resp.setFlightId(flight.getId());
            resp.setAirline("Delta"); // Placeholder, should come from flight data
            resp.setPrice(flight.getPrice());
            resp.setSeatsAvailable(availableSeats.size());
            resp.setSeatClass("Economy"); // Placeholder, can be enhanced
            responses.add(resp);
        }
        return responses;
    }
}
