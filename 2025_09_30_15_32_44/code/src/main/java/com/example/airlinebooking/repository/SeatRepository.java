package com.example.airlinebooking.repository;

import com.example.airlinebooking.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Seat entity.
 */
@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
