package com.example.scheduler.util;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

/**
 * Utility for validating email addresses.
 */
@Component
public class EmailValidatorUtil {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}
