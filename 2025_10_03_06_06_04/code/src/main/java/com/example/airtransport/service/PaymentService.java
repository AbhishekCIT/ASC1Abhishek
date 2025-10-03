package com.example.airtransport.service;

import com.example.airtransport.model.PaymentInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service for payment processing logic.
 */
@Service
@Slf4j
public class PaymentService {
    /**
     * Processes payment for a booking. (Simulated for demo)
     * @param bookingId Booking ID
     * @param paymentInfo Payment information
     * @return true if payment successful, false otherwise
     */
    public boolean processPayment(String bookingId, PaymentInfo paymentInfo) {
        // Validate payment info (PCI DSS, expiry, etc.)
        if (!isValidPaymentInfo(paymentInfo)) {
            log.error("Invalid payment information for booking {}", bookingId);
            return false;
        }
        // Simulate payment gateway integration
        log.info("Processing payment for booking {}: amount={} {}", bookingId, paymentInfo.getAmount(), paymentInfo.getCurrency());
        // Assume payment is always successful in this demo
        return true;
    }

    /**
     * Validates payment information.
     * @param paymentInfo PaymentInfo
     * @return true if valid, false otherwise
     */
    private boolean isValidPaymentInfo(PaymentInfo paymentInfo) {
        // Basic validation for demo
        return paymentInfo != null &&
                paymentInfo.getCardNumber() != null && paymentInfo.getCardNumber().length() >= 12 &&
                paymentInfo.getExpiryDate() != null && paymentInfo.getCvv() != null;
    }
}
