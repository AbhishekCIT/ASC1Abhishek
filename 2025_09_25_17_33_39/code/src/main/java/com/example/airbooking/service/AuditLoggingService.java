package com.example.airbooking.service;

import com.example.airbooking.model.Booking;
import com.example.airbooking.model.Payment;
import org.springframework.stereotype.Service;

/**
 * AuditLoggingService logs all booking and payment transactions.
 */
@Service
public class AuditLoggingService {
    /**
     * Logs a booking and payment transaction.
     * @param booking the booking entity
     * @param payment the payment entity
     * @param username the user performing the transaction
     */
    public void logTransaction(Booking booking, Payment payment, String username) {
        // TODO: Integrate with real audit logging system
        System.out.println("AUDIT LOG: User=" + username + ", BookingID=" + booking.getId() + ", PaymentID=" + payment.getId() + ", Status=" + booking.getStatus());
    }
}
