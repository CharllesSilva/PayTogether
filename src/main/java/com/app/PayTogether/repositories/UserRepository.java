package com.app.PayTogether.repositories;

import com.app.PayTogether.entity.User;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{email}, Integer.class);
        return count != null && count > 0;
    }


    public Optional<User> findBasicUserByEmail(String email) {
        String sql = "SELECT id, email, password FROM users WHERE email = ?";

        try {
            User user = jdbcTemplate.queryForObject(sql, new Object[]{email}, (rs, rowNum) ->
                    new User(
                            rs.getLong("id"),
                            rs.getString("email"),
                            rs.getString("password")
                    )
            );
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public User save(User user) {
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            return ps;
        }, keyHolder);

        Long generatedId = requireNonNull(keyHolder.getKey()).longValue();
        user.setId(generatedId);

        return user;
    }

}
