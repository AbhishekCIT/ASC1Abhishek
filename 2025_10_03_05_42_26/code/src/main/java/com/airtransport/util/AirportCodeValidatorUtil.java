package com.airtransport.util;

import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

/**
 * Utility for validating airport codes (IATA standard).
 */
@Component
public class AirportCodeValidatorUtil {
    // Example valid codes; in production, use a full list or external service
    private static final List<String> VALID_CODES = Arrays.asList("JFK", "LAX", "SFO", "ORD", "ATL", "DFW");

    public static boolean isValidAirportCode(String code) {
        return code != null && VALID_CODES.contains(code.toUpperCase());
    }
}
