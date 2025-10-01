package com.airtransport.booking.repository;

import com.airtransport.booking.entity.Booking;
import com.airtransport.booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, String> {
    List<Booking> findByUser(User user);
    Optional<Booking> findByIdAndUser(String id, User user);
    Optional<Booking> findById(String id);
}
