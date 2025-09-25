package com.example.scheduler.util;

import org.springframework.stereotype.Component;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility for time format validation.
 */
@Component
public class TimeUtil {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Validates if the time string is in HH:mm format and within allowed range (e.g., 00:00 to 23:59).
     */
    public static boolean isValidTime(String time) {
        try {
            LocalTime.parse(time, TIME_FORMATTER);
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }
}
