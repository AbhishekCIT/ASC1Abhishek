package com.asc.airbooking.util;

import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.HashSet;

/**
 * Utility for validating airport codes.
 */
@Component
public class AirportCodeUtil {
    private static final Set<String> VALID_CODES = new HashSet<>();

    public AirportCodeUtil() {
        // Example: Add some valid codes. In production, fetch from DB or config.
        VALID_CODES.add("JFK");
        VALID_CODES.add("LAX");
        VALID_CODES.add("SFO");
        VALID_CODES.add("ORD");
        VALID_CODES.add("ATL");
    }

    /**
     * Checks if the airport code is valid.
     * @param code Airport code
     * @return true if valid, false otherwise
     */
    public boolean isValidAirportCode(String code) {
        return VALID_CODES.contains(code);
    }
}
