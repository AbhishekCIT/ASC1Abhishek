package com.example.airbooking.util;

import com.example.airbooking.model.Booking;
import org.springframework.stereotype.Component;

/**
 * Utility for sending notifications (email/SMS).
 */
@Component
public class NotificationService {
    /**
     * Sends booking confirmation to the passenger (mock implementation).
     * @param contact Contact info (email or phone)
     * @param booking Booking details
     */
    public void sendBookingConfirmation(String contact, Booking booking) {
        // TODO: Integrate with real email/SMS service
        System.out.println("Confirmation sent to " + contact + ": Booking ID " + booking.getId());
    }
}
