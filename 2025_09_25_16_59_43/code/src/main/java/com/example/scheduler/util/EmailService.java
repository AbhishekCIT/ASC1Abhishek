package com.example.scheduler.util;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

/**
 * Utility class for email delivery and validation
 */
@Component
public class EmailService {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    /**
     * Validate email format
     */
    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Send report to recipients
     * @param recipients array of recipient emails
     * @param reportData byte array of report file
     * @return true if delivery successful
     */
    public boolean sendReport(String[] recipients, byte[] reportData) {
        // TODO: Integrate with SendGrid API for actual delivery
        // For now, simulate success
        return true;
    }
}
