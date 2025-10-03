package com.airtransport.service;

import com.airtransport.model.Flight;
import com.airtransport.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    /**
     * Search available flights by destination and date
     * @param destination Destination airport code
     * @param date Departure date
     * @return List of flights
     */
    public List<Flight> searchFlights(String destination, LocalDateTime date) {
        if (destination == null || destination.isEmpty()) {
            throw new IllegalArgumentException("Destination is required");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date is required");
        }
        // Assuming search for flights on the given day
        LocalDateTime start = date.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime end = date.withHour(23).withMinute(59).withSecond(59);
        return flightRepository.findByDestinationAndDepartureBetween(destination, start, end);
    }
}
