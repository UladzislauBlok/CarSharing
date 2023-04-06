package org.carsharing.users;

import org.carsharing.userOperation.CompanyOperation;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Manager {
    public static void doOperation(Statement statement) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        CompanyOperation companyOperation = new CompanyOperation(statement);
        OUT: while (true) {
            System.out.print("1. Company list\n" +
                    "2. Create a company\n" +
                    "0. Back\n" +
                    "> ");
            int choice = scanner.nextInt();

            System.out.print('\n');

            switch (choice) {
                case 1 : {
                    boolean isEmpty = companyOperation.printAllCompany();
                    if (!isEmpty) {
                        companyOperation.chooseCompany(statement);
                    }
                    break;
                }
                case 2 : {
                    companyOperation.addCompany();
                    break;
                }
                case 0 : {
                    break OUT;
                }
                default :{
                    System.out.println("Unknown operation\n");
                    break;
                }
            }
        }
    }
}
