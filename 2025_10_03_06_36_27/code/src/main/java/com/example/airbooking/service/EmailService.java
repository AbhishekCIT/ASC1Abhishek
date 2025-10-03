package com.example.airbooking.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Service for sending booking confirmation and itinerary emails
 */
@Service
public class EmailService {

    /**
     * Send confirmation email asynchronously
     * @param bookingId booking identifier
     * @param email recipient email address
     */
    @Async
    public void sendConfirmationEmail(String bookingId, String email) {
        // TODO: Integrate with SMTP/Email API to send confirmation and itinerary
    }
}
