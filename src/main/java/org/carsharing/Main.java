package org.carsharing;
import org.carsharing.users.*;

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
                        "0. Exit\n" +
                        "> ");

                int choice = scanner.nextInt();
                System.out.print('\n');

                switch (choice) {
                    case 1 : {
                        Manager.doOperation(statement);
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
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("Database error");
        }
    }
}