package com.example.flightbooking.repository;

import com.example.flightbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for CRUD operations on Booking entity.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
}
