CREATE TABLE users (
    user_id BIGINT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL
);

CREATE TABLE tasks (
    task_id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    task_value VARCHAR(255) NOT NULL,
    creation_date TIMESTAMPTZ NOT NULL,
    target_date TIMESTAMPTZ,
    CONSTRAINT fk_task_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE notifications (
    notification_id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    task_id BIGINT NOT NULL,
    notification_value VARCHAR(255) NOT NULL,
    CONSTRAINT fk_notification_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT fk_notification_task FOREIGN KEY (task_id) REFERENCES tasks(task_id)
);
