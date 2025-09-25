package com.example.scheduler.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Entity representing a user.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    private String userId;
    private String email;

    // Constructors
    public User() {}
    public User(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
