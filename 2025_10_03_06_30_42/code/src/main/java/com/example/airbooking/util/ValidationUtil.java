package com.example.airbooking.util;

import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Utility class for common validations.
 */
@Component
public class ValidationUtil {
    private static final Pattern IATA_PATTERN = Pattern.compile("^[A-Z]{3}$");

    /**
     * Validates IATA airport code.
     */
    public boolean isValidIataCode(String code) {
        return code != null && IATA_PATTERN.matcher(code).matches();
    }

    /**
     * Validates that the travel date is not in the past.
     */
    public boolean isValidTravelDate(LocalDate date) {
        return date != null && !date.isBefore(LocalDate.now());
    }
}
