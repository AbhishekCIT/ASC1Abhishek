package com.airtransport.util;

import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility service for validating IATA airport codes.
 */
@Component
public class AirportValidationService {
    // Example set of valid IATA codes. In production, fetch from DB or config.
    private static final Set<String> VALID_CODES = new HashSet<>(Arrays.asList("JFK", "LAX", "ATL", "ORD", "DFW", "DEN", "SFO", "SEA", "MIA", "BOS"));

    /**
     * Checks if the provided airport code is a valid IATA code.
     * @param code IATA airport code
     * @return true if valid, false otherwise
     */
    public boolean isValidAirportCode(String code) {
        return code != null && VALID_CODES.contains(code.toUpperCase());
    }
}
