package com.airtransport.repository;

import com.airtransport.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Payment entity.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
}
