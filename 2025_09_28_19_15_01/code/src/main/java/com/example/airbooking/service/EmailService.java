package com.example.airbooking.service;

import com.example.airbooking.entity.Booking;
import org.springframework.stereotype.Service;

/**
 * Service for sending confirmation and e-ticket emails.
 */
@Service
public class EmailService {
    /**
     * Send booking confirmation and e-ticket to the user's email.
     * @param booking Booking entity
     * @param email Email address
     */
    public void sendConfirmation(Booking booking, String email) {
        // TODO: Integrate with email delivery service
        // For demonstration, print to console
        System.out.println("Sending booking confirmation to " + email);
    }
}
