package com.airtransport.service;

import com.airtransport.model.PaymentRequest;
import com.airtransport.model.PaymentResponse;
import com.airtransport.model.PaymentInfo;
import org.springframework.stereotype.Service;

/**
 * Service for integrating with payment gateway.
 */
@Service
public class PaymentGatewayService {
    /**
     * Processes payment for a booking.
     * @param bookingId Booking ID
     * @param paymentInfo Payment details
     * @return PaymentResponse with payment status
     */
    public PaymentResponse processPayment(String bookingId, PaymentInfo paymentInfo) {
        // Validate payment info
        if (!isValidPaymentInfo(paymentInfo)) {
            throw new RuntimeException("Invalid payment details");
        }
        // TODO: Integrate with payment gateway provider
        // For demo, assume payment is successful
        return new PaymentResponse("SUCCESS");
    }

    /**
     * Processes payment for booking (used by BookingService).
     * @param amount Amount to be paid
     * @param paymentInfo Payment details
     * @return true if payment is successful
     */
    public boolean processPaymentForBooking(double amount, PaymentInfo paymentInfo) {
        // TODO: Integrate with payment gateway
        // For demo, always return true
        return true;
    }

    private boolean isValidPaymentInfo(PaymentInfo paymentInfo) {
        // TODO: Implement payment info validation logic
        return paymentInfo != null && paymentInfo.getCardNumber() != null;
    }
}
