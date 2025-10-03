package com.airtransport.service;

import org.springframework.stereotype.Service;

/**
 * Service to validate origin and destination locations.
 */
@Service
public class LocationService {
    /**
     * Validates a location code (e.g., IATA airport code).
     * @param location Location code
     * @return true if valid, false otherwise
     */
    public boolean validateLocation(String location) {
        // In real implementation, check against geo-location service or database
        return location != null && location.matches("^[A-Z]{3}$");
    }
}
