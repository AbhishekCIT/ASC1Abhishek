package com.example.airtransport.repository;

import com.example.airtransport.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Booking entity.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
}
