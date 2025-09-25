package com.example.scheduledreporting.service;

import com.example.scheduledreporting.entity.User;
import com.example.scheduledreporting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

/**
 * Service for user management and authorization.
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * Get the authorized user by username, throw if not found or unauthorized.
     */
    public User getAuthorizedUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new AccessDeniedException("Unauthorized to schedule report"));
    }
}
