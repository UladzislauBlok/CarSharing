package org.carsharing;

import org.carsharing.user.CustomerLogin;
import org.carsharing.user.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        String jdbc = "jdbc:mysql://localhost:3306/carsharing";
        String user = "carsharinguser";
        String password = "1111";

        try {
            Connection connection = DriverManager.getConnection(jdbc, user, password);
            connection.setAutoCommit(true);
            Statement statement = connection.createStatement();

            Scanner scanner = new Scanner(System.in);
            OUT:
            while (true) {
                System.out.print("1. Log in as a manager\n" +
                        "2. Log in as a customer\n" +
                        "3. Create a customer\n" +
                        "0. Exit\n" +
                        "> ");

                int choice = scanner.nextInt();
                System.out.print('\n');

                switch (choice) {
                    case 1: {
                        Manager.doOperation(statement);
                        break;
                    }
                    case 2 : {
                        CustomerLogin.doOperation(statement);
                        break;
                    }
                    case 3 : {
                        CustomerLogin.createCustomer(statement);
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
            statement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("Database error");
        }
    }
}