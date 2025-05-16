package com.app.PayTogether.entity;

public class UserGroup {
    private Long userId;
    private Long groupId;

    public UserGroup(Long userId, Long groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public UserGroup() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}