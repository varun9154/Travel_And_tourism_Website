# Travel_And_tourism_Website

A Java-based Travel and Tourism Management System designed to manage customers, hotel bookings, travel packages, and more. This system provides a seamless experience for both customers and administrators to manage bookings, customer details, and packages.

## Features

### Customer Management
- **Add Customer**: Register new customers by capturing personal details.
- **Update Customer**: Modify customer details.
- **Delete Customer**: Remove customer details from the system.
- **View Customers**: View a list of all registered customers.

### Booking System
- **Book Hotel**: Allow customers to book hotels for their travel dates.
- **View Booked Hotels**: View the list of hotels booked by customers.
- **Book Package**: Customers can book various travel packages.
- **View Packages**: Display available travel packages with details.

### Admin Functions
- **Manage Hotels**: Admins can add, update, or delete hotels.
- **Manage Packages**: Admins can manage available travel packages.
- **Customer Management**: Admins can view, add, update, or delete customer data.
  
### User Interface
- Clean, simple, and user-friendly graphical interface for both customers and administrators.
- Forms to enter and update details for customers, hotels, and travel packages.

## Tech Stack

- **Backend**: Java
- **Database**: MySQL (for storing customer and booking information)
- **GUI**: Swing (Java GUI library for frontend)
- **JDBC**: Java Database Connectivity for database operations

## Prerequisites

Before running the project, ensure you have the following installed:

- **Java 8 or higher**: Required to run the Java application.
- **MySQL Database**: Required to store and manage the data. You should set up a MySQL database with the required schema.
  
  **Example MySQL setup:**
  ```sql
  CREATE DATABASE travel_management_system;
  CREATE TABLE customer (
      username VARCHAR(50) PRIMARY KEY,
      name VARCHAR(100),
      phone VARCHAR(15),
      email VARCHAR(50),
      country VARCHAR(50),
      permanent_address VARCHAR(255)
  );
  CREATE TABLE hotel (
      hotel_id INT PRIMARY KEY AUTO_INCREMENT,
      hotel_name VARCHAR(100),
      city VARCHAR(50),
      price DECIMAL(10,2)
  );
  CREATE TABLE package (
      package_id INT PRIMARY KEY AUTO_INCREMENT,
      package_name VARCHAR(100),
      price DECIMAL(10,2),
      description TEXT
  );
  CREATE TABLE booking (
      booking_id INT PRIMARY KEY AUTO_INCREMENT,
      username VARCHAR(50),
      hotel_id INT,
      package_id INT,
      booking_date DATE,
      FOREIGN KEY (username) REFERENCES customer(username),
      FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id),
      FOREIGN KEY (package_id) REFERENCES package(package_id)
  );
