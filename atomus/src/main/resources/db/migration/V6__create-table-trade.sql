CREATE TABLE trade (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    trade_type VARCHAR(10),
    trade_date DATE,
    broker_id INT REFERENCES broker(id),
    product_id INT REFERENCES product(id),
    title VARCHAR(255),
    quantity INT,
    unit_price NUMERIC(18, 2),
    fees NUMERIC(18, 2)
);