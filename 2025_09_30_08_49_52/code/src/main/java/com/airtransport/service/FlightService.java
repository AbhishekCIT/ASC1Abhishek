package com.airtransport.service;

import com.airtransport.entity.Flight;
import com.airtransport.model.FlightDTO;
import com.airtransport.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for flight search business logic.
 */
@Service
public class FlightService {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    /**
     * Search for available flights based on origin, destination, date, and class.
     * @param origin origin airport code
     * @param destination destination airport code
     * @param date date of departure
     * @param flightClass class (Economy, Business, etc.)
     * @return list of available flights
     */
    public List<FlightDTO> searchFlights(String origin, String destination, LocalDate date, String flightClass) {
        // Validation: Origin and destination cannot be the same
        if (origin.equalsIgnoreCase(destination)) {
            throw new IllegalArgumentException("Origin and destination cannot be the same");
        }
        // Validation: Date must be in the future
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Dates must be in the future");
        }
        // For simplicity, assuming origin and destination are part of airline string or filter logic
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        List<Flight> flights = flightRepository.findAll().stream()
                .filter(f -> f.getDeparture().isAfter(start) && f.getDeparture().isBefore(end))
                .filter(f -> f.getFlightClass().equalsIgnoreCase(flightClass))
                .filter(f -> f.getAirline().toLowerCase().contains(origin.toLowerCase()) || f.getAirline().toLowerCase().contains(destination.toLowerCase()))
                .collect(Collectors.toList());
        return flights.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private FlightDTO toDTO(Flight flight) {
        FlightDTO dto = new FlightDTO();
        dto.setFlightId(flight.getFlightId());
        dto.setAirline(flight.getAirline());
        dto.setDeparture(flight.getDeparture());
        dto.setArrival(flight.getArrival());
        dto.setPrice(flight.getPrice());
        dto.setFlightClass(flight.getFlightClass());
        return dto;
    }
}
