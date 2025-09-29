package com.airtransport.repository;

import com.airtransport.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository for Booking entity.
 */
@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, String> {
    Optional<BookingEntity> findByBookingId(String bookingId);
}
