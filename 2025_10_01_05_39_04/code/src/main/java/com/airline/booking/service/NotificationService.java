package com.airline.booking.service;

import com.airline.booking.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for sending notifications (e.g., email).
 */
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final EmailUtil emailUtil;

    /**
     * Send booking confirmation email.
     * @param to recipient email
     * @param subject subject
     * @param body body
     */
    public void sendBookingConfirmation(String to, String subject, String body) {
        emailUtil.sendEmail(to, subject, body);
    }
}
