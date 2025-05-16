package com.app.PayTogether.repositories;


import com.app.PayTogether.entity.UserGroup;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class UserGroupRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserGroupRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(UserGroup userGroup) throws SQLException {
        String sql = "INSERT INTO user_groups (user_id, group_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, userGroup.getUserId(), userGroup.getGroupId());
    }
}