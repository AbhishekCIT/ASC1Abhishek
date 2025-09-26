package com.example.flightbooking.repository;

import com.example.flightbooking.model.PaymentResult;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Payment entity (represented by PaymentResult for simplicity).
 */
public interface PaymentRepository extends JpaRepository<PaymentResult, Long> {
}
