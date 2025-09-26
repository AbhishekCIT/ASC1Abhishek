package com.example.flightsearch.repository;

import com.example.flightsearch.entity.SearchCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for SearchCriteria entity.
 */
@Repository
public interface SearchCriteriaRepository extends JpaRepository<SearchCriteria, Long> {
    // Additional query methods can be defined here
}
