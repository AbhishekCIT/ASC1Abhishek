package com.example.flightbooking.util;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

/**
 * Utility for validating IATA airport codes.
 */
@Component
public class AirportCodeUtil {
    private static final Pattern IATA_PATTERN = Pattern.compile("^[A-Z]{3}$");

    public static boolean isValidIATACode(String code) {
        return code != null && IATA_PATTERN.matcher(code).matches();
    }
}
