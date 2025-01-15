CREATE TABLE answer (
    id SERIAL PRIMARY KEY,
    answer_value NUMERIC(1,0),
    question_id INT REFERENCES question(id),
    user_id INT REFERENCES users(id),
    CONSTRAINT unique_question_user UNIQUE (question_id, user_id)
);