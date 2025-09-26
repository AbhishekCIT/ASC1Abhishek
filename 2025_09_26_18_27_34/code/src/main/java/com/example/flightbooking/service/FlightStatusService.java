package com.example.flightbooking.service;

import com.example.flightbooking.model.FlightStatus;
import com.example.flightbooking.model.Flight;
import com.example.flightbooking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for fetching and processing flight status.
 */
@Service
public class FlightStatusService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirlineApiClient airlineApiClient;

    /**
     * Get flight status by flight number or booking reference.
     */
    public FlightStatus getStatus(String flightNumber, String bookingReference) {
        // Validate input
        if ((flightNumber == null || flightNumber.isEmpty()) && (bookingReference == null || bookingReference.isEmpty())) {
            throw new IllegalArgumentException("Invalid flight number or booking reference");
        }
        // Fetch flight info from DB (if bookingReference is provided)
        if (bookingReference != null && !bookingReference.isEmpty()) {
            Flight flight = flightRepository.findByBookingReference(bookingReference);
            if (flight == null) throw new RuntimeException("Flight not found");
            flightNumber = flight.getFlightId();
        }
        // Fetch real-time status from airline API
        return airlineApiClient.fetchStatus(flightNumber);
    }
}
