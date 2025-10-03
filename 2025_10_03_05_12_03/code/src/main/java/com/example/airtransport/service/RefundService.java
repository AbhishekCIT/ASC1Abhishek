package com.example.airtransport.service;

import com.example.airtransport.model.Booking;
import org.springframework.stereotype.Service;

/**
 * Service for refund transaction with Payment Gateway API.
 */
@Service
public class RefundService {
    /**
     * Processes refund for the booking and amount.
     * @param booking Booking object
     * @param amount Refund amount
     * @return true if refund is successful, false otherwise
     */
    public boolean processRefund(Booking booking, double amount) {
        // Simulate refund gateway integration (should call real API in production)
        // For demo, always return true if amount > 0
        return booking != null && amount > 0;
    }
}
