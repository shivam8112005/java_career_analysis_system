# Career Analysis System - Java Console Application
## Overview
The Career Analysis System is a robust, console-based application developed in Java, designed to streamline the recruitment and job-searching process. This system offers distinct functionalities for both jobseekers and recruiters, enabling efficient career management and recruitment operations.

## Features:
Jobseekers can manage profiles, search and apply for jobs, build resumes, and find suitable career matches.
Recruiters can search for potential candidates, manage profiles, and recruit jobseekers who have applied for positions.
This application demonstrates advanced Java concepts such as:

## Object-Oriented Programming (OOP)
Recursion

Encapsulation

Abstraction

File I/O

Exception Handling

Multithreading

Java Generics for data structure management

## DBMS
Transaction Management

Stored Procedures

Junction Tables

SQL Joins

## Features and Functionalities
### For Jobseekers:
Profile Management: Create, update, and manage jobseeker profiles.

Job Search and Application: Search for job listings and apply to open positions.

User Search: Look for other users within the system.

Resume Building: Build and manage resumes.

Career Matching: Get career recommendations based on the jobseeker's profile.

Assessments: Participate in assessments to improve career prospects

### For Recruiters:
Manages Joblistings: Create, update, and manage joblistings.

Recruitment: Recruit jobseekers who have applied for various positions.

User Search: Search for jobseekers within the system based on various criteria.

Profile Management: Create, update, and manage recruiter profiles.

## Key Technologies
Java: The core programming language used to implement the application.

MySQL: The database system used to store and manage data for jobseekers and recruiters.

JDBC (Java Database Connectivity): Used for establishing connections and executing SQL queries on the MySQL database.

## Installation and Setup
Clone the repository:

git clone [https://github.com/shivam8112005/java_career_analysis_system](https://github.com/shivam8112005/java_career_analysis_system.git)

cd career-analysis-system

Database Setup:

Install MySQL and set up the database using the provided SQL schema file.

Import the career_analysis.sql file to create the necessary tables and stored procedures:


mysql -u root -p < career_analysis.sql

Configure Database Connection:

Update the JDBC connection settings in the DatabaseConfig.java file with your MySQL credentials:

String url = "jdbc:mysql://localhost:3306/your_database";

String username = "root";

String password = "your_password";

## Run the Application:
Compile and run the application from the terminal:

javac CareerAnalysisSystem.java

java CareerAnalysisSystem

## Contribution
Feel free to contribute to the project by opening a pull request or submitting issues. Your suggestions for new features and enhancements are welcome!


