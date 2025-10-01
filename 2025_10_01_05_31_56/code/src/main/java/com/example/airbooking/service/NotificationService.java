package com.example.airbooking.service;

import com.example.airbooking.model.Booking;
import org.springframework.stereotype.Service;

/**
 * Service for sending booking confirmations via email/SMS.
 * (Stub implementation; integration with actual email/SMS gateway required.)
 */
@Service
public class NotificationService {

    /**
     * Send booking confirmation to the user.
     * @param booking The booking for which confirmation is to be sent.
     */
    public void sendConfirmation(Booking booking) {
        // TODO: Integrate with email/SMS gateway
        System.out.println("Confirmation sent for booking ID: " + booking.getBookingId());
    }
}
