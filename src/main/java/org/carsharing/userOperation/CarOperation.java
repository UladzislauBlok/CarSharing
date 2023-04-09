package org.carsharing.userOperation;

import org.carsharing.carObjects.Car;
import org.carsharing.carObjects.CarDAO;
import org.carsharing.carObjects.CarDAOImpl;
import org.carsharing.companyObjects.Company;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class CarOperation {

    private final CarDAO carDAO;

    public CarOperation(Statement statement) {
        carDAO = new CarDAOImpl(statement);
    }

    public void operationWithCompany(Company company) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println('\n' + "'" + company.getName() + "' company");
        OUT: while (true) {
            System.out.print("1. Car list\n" +
                    "2. Create a car\n" +
                    "0. Back\n" +
                    "> ");
            int choice = scanner.nextInt();

            System.out.print('\n');

            switch (choice) {
                case 1: {
                    List<Car> carList = getCarListOfOneCompany(company);
                    printCarList(carList);
                    System.out.print('\n');
                    break;
                }
                case 2: {
                    String carName = getCarName();
                    carDAO.createCar(carName, company);
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

    public Car getCarById(int id) throws SQLException {
        return carDAO.getCarById(id);
    }

    public List<Car> getCarListOfOneCompany(Company company) throws SQLException {
        return carDAO.getCarListOfOneCompany(company);
    }

    public List<Car> getFreeCarListOfOneCompany(Company company) throws SQLException {
        return carDAO.getFreeCarListOfOneCompany(company);
    }

    public boolean printCarList(List<Car> carList) {
        if (carList.isEmpty()) {
            System.out.println("The car list is empty!");
            return false;
        } else {
            for (int count = 0; count < carList.size(); count++) {
                System.out.println(count + 1 + ". " + carList.get(count).getName());
            }
            return true;
        }
    }

    public Car selectCar(Company company) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        List<Car> carList = carDAO.getFreeCarListOfOneCompany(company);
        System.out.println("0. Back");
        System.out.print("> ");
        int choice = scanner.nextInt();
        System.out.print('\n');
        if (choice > 0 && choice <= carList.size()) {
            return carList.get(choice-1);
        } else if (choice != 0) {
            System.out.println("Wrong car number");
            return null;
        }
        return null;
    }

    public String getCarName() {
        System.out.print("Enter the car name: \n" +
                "> ");
        Scanner scanner = new Scanner(System.in);
        String carName = scanner.nextLine();
        System.out.println("The car was added!");
        System.out.print('\n');
        return carName;
    }
}
