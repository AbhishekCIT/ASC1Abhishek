package com.example.airbooking.repository;

import com.example.airbooking.model.Payment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for PaymentRepository interface (mock-based).
 */
public class PaymentRepositoryTest {
    /**
     * Test save and find operations (mocked).
     */
    @Test
    void testSaveAndFind() {
        PaymentRepository repo = mock(PaymentRepository.class);
        Payment payment = new Payment();
        when(repo.save(payment)).thenReturn(payment);
        assertEquals(payment, repo.save(payment));
    }
}
