CREATE TABLE expenses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    due_date DATE NOT NULL,
    group_id BIGINT,
    FOREIGN KEY (group_id) REFERENCES groups(id)
)