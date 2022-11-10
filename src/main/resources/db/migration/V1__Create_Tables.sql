CREATE TABLE products (
    product_id              SERIAL PRIMARY KEY,
    product_name            VARCHAR NOT NULL,
    price                   DECIMAL(6, 2) NOT NULL DEFAULT 0.0
);

COMMENT ON TABLE products IS 'This table holds all products';

CREATE TABLE clients (
    client_id               SERIAL PRIMARY KEY,
    full_name               VARCHAR NOT NULL,
    email                   VARCHAR NOT NULL,
    address                 VARCHAR,
    phone_number            VARCHAR NOT NULL
);

COMMENT ON TABLE clients IS 'This table holds all clients';

CREATE TABLE transactions (
    transaction_id          SERIAL PRIMARY KEY,
    client_id               INT REFERENCES clients ON DELETE RESTRICT ON UPDATE CASCADE,
    card                    BIGINT NOT NULL,
    transaction_sum         DECIMAL(6, 2) CONSTRAINT positive_transaction CHECK (transaction_sum > 0)
);

COMMENT ON TABLE transactions IS 'This table holds all transactions';
COMMENT ON COLUMN transactions.card IS 'Credit card that was used for the transaction';

CREATE TABLE orders (
    order_id                SERIAL PRIMARY KEY,
    client_id               INT,
    order_datetime          TIMESTAMP,
    order_sum               DECIMAL(6, 2) CONSTRAINT positive_sum CHECK (order_sum > 0),
    transaction_id          INT REFERENCES transactions ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT "client_id_fk" FOREIGN KEY (client_id) REFERENCES clients (client_id) ON DELETE RESTRICT ON UPDATE CASCADE
);

COMMENT ON TABLE orders IS 'This table holds all orders';

CREATE TABLE order_product (
    order_id                INT REFERENCES orders ON DELETE RESTRICT,
    product_id              INT REFERENCES products ON DELETE RESTRICT,
    amount                  INT NOT NULL,
    PRIMARY KEY (order_id, product_id)
);

COMMENT ON TABLE order_product IS 'This table connects orders table with products table';
