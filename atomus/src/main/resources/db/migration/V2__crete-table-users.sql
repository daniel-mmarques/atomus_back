CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    cpf VARCHAR(14) UNIQUE,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    profile_id INT REFERENCES profile(id),
    is_active BOOLEAN
);