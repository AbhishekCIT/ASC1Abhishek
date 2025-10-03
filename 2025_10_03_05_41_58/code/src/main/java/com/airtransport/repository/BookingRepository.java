package com.airtransport.repository;

import com.airtransport.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Booking entity.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    List<Booking> findByUserUserId(String userId);
}
