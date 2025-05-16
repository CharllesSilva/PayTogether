
CREATE TABLE user_groups (
  user_id BIGINT,
  group_id BIGINT,
  PRIMARY KEY (user_id, group_id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (group_id) REFERENCES groups(id)
)