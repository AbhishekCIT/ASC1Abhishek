package com.airtransport.repository;

import com.airtransport.entity.PaymentEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for PaymentRepository.
 * Covers CRUD operations, edge cases, and error scenarios.
 */
@DataJpaTest
class PaymentRepositoryTest {
    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Test saving and retrieving a payment (normal case).
     */
    @Test
    void testSaveAndFindById_Normal() {
        PaymentEntity payment = new PaymentEntity();
        payment.setPaymentId("P123");
        payment.setBookingId("B456");
        payment.setAmount(150.0);
        payment.setStatus("SUCCESS");
        payment.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(payment);
        Optional<PaymentEntity> found = paymentRepository.findById("P123");
        assertTrue(found.isPresent());
        assertEquals("B456", found.get().getBookingId());
    }

    /**
     * Test finding a non-existent payment (edge case).
     */
    @Test
    void testFindById_NotFound() {
        Optional<PaymentEntity> found = paymentRepository.findById("NON_EXISTENT");
        assertFalse(found.isPresent());
    }

    /**
     * Test deleting a payment (normal case).
     */
    @Test
    void testDeleteById_Normal() {
        PaymentEntity payment = new PaymentEntity();
        payment.setPaymentId("P456");
        paymentRepository.save(payment);
        paymentRepository.deleteById("P456");
        Optional<PaymentEntity> found = paymentRepository.findById("P456");
        assertFalse(found.isPresent());
    }

    /**
     * Test saving payment with null ID (error scenario).
     */
    @Test
    void testSave_NullId() {
        PaymentEntity payment = new PaymentEntity();
        payment.setPaymentId(null);
        assertThrows(Exception.class, () -> paymentRepository.save(payment));
    }
}
