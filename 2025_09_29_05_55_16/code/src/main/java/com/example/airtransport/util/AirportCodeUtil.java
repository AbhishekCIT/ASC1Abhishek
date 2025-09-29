package com.example.airtransport.util;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

/**
 * Utility for validating airport codes.
 */
@Component
public class AirportCodeUtil {
    private static final Pattern AIRPORT_CODE_PATTERN = Pattern.compile("^[A-Z]{3}$");

    public static boolean isValid(String code) {
        return code != null && AIRPORT_CODE_PATTERN.matcher(code).matches();
    }
}
