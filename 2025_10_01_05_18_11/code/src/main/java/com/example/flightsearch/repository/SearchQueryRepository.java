package com.example.flightsearch.repository;

import com.example.flightsearch.entity.SearchQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for SearchQuery entity.
 */
@Repository
public interface SearchQueryRepository extends JpaRepository<SearchQuery, Long> {
}
