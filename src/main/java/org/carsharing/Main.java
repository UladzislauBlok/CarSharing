package org.carsharing;

import org.carsharing.users.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OUT: while (true) {
            System.out.print("1. Log in as a manager\n" +
                    "0. Exit\n" +
                    "> ");

            int chose = scanner.nextInt();
            System.out.print('\n');

            switch (chose) {
                case 1 : {
                    Manager.login();
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
    }
}