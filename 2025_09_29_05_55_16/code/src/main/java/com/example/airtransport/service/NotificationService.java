package com.example.airtransport.service;

import com.example.airtransport.model.Booking;
import org.springframework.stereotype.Service;

/**
 * Service for sending confirmation notifications (Email, In-app).
 */
@Service
public class NotificationService {

    /**
     * Send booking confirmation to the user via email and in-app notification.
     * @param booking Booking details.
     */
    public void sendConfirmation(Booking booking) {
        // TODO: Integrate with Email and Notification APIs
        // Stub: Print confirmation to console
        System.out.println("Confirmation sent for booking: " + booking.getBookingId());
    }
}
