CREATE TABLE clients (
    client_id               SERIAL PRIMARY KEY,
    full_name               VARCHAR NOT NULL,
    email                   VARCHAR NOT NULL,
    address                 VARCHAR,
    phone_number            VARCHAR NOT NULL,
    balance                 DECIMAL(8, 2) NOT NULL
);

COMMENT ON TABLE clients IS 'This table holds all clients';

CREATE TABLE cards (
    card_id                 SERIAL PRIMARY KEY,
    card_number             BIGINT,
    client_id               INT REFERENCES clients ON DELETE CASCADE ON UPDATE CASCADE,
    expiration_date         DATE,
    expired                 BOOLEAN
);

CREATE TABLE transactions (
    transaction_id          SERIAL PRIMARY KEY,
    card_number             BIGINT REFERENCES cards ON DELETE CASCADE ON UPDATE CASCADE,
    transaction_sum         DECIMAL(6, 2) CONSTRAINT positive_transaction CHECK (transaction_sum > 0)
);

COMMENT ON TABLE transactions IS 'This table holds all transactions';
COMMENT ON COLUMN transactions.card_number IS 'Credit card that was used for the transaction';
