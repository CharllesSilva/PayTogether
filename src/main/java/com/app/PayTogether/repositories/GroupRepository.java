package com.app.PayTogether.repositories;


import com.app.PayTogether.entity.Group;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

import static java.util.Objects.requireNonNull;


@Repository
public class GroupRepository {

    private final JdbcTemplate jdbcTemplate;

    public GroupRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(Group group) {
        String sql = "INSERT INTO groups (name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, group.getName());
            return ps;
        }, keyHolder);

        return requireNonNull(keyHolder.getKey()).longValue();
    }

}