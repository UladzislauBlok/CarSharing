package org.carsharing.user;

import org.carsharing.companyObjects.Company;
import org.carsharing.userOperation.CarOperation;
import org.carsharing.userOperation.CompanyOperation;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Manager {
    public static void doOperation(Statement statement) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        CompanyOperation companyOperation = new CompanyOperation(statement);
        OUT:
        while (true) {
            System.out.print("1. Company list\n" +
                    "2. Create a company\n" +
                    "0. Back\n" +
                    "> ");
            int choice = scanner.nextInt();

            System.out.print('\n');

            switch (choice) {
                case 1: {
                    boolean isPresent = companyOperation.printCompanyList();
                    if (isPresent) {
                        Company company = companyOperation.selectCompany();
                        if (company != null) {
                            CarOperation carOperation = new CarOperation(statement);
                            carOperation.operationWithCompany(company);
                        }
                    }
                    break;
                }
                case 2: {
                    companyOperation.addCompany();
                    break;
                }
                case 0: {
                    break OUT;
                }
                default: {
                    System.out.println("Unknown operation\n");
                    break;
                }
            }
        }
    }
}
