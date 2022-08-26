SET SCHEMA REWARD_POINTS;

INSERT INTO customer(name, created_date, updated_date) VALUES('Customer 1', '2021-12-29 00.00.00', '2021-12-29 00.00.00');
INSERT INTO customer(name, created_date, updated_date) VALUES('Customer 2', '2021-12-30 00.00.00', '2021-12-30 00.00.00');
INSERT INTO customer(name, created_date, updated_date) VALUES('Customer 3', '2021-12-31 00.00.00', '2022-12-31 00.00.00');

INSERT INTO transaction(customer_id, amount, created_date) VALUES((select id from customer where name = 'Customer 1'), 100, '2022-05-29 00.00.00');
INSERT INTO transaction(customer_id, amount, created_date) VALUES((select id from customer where name = 'Customer 1'), 52, '2022-05-11 00.00.00');
INSERT INTO transaction(customer_id, amount, created_date) VALUES((select id from customer where name = 'Customer 1'), 100, '2022-06-29 00.00.00');
INSERT INTO transaction(customer_id, amount, created_date) VALUES((select id from customer where name = 'Customer 1'), 52, '2022-06-11 00.00.00');
INSERT INTO transaction(customer_id, amount, created_date) VALUES((select id from customer where name = 'Customer 1'), 100, '2022-07-29 00.00.00');
INSERT INTO transaction(customer_id, amount, created_date) VALUES((select id from customer where name = 'Customer 1'), 52, '2022-07-11 00.00.00');

INSERT INTO transaction(customer_id, amount, created_date) VALUES((select id from customer where name = 'Customer 2'), 102, '2022-05-29 00.00.00');
INSERT INTO transaction(customer_id, amount, created_date) VALUES((select id from customer where name = 'Customer 2'), 54, '2022-05-11 00.00.00');
INSERT INTO transaction(customer_id, amount, created_date) VALUES((select id from customer where name = 'Customer 2'), 102, '2022-06-29 00.00.00');
INSERT INTO transaction(customer_id, amount, created_date) VALUES((select id from customer where name = 'Customer 2'), 54, '2022-06-11 00.00.00');
INSERT INTO transaction(customer_id, amount, created_date) VALUES((select id from customer where name = 'Customer 2'), 102, '2022-07-29 00.00.00');
INSERT INTO transaction(customer_id, amount, created_date) VALUES((select id from customer where name = 'Customer 2'), 54, '2022-07-11 00.00.00');

INSERT INTO transaction(customer_id, amount, created_date) VALUES((select id from customer where name = 'Customer 3'), 120, '2022-05-29 00.00.00');
INSERT INTO transaction(customer_id, amount, created_date) VALUES((select id from customer where name = 'Customer 3'), 58, '2022-05-11 00.00.00');
INSERT INTO transaction(customer_id, amount, created_date) VALUES((select id from customer where name = 'Customer 3'), 120, '2022-06-29 00.00.00');
INSERT INTO transaction(customer_id, amount, created_date) VALUES((select id from customer where name = 'Customer 3'), 58, '2022-06-11 00.00.00');
INSERT INTO transaction(customer_id, amount, created_date) VALUES((select id from customer where name = 'Customer 3'), 120, '2022-07-29 00.00.00');
INSERT INTO transaction(customer_id, amount, created_date) VALUES((select id from customer where name = 'Customer 3'), 58, '2022-07-11 00.00.00');