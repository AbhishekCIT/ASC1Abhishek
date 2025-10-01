package com.example.airlinebooking.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Utility class for common validations in the airline booking system.
 */
@Component
public class ValidationUtil {
    private static final Pattern AIRPORT_CODE_PATTERN = Pattern.compile("^[A-Z]{3}$");

    /**
     * Validates if the given date is a valid future date.
     */
    public boolean isValidFutureDate(String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr);
            return date.isAfter(LocalDate.now());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validates if the destination is a valid airport code (3 uppercase letters).
     */
    public boolean isValidDestination(String destination) {
        return AIRPORT_CODE_PATTERN.matcher(destination).matches();
    }
}
