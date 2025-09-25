package com.example.scheduler.util;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

/**
 * Utility class for validating email addresses.
 */
@Component
public class EmailValidatorUtil {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    /**
     * Validate email address format.
     */
    public boolean isValid(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}
