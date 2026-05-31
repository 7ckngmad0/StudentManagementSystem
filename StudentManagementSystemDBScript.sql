CREATE DATABASE IF NOT EXISTS student_record_db;
USE student_record_db;

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'Pending'
);

CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    student_number VARCHAR(30) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    course VARCHAR(50) NOT NULL,
    year_level INT NOT NULL
);

CREATE TABLE student_reports (
    report_id INT PRIMARY KEY AUTO_INCREMENT,
    report_title VARCHAR(100),
    report_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (username, password, role, status)
VALUES ('admin', 'admin123', 'Admin', 'Approved');

USE student_record_db;

SELECT * FROM students;
SELECT * FROM users;

ALTER TABLE users ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'Pending';
UPDATE users SET status = 'Approved' WHERE username = 'admin';