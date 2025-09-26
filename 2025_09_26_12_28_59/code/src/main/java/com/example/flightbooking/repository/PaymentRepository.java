package com.example.flightbooking.repository;

import com.example.flightbooking.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for CRUD operations on Payment entity.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
}
