package com.example.airbooking.repository;

import com.example.airbooking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Seat entity.
 */
@Repository
public interface SeatRepository extends JpaRepository<Seat, String> {
}
