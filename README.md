Program that manages a car-sharing service allowing companies to rent out their cars and find customers.

Functions implemented so far:
1) When logging in as a manager.

   Available features:
   1) Ability to add the company to the database;
   2) Ability to print a list of all companies in ascending order of their id.
   3) Add cars to companies
   4) Print the car list of the selected company

2) When logging in as a customer

   Available features:
   1) Rent a car
   2) Return the car
   3) Print information about the rental car

3) Allows to create a new customer

To start the project:
1) git clone https://github.com/UladzislauBlok/CarSharing;
2) MySql configuration:
   1) CREATE DATABASE carsharing;
   2) USE carsharing;
   3) CREATE TABLE COMPANY
      (ID INTEGER PRIMARY KEY AUTO_INCREMENT,
      NAME VARCHAR(70) UNIQUE NOT NULL
      );
   4) CREATE TABLE CAR
      (ID INTEGER PRIMARY KEY AUTO_INCREMENT,
      NAME VARCHAR(70) UNIQUE NOT NULL,
      COMPANY_ID INTEGER NOT NULL,
      CONSTRAINT fk_company FOREIGN KEY (COMPANY_ID)
      REFERENCES COMPANY (ID));
   5) CREATE TABLE CUSTOMER
      (ID INTEGER PRIMARY KEY AUTO_INCREMENT,
      NAME VARCHAR(70) UNIQUE NOT NULL,
      RENTED_CAR_ID INTEGER,
      CONSTRAINT fk_car FOREIGN KEY (RENTED_CAR_ID)
      REFERENCES CAR (ID));
   6) CREATE USER 'carsharinguser'@'%' IDENTIFIED by '1111';
   7) GRANT ALL ON carsharing.* TO 'carsharinguser'@'%';
3) Open the project in the IDE (Eclipse or Intellij IDEA), build and run it. (MySql server must be running to work correctly)