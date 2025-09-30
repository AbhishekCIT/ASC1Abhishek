package com.example.airlinebooking.util;

import com.example.airlinebooking.model.Booking;
import org.springframework.stereotype.Component;

/**
 * Utility for sending booking confirmation emails.
 */
@Component
public class EmailService {
    /**
     * Sends booking confirmation email to the user.
     * @param email user email address
     * @param booking booking details
     */
    public void sendConfirmation(String email, Booking booking) {
        // In real implementation, integrate with email service provider
        System.out.println("Email sent to " + email + ": Booking confirmed. Ref: " + booking.getBookingRef());
    }
}
