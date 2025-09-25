package com.example.scheduledreporting.service;

import com.example.scheduledreporting.entity.ScheduledReport;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * Service for delivering reports via email or notification.
 */
@Service
@Slf4j
public class DeliveryService {
    private final JavaMailSender mailSender;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public DeliveryService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Deliver the report as per delivery type.
     */
    public void deliverReport(ScheduledReport report, byte[] reportContent) throws MessagingException {
        if ("EMAIL".equalsIgnoreCase(report.getDeliveryType())) {
            if (!isValidEmail(report.getDeliveryAddress())) {
                throw new IllegalArgumentException("Invalid email address");
            }
            // TODO: Implement actual email sending logic using mailSender
            log.info("Sending report {} to email {}", report.getId(), report.getDeliveryAddress());
        } else if ("NOTIFICATION".equalsIgnoreCase(report.getDeliveryType())) {
            // TODO: Implement notification delivery
            log.info("Sending notification for report {}", report.getId());
        }
    }

    /**
     * Validate email address format.
     */
    public boolean isValidEmail(String email) {
        return StringUtils.hasText(email) && EMAIL_PATTERN.matcher(email).matches();
    }
}
