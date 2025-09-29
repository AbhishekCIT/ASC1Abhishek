package com.airbook.service;

import com.airbook.model.Booking;
import com.airbook.model.Refund;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Service for refund calculation and processing
 */
@Service
public class RefundService {
    /**
     * Process refund for a cancelled booking (mock)
     */
    public Refund processRefund(Booking booking) {
        Refund refund = new Refund();
        refund.setRefundId(UUID.randomUUID().toString());
        refund.setBookingId(booking.getBookingId());
        refund.setAmount(booking.getTotalPrice() * 0.8); // 80% refund policy
        refund.setStatus("PROCESSED");
        refund.setProcessedAt(LocalDateTime.now());
        return refund;
    }
}
