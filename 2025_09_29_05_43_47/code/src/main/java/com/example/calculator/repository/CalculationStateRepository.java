package com.example.calculator.repository;

import com.example.calculator.entity.CalculationState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for CalculationState entity
 */
@Repository
public interface CalculationStateRepository extends JpaRepository<CalculationState, Integer> {
    // Additional query methods can be defined here if needed
}
