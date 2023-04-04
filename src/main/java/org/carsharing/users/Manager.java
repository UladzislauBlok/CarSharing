package org.carsharing.users;

import org.carsharing.companyObjects.CompanyDAO;
import org.carsharing.companyObjects.CompanyDAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Manager{
    static String jdbc = "jdbc:mysql://localhost:3306/carsharing";
    static String user = "carsharinguser";
    static String password = "1111";
    static Scanner scanner = new Scanner(System.in);

    public static void login() {
        try {
            Connection connection = DriverManager.getConnection(jdbc, user, password);
            connection.setAutoCommit(true);
            Statement statement = connection.createStatement();
            CompanyDAO companyDAO = new CompanyDAOImpl(connection, statement);
            OUT: while (true) {
                System.out.print("1. Company list\n" +
                        "2. Create a company\n" +
                        "0. Back\n" +
                        "> ");
                int chose = scanner.nextInt();

                System.out.print('\n');

                switch (chose) {
                    case 1 : {
                        companyDAO.printAllCompany();
                        break;
                    }
                    case 2 : {
                        companyDAO.addCompany();
                        break;
                    }
                    case 0 : {
                        break OUT;
                    }
                    default: {
                        System.out.println("Unknown operation\n");
                        break;
                    }
                }
            }
            connection.close();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }
}
