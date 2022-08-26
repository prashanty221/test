SET SCHEMA REWARD_POINTS;
CREATE TABLE customer
(
    id  integer AUTO_INCREMENT PRIMARY KEY,
    name    varchar(255) NOT NULL,
    UNIQUE (name),
    created_date timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_date timestamp
);

CREATE TABLE transaction
(
    id  integer AUTO_INCREMENT PRIMARY KEY,
    customer_id  integer NOT NULL,
    amount integer default 0,
    created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE reward_points
(
    id  integer AUTO_INCREMENT PRIMARY KEY,
    transaction_id  integer NOT NULL,
    points integer default 0,
    FOREIGN KEY (transaction_id) REFERENCES transaction(id)
);
