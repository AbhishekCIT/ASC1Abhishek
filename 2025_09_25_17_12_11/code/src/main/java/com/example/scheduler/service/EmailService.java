package com.example.scheduler.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service for sending emails with reports.
 */
@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    /**
     * Send report as email attachment to recipients.
     */
    public void sendReport(byte[] reportFile, List<String> recipients) {
        // Simulate sending email with report attachment
        logger.info("Sending report to recipients: {}", recipients);
        // Actual implementation would use JavaMailSender or external API
    }

    /**
     * Validate recipient emails (delegated to util in service layer).
     */
    public boolean validateRecipients(List<String> recipients) {
        // This can be extended for more complex validation
        return recipients != null && !recipients.isEmpty();
    }
}
