CREATE TABLE notifications (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    notification_value VARCHAR(255) NOT NULL,
    notification_type VARCHAR(255) NOT NULL
);