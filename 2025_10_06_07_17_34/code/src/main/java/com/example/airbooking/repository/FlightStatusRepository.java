package com.example.airbooking.repository;

import com.example.airbooking.model.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightStatusRepository extends JpaRepository<FlightStatus, String> {
    List<FlightStatus> findByFlightNumberOrderByUpdatedAtDesc(String flightNumber);
}