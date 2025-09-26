package com.example.flightbooking.service;

import com.example.flightbooking.model.User;
import com.example.flightbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for user authentication and retrieval.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Authenticates a user based on a token (stub implementation).
     * @param token JWT/OAuth2 token
     * @return Authenticated User
     */
    public User authenticate(String token) {
        // TODO: Implement JWT/OAuth2 authentication
        // For demonstration, return a dummy user
        return userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
