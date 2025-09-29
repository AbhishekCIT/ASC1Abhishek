package com.example.airbooking.service;

import com.example.airbooking.model.Booking;
import org.springframework.stereotype.Service;

/**
 * Service for sending booking confirmation emails.
 */
@Service
public class EmailService {
    /**
     * Sends booking confirmation email to the passenger.
     * @param email recipient email
     * @param booking booking details
     */
    public void sendBookingConfirmation(String email, Booking booking) {
        // Simulate sending email (replace with real integration)
        System.out.println("Booking confirmation sent to " + email + " for booking ID: " + booking.getId());
    }
}
