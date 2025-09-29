package com.airbook.service;

import com.airbook.model.NotificationPreference;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service for managing user notification preferences
 */
@Service
public class PreferenceService {
    private final Map<String, List<String>> preferenceStore = new HashMap<>();

    /**
     * Save notification preferences for a user
     */
    public void savePreferences(NotificationPreference preference) {
        List<String> validChannels = preference.getChannels().stream()
                .filter(c -> Arrays.asList("email", "sms", "app").contains(c))
                .collect(Collectors.toList());
        if (validChannels.isEmpty()) {
            throw new IllegalArgumentException("Invalid notification channel");
        }
        preferenceStore.put(preference.getUserId(), validChannels);
    }

    /**
     * Get notification preferences for a user
     */
    public List<String> getPreferences(String userId) {
        return preferenceStore.getOrDefault(userId, Collections.singletonList("email"));
    }
}
