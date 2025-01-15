CREATE TABLE broker_user (
    broker_id INT REFERENCES broker(id),
    user_id INT REFERENCES users(id),
    PRIMARY KEY (broker_id, user_id)
);