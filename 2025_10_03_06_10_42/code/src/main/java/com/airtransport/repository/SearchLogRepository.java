package com.airtransport.repository;

import com.airtransport.model.SearchLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for SearchLog entity.
 */
@Repository
public interface SearchLogRepository extends JpaRepository<SearchLog, Long> {
}
