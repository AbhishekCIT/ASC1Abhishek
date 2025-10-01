package com.airtransport.repository;

import com.airtransport.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Payment entity.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
    // Custom query methods can be added here if needed
}
