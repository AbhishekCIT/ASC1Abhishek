package com.airbook.repository;

import com.airbook.model.SupportRequest;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Repository for support ticket data access
 */
@Repository
public class SupportRepository {
    private final Map<String, SupportRequest> supportStore = new HashMap<>();

    /**
     * Save support request (mock implementation)
     */
    public void save(SupportRequest request) {
        supportStore.put(request.getRequestId(), request);
    }

    /**
     * Find support requests by user ID (mock implementation)
     */
    public List<SupportRequest> findRequestsByUser(String userId) {
        return supportStore.values().stream()
                .filter(r -> userId.equals(r.getUserId()))
                .collect(Collectors.toList());
    }
}
