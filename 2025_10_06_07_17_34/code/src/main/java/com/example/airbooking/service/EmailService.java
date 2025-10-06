package com.example.airbooking.service;

import org.springframework.stereotype.Service;

/**
 * Service for sending confirmation emails and e-tickets.
 */
@Service
public class EmailService {
    /**
     * Sends booking confirmation and e-ticket to the user via email.
     * @param bookingId Booking ID
     * @param email User's email address
     */
    public void sendConfirmationEmail(String bookingId, String email) {
        // TODO: Integrate with actual email service provider
        // For now, simulate sending email
        System.out.println("Confirmation email sent to " + email + " for booking " + bookingId);
    }
}