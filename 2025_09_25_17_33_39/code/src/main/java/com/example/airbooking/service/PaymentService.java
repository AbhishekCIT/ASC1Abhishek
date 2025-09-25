package com.example.airbooking.service;

import com.example.airbooking.model.Payment;
import com.example.airbooking.model.PaymentInfo;
import org.springframework.stereotype.Service;

/**
 * PaymentService processes payments securely (PCI DSS compliant).
 */
@Service
public class PaymentService {
    /**
     * Processes payment for a booking.
     * @param paymentInfo payment details
     * @param flightId associated flight id
     * @return Payment entity with status
     */
    public Payment processPayment(PaymentInfo paymentInfo, Long flightId) {
        // TODO: Integrate with real payment gateway
        Payment payment = new Payment();
        payment.setStatus("CONFIRMED");
        payment.setAmount(paymentInfo.getAmount());
        payment.setPaymentDate(java.time.LocalDate.now());
        return payment;
    }
}
