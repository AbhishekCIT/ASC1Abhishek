package com.airbook.repository;

import com.airbook.model.NotificationLog;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Repository for notification delivery logs
 */
@Repository
public class NotificationLogRepository {
    private final Map<String, NotificationLog> logStore = new HashMap<>();

    /**
     * Save notification log (mock implementation)
     */
    public void save(NotificationLog log) {
        logStore.put(log.getLogId(), log);
    }

    /**
     * Find logs by user ID (mock implementation)
     */
    public List<NotificationLog> findLogsByUser(String userId) {
        return logStore.values().stream()
                .filter(l -> userId.equals(l.getUserId()))
                .collect(Collectors.toList());
    }
}
