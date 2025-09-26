package com.example.airbooking.util;

import com.example.airbooking.model.Booking;
import org.springframework.stereotype.Component;

/**
 * Utility class to send booking confirmations via email/SMS.
 */
@Component
public class NotificationService {
    /**
     * Send booking confirmation to the user. (Stub implementation)
     */
    public void sendConfirmation(Booking booking, String email) {
        // TODO: Integrate with actual notification service
        // Stub: Print confirmation to console
        System.out.println("Confirmation sent to " + email + " for booking " + booking.getBookingId());
    }
}
