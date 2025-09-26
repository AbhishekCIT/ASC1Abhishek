package com.airtransport.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * DateUtil provides utility methods for date validation.
 */
public class DateUtil {
    /**
     * Checks if the given date string (yyyy-MM-dd) is a valid future date.
     */
    public static boolean isFutureDate(String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            return date.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
