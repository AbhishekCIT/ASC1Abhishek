package com.airtransport.util;

import org.springframework.stereotype.Component;
import java.util.Set;

/**
 * Utility for validating IATA airport codes.
 */
@Component
public class IATACodeUtil {
    private static final Set<String> VALID_CODES = Set.of("JFK", "LAX", "SFO", "ORD", "ATL", "DFW", "DEN", "SEA", "MIA", "BOS");
    /**
     * Checks if the provided code is a valid IATA airport code.
     * @param code IATA code
     * @return true if valid
     */
    public boolean isValidIATACode(String code) {
        return code != null && VALID_CODES.contains(code.toUpperCase());
    }
}
