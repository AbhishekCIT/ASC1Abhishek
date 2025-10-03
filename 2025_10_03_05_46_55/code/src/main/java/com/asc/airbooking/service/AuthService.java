package com.asc.airbooking.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * Service for OAuth2 authentication.
 */
@Service
public class AuthService {
    /**
     * Authenticates the user using OAuth2 token.
     * @param authentication Spring Security Authentication object
     * @return true if authenticated, false otherwise
     */
    public boolean authenticate(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated() && authentication instanceof JwtAuthenticationToken;
    }
}
