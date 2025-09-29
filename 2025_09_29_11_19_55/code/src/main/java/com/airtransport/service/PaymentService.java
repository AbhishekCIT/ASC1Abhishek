package com.airtransport.service;

import com.airtransport.model.Payment;
import com.airtransport.model.PaymentInfo;
import org.springframework.stereotype.Service;

/**
 * Service for handling payment processing and validation.
 */
@Service
public class PaymentService {
    /**
     * Process payment and validate details.
     * @param paymentInfo Payment information
     * @param amount Amount to charge
     * @return Payment result
     */
    public Payment processPayment(PaymentInfo paymentInfo, double amount) {
        // In real implementation, integrate with payment gateway (PCI DSS compliant)
        // Here, simulate payment success for valid card number
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentDate(java.time.LocalDate.now());
        if (paymentInfo != null && paymentInfo.getCardNumber() != null && paymentInfo.getCardNumber().length() == 16) {
            payment.setPaymentStatus("SUCCESS");
        } else {
            payment.setPaymentStatus("FAILED");
        }
        return payment;
    }
}
