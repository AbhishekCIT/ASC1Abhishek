package com.example.airbooking.service;

import com.example.airbooking.model.FlightStatus;
import com.example.airbooking.repository.FlightStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for flight status queries and updates.
 */
@Service
public class FlightStatusService {
    @Autowired
    private FlightStatusRepository flightStatusRepository;

    /**
     * Gets the latest flight status by flight number.
     * @param flightNumber Flight number
     * @return FlightStatus
     */
    public FlightStatus getFlightStatusByFlightNumber(String flightNumber) {
        if (flightNumber == null || flightNumber.isEmpty()) {
            throw new IllegalArgumentException("Invalid flight number.");
        }
        List<FlightStatus> statuses = flightStatusRepository.findByFlightNumberOrderByUpdatedAtDesc(flightNumber);
        if (statuses == null || statuses.isEmpty()) {
            throw new RuntimeException("Flight not found.");
        }
        return statuses.get(0);
    }
}