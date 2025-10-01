package com.airtransport.service;

import com.airtransport.model.Payment;
import com.airtransport.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Service for payment processing logic and integration.
 */
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Process payment for a booking.
     * @param bookingId Booking ID
     * @param paymentDetails Payment details map
     * @return Payment object
     */
    public Payment processPayment(String bookingId, Map<String, Object> paymentDetails) {
        // Validate payment details (mock validation for demo)
        if (paymentDetails == null || !paymentDetails.containsKey("cardNumber")) {
            throw new IllegalArgumentException("Invalid payment details");
        }
        // TODO: Integrate with real payment gateway (PCI DSS compliant)
        // For demo, assume payment is always successful
        Payment payment = new Payment();
        payment.setPaymentId("P" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        payment.setBookingId(bookingId);
        payment.setAmount((Double) paymentDetails.getOrDefault("amount", 350.00));
        payment.setStatus("SUCCESS");
        payment.setPaymentDate(LocalDateTime.now());
        payment.setTransactionId("TX" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        paymentRepository.save(payment);
        return payment;
    }
}
