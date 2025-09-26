package com.example.flightbooking.repository;

import com.example.flightbooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
