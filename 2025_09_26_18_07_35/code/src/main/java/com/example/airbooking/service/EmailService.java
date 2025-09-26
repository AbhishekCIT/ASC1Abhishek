package com.example.airbooking.service;

import com.example.airbooking.entity.Booking;
import org.springframework.stereotype.Service;

/**
 * Service for sending booking confirmation emails.
 */
@Service
public class EmailService {
    /**
     * Sends booking confirmation email.
     * @param booking Booking
     */
    public void sendConfirmationEmail(Booking booking) {
        // TODO: Integrate with email provider. Here, just log/send mock email.
        System.out.println("Confirmation email sent for bookingRef: " + booking.getBookingRef());
    }
}
