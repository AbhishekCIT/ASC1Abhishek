package com.airtransport.service;

import org.springframework.stereotype.Service;

/**
 * Service for sending booking confirmations and notifications.
 */
@Service
public class NotificationService {
    /**
     * Send booking confirmation to the user (mocked).
     * @param bookingId Booking identifier
     * @param userContact User's email or phone
     */
    public void sendBookingConfirmation(String bookingId, String userContact) {
        // TODO: Integrate with real notification/email service
        System.out.println("Booking confirmation sent to " + userContact + " for booking " + bookingId);
    }
}
