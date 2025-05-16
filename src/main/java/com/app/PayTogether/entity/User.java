package com.app.PayTogether.entity;

import java.util.List;

public class User {

    private Long id;
    private String email;
    private String password;
    private String username;
    private boolean verified = false;
    private List<Long> groupIds;

    public User() {
    }

    public User(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }


    public User(Long id, String email, String password, String username, boolean verified, List<Long> groupIds) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.verified = verified;
        this.groupIds = groupIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public List<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Long> groupIds) {
        this.groupIds = groupIds;
    }
}