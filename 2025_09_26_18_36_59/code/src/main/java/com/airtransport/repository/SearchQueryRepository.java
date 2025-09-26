package com.airtransport.repository;

import com.airtransport.entity.SearchQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for SearchQueryEntity.
 */
@Repository
public interface SearchQueryRepository extends JpaRepository<SearchQueryEntity, String> {
    // Custom query methods can be added here
}
