package com.example.airbooking.service;

import org.springframework.stereotype.Service;

/**
 * Service for OAuth2 authentication and token validation
 */
@Service
public class AuthService {
    /**
     * Validate OAuth2 token
     * @param token OAuth2 token
     * @return true if valid, false otherwise
     */
    public boolean validateToken(String token) {
        // TODO: Integrate with IAM provider for token validation
        return true;
    }
}
