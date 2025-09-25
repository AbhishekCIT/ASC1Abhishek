package com.example.scheduler.util;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

/**
 * Utility for email validation.
 */
@Component
public class EmailUtil {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    /**
     * Validate email address format.
     */
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}
