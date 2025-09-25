package com.example.scheduler.service;

import com.example.scheduler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for user validation and lookup.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Validate user existence by ID.
     */
    public boolean validateUser(Long userId) {
        return userRepository.existsById(userId);
    }
}
