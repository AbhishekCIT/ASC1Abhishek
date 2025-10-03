package com.example.airtransport.repository;

import com.example.airtransport.entity.Booking;
import com.example.airtransport.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Booking entity.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    List<Booking> findByUser(User user);
}
