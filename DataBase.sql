CREATE DATABASE smart_atm;

USE smart_atm;

CREATE TABLE accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    pin VARCHAR(10),
    email VARCHAR(100),
    phone VARCHAR(15),
    address VARCHAR(255),
    date_of_birth DATE,
    account_number BIGINT UNIQUE,
    balance DOUBLE DEFAULT 0.0
);

USE smart_atm;

CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_number BIGINT,
    type VARCHAR(10),             -- 'deposit' or 'withdraw'
    amount DOUBLE,
    transaction_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_number) REFERENCES accounts(account_number)
);



