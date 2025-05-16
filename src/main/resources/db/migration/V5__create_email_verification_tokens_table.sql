CREATE TABLE email_verification_tokens (
    token VARCHAR(255) PRIMARY KEY,
    user_id BIGINT NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);
