package com.example.scheduler.util;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Utility for validating recipient email addresses.
 */
@Component
public class RecipientValidatorUtil {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    /**
     * Validate all emails in the list.
     */
    public boolean areValidEmails(List<String> emails) {
        if (emails == null || emails.isEmpty()) return false;
        return emails.stream().allMatch(this::isValidEmail);
    }

    /**
     * Validate a single email address.
     */
    public boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}
