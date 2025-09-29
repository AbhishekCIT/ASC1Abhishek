package com.example.airtransport.util;

import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility for validating and parsing dates.
 */
@Component
public class DateUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static boolean isValidFutureDate(String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr, FORMATTER);
            return date.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
