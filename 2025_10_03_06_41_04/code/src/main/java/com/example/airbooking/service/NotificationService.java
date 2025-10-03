package com.example.airbooking.service;

import com.example.airbooking.model.Booking;
import org.springframework.stereotype.Service;

/**
 * Service for sending booking confirmation notifications.
 */
@Service
public class NotificationService {
    /**
     * Send booking confirmation via email/SMS (stub implementation).
     */
    public void sendConfirmation(Booking booking) {
        // TODO: Integrate with email/SMS provider
        System.out.println("Confirmation sent for booking ref: " + booking.getBookingRef());
    }
}
