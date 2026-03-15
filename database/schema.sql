-- Employee Management System Database Schema
-- Run this SQL script in MySQL to create the database and tables

-- Create database
CREATE DATABASE IF NOT EXISTS emp_management;
USE emp_management;

-- Create admin table
CREATE TABLE IF NOT EXISTS admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create employee table
CREATE TABLE IF NOT EXISTS employee (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL,
    position VARCHAR(50) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    phone VARCHAR(15),
    email VARCHAR(100),
    joining_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert default admin user (username: admin, password: admin123)
INSERT INTO admin (username, password) VALUES 
('admin', 'admin123');

-- Sample employee data
INSERT INTO employee (name, department, position, salary, phone, email, joining_date) VALUES
('John Smith', 'IT', 'Software Developer', 60000.00, '9876543210', 'john.smith@email.com', '2023-01-15'),
('Sarah Johnson', 'HR', 'HR Manager', 55000.00, '9876543211', 'sarah.johnson@email.com', '2022-06-20'),
('Michael Brown', 'Finance', 'Accountant', 50000.00, '9876543212', 'michael.brown@email.com', '2021-03-10'),
('Emily Davis', 'IT', 'System Analyst', 65000.00, '9876543213', 'emily.davis@email.com', '2022-09-05'),
('Robert Wilson', 'Marketing', 'Marketing Executive', 45000.00, '9876543214', 'robert.wilson@email.com', '2023-02-28');

-- Verify data
SELECT * FROM admin;
SELECT * FROM employee;
