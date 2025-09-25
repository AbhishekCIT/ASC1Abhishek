package com.example.scheduler.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for EmailService.
 */
class EmailServiceTest {
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        emailService = new EmailService();
    }

    @Test
    @DisplayName("Send report - no exception for valid input")
    void testSendReportNoException() {
        byte[] reportFile = new byte[]{1, 2, 3};
        List<String> recipients = Arrays.asList("user1@abc.com", "user2@abc.com");
        assertDoesNotThrow(() -> emailService.sendReport(reportFile, recipients));
    }

    @Test
    @DisplayName("Validate recipients - valid list")
    void testValidateRecipientsValid() {
        List<String> recipients = Arrays.asList("user1@abc.com", "user2@abc.com");
        assertTrue(emailService.validateRecipients(recipients));
    }

    @Test
    @DisplayName("Validate recipients - empty list")
    void testValidateRecipientsEmpty() {
        List<String> recipients = Collections.emptyList();
        assertFalse(emailService.validateRecipients(recipients));
    }

    @Test
    @DisplayName("Validate recipients - null list")
    void testValidateRecipientsNull() {
        assertFalse(emailService.validateRecipients(null));
    }
}
