package com.airtransport.service;

import com.airtransport.model.Booking;
import org.springframework.stereotype.Service;

/**
 * Service for sending emails, e.g., booking confirmations.
 */
@Service
public class EmailService {
    /**
     * Send booking confirmation email to user.
     * @param booking Booking details
     * @param email Recipient email address
     */
    public void sendBookingConfirmation(Booking booking, String email) {
        // In real implementation, integrate with email gateway
        // Here, simulate email sending
        System.out.println("Confirmation email sent to " + email + " for booking " + booking.getBookingId());
    }
}
