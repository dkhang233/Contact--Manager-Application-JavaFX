DROP DATABASE IF EXISTS contact_manager_system;
CREATE DATABASE contact_manager_system;

USE contact_manager_system;

DROP TABLE IF EXISTS _users;
CREATE TABLE _users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(120) NOT NULL,
    last_name VARCHAR(120) NOT NULL,
    phone_number VARCHAR(10) NOT NULL,
    password VARCHAR(120) NOT NULL,
    username VARCHAR(120) NOT NULL,
    is_used TINYINT(1) NOT NULL
);

DROP TABLE IF EXISTS _groups;
CREATE TABLE _groups (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(200) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS _members;
CREATE TABLE _members (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    group_id INT,
    role VARCHAR(20) NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES _users (id),
    FOREIGN KEY (group_id)
        REFERENCES _groups (id)
);

INSERT INTO _users(first_name,last_name,phone_number,password,username,is_used)
VALUES("admin","admin","1","1","admin",0),
("john","john","0123456789","1","john",0);

INSERT INTO _groups(name,description,created_at)
VALUES("aaa","This is a test group",now()),
("bbb","This is a test group",now());