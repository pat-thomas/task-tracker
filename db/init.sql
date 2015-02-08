CREATE USER 'task_tracker'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* to 'task_tracker'@'localhost' WITH GRANT OPTION;
CREATE USER 'task_tracker'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* to 'task_tracker'@'%' WITH GRANT OPTION;
CREATE USER 'admin'@'localhost';
GRANT RELOAD,PROCESS ON *.* TO 'admin'@'localhost';

CREATE DATABASE IF NOT EXISTS users;
CREATE TABLE IF NOT EXISTS users.accounts (
  username VARCHAR(255),
  email VARCHAR(255),
  password VARCHAR(255)
);
CREATE UNIQUE INDEX idx_users_accounts_username ON users.accounts(username);
CREATE UNIQUE INDEX idx_users_accounts_email ON users.accounts(email);

INSERT INTO users.accounts (username, email, password) VALUES ('PatThomas', 'patthomassoftware@gmail.com', 'password');
INSERT INTO users.accounts (username, email, password) VALUES ('OatRhombus', 'oat@rhom.bus', 'password');
INSERT INTO users.accounts (username, email, password) VALUES ('BeesHere', 'bees@here.com', 'password');
