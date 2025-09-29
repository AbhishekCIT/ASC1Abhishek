package com.airbook.repository;

import com.airbook.model.Payment;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

/**
 * Repository for payment data access
 */
@Repository
public class PaymentRepository {
    private final Map<String, Payment> paymentStore = new HashMap<>();

    /**
     * Save payment (mock implementation)
     */
    public void save(Payment payment) {
        paymentStore.put(payment.getPaymentId(), payment);
    }

    /**
     * Find payment by ID (mock implementation)
     */
    public Payment findById(String paymentId) {
        return paymentStore.get(paymentId);
    }
}
