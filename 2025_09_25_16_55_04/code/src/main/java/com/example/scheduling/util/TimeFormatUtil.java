package com.example.scheduling.util;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

/**
 * Utility class for validating time format.
 */
@Component
public class TimeFormatUtil {
    private static final Pattern TIME_24H_PATTERN = Pattern.compile("^(?:[01]\d|2[0-3]):[0-5]\d$");

    /**
     * Validates if the given time string is in 24-hour format (HH:mm).
     * @param time Time string.
     * @return true if valid, false otherwise.
     */
    public static boolean isValid24HourFormat(String time) {
        return time != null && TIME_24H_PATTERN.matcher(time).matches();
    }
}
