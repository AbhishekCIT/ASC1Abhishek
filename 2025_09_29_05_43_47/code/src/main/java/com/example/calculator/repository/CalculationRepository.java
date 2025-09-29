package com.example.calculator.repository;

import com.example.calculator.entity.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Calculation entity
 */
@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Integer> {
    // Additional query methods can be defined here if needed
}
