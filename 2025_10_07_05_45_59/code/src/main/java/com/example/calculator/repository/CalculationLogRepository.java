package com.example.calculator.repository;

import com.example.calculator.entity.CalculationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for CalculationLog entity.
 */
@Repository
public interface CalculationLogRepository extends JpaRepository<CalculationLog, Long> {
}
