# Sorting Visualizer with Authentication System

## Overview

This project is a Java-based sorting visualizer with an authentication system. It allows users to visualize various sorting algorithms while providing a secure login and sign-up process using OTP (One-Time Password) verification via email and hash password system.

## Features

- Visualization of popular sorting algorithms:
  - Bubble Sort
  - Selection Sort
  - Insertion Sort
  - Merge Sort
  - Quick Sort

- Secure authentication system:
  - User registration with email verification
  - OTP (One-Time Password) sent to user's email for verification
  - Login functionality with email and password

## Requirements

- Java Development Kit (JDK) and installed
- IDE (Integrated Development Environment) such as Apache NetBeans or IntelliJ IDEA
- XAMPP for MySQL database server (or any other similar software)
- MySQL Connector/J JAR file for JDBC connectivity
- JavaMail API JAR file for sending OTP emails
- Internet connectivity for sending OTP emails

## Installation

- Open the project in your preferred IDE (Apache NetBeans, IntelliJ IDEA, etc.).
- Configure the MySQL database connection:
   - Create a database named `java_user_database`.
   - Modify the database connection details in the `establishConnection()` method of the `SignUp` and `Login` classes.
- Import the required libraries (JAR files) for the project:
   - Make sure to include the necessary JAR files for authentication, such as `javax.mail` and `org.mindrot.jbcrypt`.
- Build and run the project.

## Setting Up MySQL Database

To store user data, you need to set up a MySQL database. Follow these steps to create the necessary table:

1. **Open your MySQL database management tool** (e.g., phpMyAdmin, MySQL Workbench).

2. **Create a new database** if you haven't already done so. You can name it `java_user_database`.

3. **Execute the following SQL script** to create the `user` table within your database:

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(127) NOT NULL,
    email VARCHAR(127) NOT NULL UNIQUE,
    password VARCHAR(127) NOT NULL
);

## Usage

1. Upon launching the application, users are presented with the sign-up form.
2. Users can register by providing their full name, email address, and password.
3. An OTP (One-Time Password) is sent to the provided email address for verification.
4. After successful verification, users can log in using their email and password.
5. Once logged in, users can access the sorting visualizer interface and choose from various sorting algorithms to visualize.

## License

This project is licensed under the [MIT License](link-to-license).



