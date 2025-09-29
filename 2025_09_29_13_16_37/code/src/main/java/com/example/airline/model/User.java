package com.example.airline.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    private String userId;
    private String name;
    private String email;
    private String phone;

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}