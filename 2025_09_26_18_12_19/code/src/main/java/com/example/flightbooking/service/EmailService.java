package com.example.flightbooking.service;

import com.example.flightbooking.model.Booking;
import org.springframework.stereotype.Service;

/**
 * Service for sending confirmation emails to users.
 */
@Service
public class EmailService {
    /**
     * Send booking confirmation email to user.
     * @param booking Booking object
     */
    public void sendConfirmationEmail(Booking booking) {
        // Simulate sending email
        // In reality, integrate with an email provider (e.g., SendGrid, SES)
        System.out.println("Confirmation email sent to user: " + booking.getUserId() + " for booking: " + booking.getBookingId());
    }
}
