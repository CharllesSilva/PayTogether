package com.app.PayTogether.entity;

import java.time.LocalDateTime;

public class EmailVerificationToken {

    private Long id;
    private String token;
    private User user;
    private LocalDateTime expiresAt;

    public EmailVerificationToken() {
    }

    public EmailVerificationToken(Long id, String token, User user, LocalDateTime expiresAt) {
        this.id = id;
        this.token = token;
        this.user = user;
        this.expiresAt = expiresAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}