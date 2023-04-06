package org.carsharing.userOperation;

import org.carsharing.carObjects.CarDAO;
import org.carsharing.carObjects.CarDAOImpl;
import org.carsharing.companyObjects.Company;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CarOperation {

    CarDAO carDAO;
    Scanner scanner;
    Company company;

    CarOperation(Statement statement, Company company) {
        this.carDAO = new CarDAOImpl(statement);
        this.company = company;
        this.scanner = new Scanner(System.in);
    }

    public void doOperation() throws SQLException {
        System.out.println(company.getName() + " company:");

        OUT: while (true) {
            System.out.print("1. Car list\n" +
                    "2. Create a car\n" +
                    "0. Back\n" +
                    "> ");
            int choice = scanner.nextInt();

            System.out.print('\n');

            switch (choice) {
                case 1 : {
                    carDAO.printAllCar(company);
                    break;
                }
                case 2 : {
                    carDAO.addCar(company);
                    break;
                }
                case 0 : {
                    break OUT;
                }
                default : {
                    System.out.println("Unknown operation\n");
                    break;
                }
            }
        }
    }
}
