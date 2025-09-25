package com.example.scheduler.util;

import org.springframework.stereotype.Component;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Utility for validating time strings.
 */
@Component
public class TimeValidatorUtil {
    public static boolean isValidFutureTime(String time) {
        try {
            LocalTime t = LocalTime.parse(time);
            return t.isAfter(LocalTime.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
