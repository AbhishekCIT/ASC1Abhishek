package com.example.airlinebooking.repository;

import com.example.airlinebooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
