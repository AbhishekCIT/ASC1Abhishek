package com.example.airlinebooking.service;

import org.springframework.stereotype.Service;

/**
 * Service for sending notifications (email/SMS) to passengers.
 */
@Service
public class NotificationService {
    /**
     * Sends booking confirmation to the passenger.
     * @param passengerId the passenger's ID
     * @param bookingRef the booking reference
     */
    public void sendConfirmation(String passengerId, String bookingRef) {
        // Integrate with Email/SMS gateway. For demo, just log the action.
        System.out.println("Confirmation sent to passenger " + passengerId + " for booking " + bookingRef);
    }
}
