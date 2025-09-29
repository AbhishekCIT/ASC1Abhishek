package com.airbook.util;

import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

/**
 * Utility for airport code validation
 */
@Component
public class AirportCodeUtil {
    // Example list of valid airport codes (expand as needed)
    private static final List<String> VALID_CODES = Arrays.asList("JFK", "LAX", "SFO", "ORD", "ATL", "DFW", "DEN");

    /**
     * Check if airport code is valid
     * @param code Airport code
     * @return true if valid
     */
    public boolean isValidAirportCode(String code) {
        return code != null && VALID_CODES.contains(code.toUpperCase());
    }
}
