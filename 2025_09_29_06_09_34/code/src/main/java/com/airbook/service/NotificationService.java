package com.airbook.service;

import com.airbook.model.Booking;
import org.springframework.stereotype.Service;

/**
 * Service for sending e-ticket notifications
 */
@Service
public class NotificationService {

    /**
     * Send e-ticket notification via email
     * @param booking Booking details
     */
    public void sendETicket(Booking booking) {
        // Integrate with email service provider (e.g., SendGrid)
        // For demonstration, just print to console
        System.out.println("E-ticket sent to " + booking.getPassengerEmail() + ": " + booking.getETicket());
    }
}
