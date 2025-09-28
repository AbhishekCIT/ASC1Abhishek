package com.airtransport.booking.repository;

import com.airtransport.booking.entity.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for PaymentInfo entity.
 */
@Repository
public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Long> {
}
