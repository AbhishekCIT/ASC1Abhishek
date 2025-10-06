package com.example.airbooking.util;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Utility class for airport code validation.
 */
@Component
public class AirportCodeUtil {
    private static final Pattern IATA_CODE_PATTERN = Pattern.compile("^[A-Z]{3}$");

    /**
     * Checks if the airport code is valid (IATA 3-letter code).
     * @param code Airport code
     * @return true if valid, false otherwise
     */
    public boolean isValidAirportCode(String code) {
        return code != null && IATA_CODE_PATTERN.matcher(code).matches();
    }
}