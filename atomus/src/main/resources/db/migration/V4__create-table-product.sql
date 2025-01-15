CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE,
    is_covered_fgc BOOLEAN,
    product_risk_level NUMERIC(3,1)
);