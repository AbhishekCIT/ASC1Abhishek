package com.airtransport.repository;

import com.airtransport.model.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PaymentRepository.
 */
@DataJpaTest
class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Test saving and retrieving a Payment entity.
     */
    @Test
    void testSaveAndFindPayment() {
        Payment payment = new Payment();
        payment.setPaymentId("P123");
        payment.setBookingId("B456");
        payment.setAmount(350.0);
        payment.setStatus("SUCCESS");
        payment.setPaymentDate(LocalDateTime.now());
        payment.setTransactionId("TX789");
        paymentRepository.save(payment);

        Payment found = paymentRepository.findById("P123").orElse(null);
        assertNotNull(found);
        assertEquals("B456", found.getBookingId());
        assertEquals(350.0, found.getAmount());
        assertEquals("SUCCESS", found.getStatus());
        assertNotNull(found.getPaymentDate());
        assertEquals("TX789", found.getTransactionId());
    }
}
