package com.example.airbooking.service;

import com.example.airbooking.entity.Booking;
import com.example.airbooking.exception.EmailSendFailureException;
import org.springframework.stereotype.Service;

/**
 * Service for sending confirmation emails.
 * For demo, this simulates email sending.
 */
@Service
public class EmailService {
    /**
     * Sends confirmation email for booking.
     * Throws EmailSendFailureException if email fails.
     */
    public void sendConfirmationEmail(Booking booking) {
        if (booking == null || booking.getEmail() == null || booking.getEmail().isEmpty()) {
            throw new EmailSendFailureException("Email address is missing");
        }
        // Simulate email sending (integration with SMTP or 3rd party)
        // For demo, assume success
    }
}
