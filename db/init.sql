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
