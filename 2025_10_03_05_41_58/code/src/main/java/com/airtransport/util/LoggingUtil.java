package com.airtransport.util;

import org.springframework.stereotype.Component;

/**
 * Utility class for logging-related helper methods.
 */
@Component
public class LoggingUtil {
    /**
     * Masks sensitive data in a string (e.g., card numbers).
     * @param data The original string.
     * @return The masked string.
     */
    public String maskSensitiveData(String data) {
        if (data == null || data.length() < 4) return "****";
        int unmasked = 4;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length() - unmasked; i++) {
            sb.append("*");
        }
        sb.append(data.substring(data.length() - unmasked));
        return sb.toString();
    }
}
