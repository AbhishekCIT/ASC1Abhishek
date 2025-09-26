package com.example.airbooking.util;

import com.example.airbooking.model.Booking;
import org.springframework.stereotype.Component;

/**
 * Utility for sending booking confirmation emails
 */
@Component
public class EmailService {
    /**
     * Send booking confirmation email to user
     * @param booking booking details
     */
    public void sendConfirmationEmail(Booking booking) {
        // Integrate with email service provider here
        // For demo, print to console
        System.out.println("Confirmation email sent to: " + booking.getEmail() + " for bookingRef: " + booking.getBookingRef());
    }
}
