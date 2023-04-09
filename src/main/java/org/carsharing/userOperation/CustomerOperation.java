package org.carsharing.userOperation;

import org.carsharing.CustomerObjects.Customer;
import org.carsharing.CustomerObjects.CustomerDAO;
import org.carsharing.CustomerObjects.CustomerDAOImpl;
import org.carsharing.carObjects.Car;
import org.carsharing.companyObjects.Company;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class CustomerOperation {

    private final CustomerDAO customerDAO;
    private final Statement statement;

    public CustomerOperation(Statement statement) {
        customerDAO = new CustomerDAOImpl(statement);
        this.statement = statement;
    }

    public boolean printCustomerList() throws SQLException {
        List<Customer> customerList = customerDAO.getCustomerList();

        if (!customerList.isEmpty()) {
            System.out.println("Customer list:");
            for (int count = 0; count < customerList.size(); count++) {
                System.out.println(count+1 + ". " + customerList.get(count).getName());
            }
            System.out.print("0. Back\n" +
                    "> ");
            return true;
        } else {
            System.out.println("The customer list is empty!\n");
            return false;
        }
    }

    public Customer selectCustomer() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        List<Customer> customerList = customerDAO.getCustomerList();
        int choice = scanner.nextInt();
        System.out.print('\n');
        if (choice > 0 && choice <= customerList.size()) {
            return customerList.get(choice-1);
        } else if (choice != 0) {
            System.out.println("Wrong customer number");
            return null;
        }
        return null;
    }

    public void selectOperation(Customer customer) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        OUT:
        while (true) {
            System.out.print("1. Rent a car\n" +
                    "2. Return a rented car\n" +
                    "3. My rented car\n" +
                    "0. Back\n" +
                    "> ");
            int choice = scanner.nextInt();

            System.out.print('\n');

            switch (choice) {
                case 1 : {
                    rentCar(customer);
                    break;
                }
                case 2 : {
                    returnCar(customer);
                    break;
                }
                case 3 : {
                    printInfoRentCar(customer);
                    break;
                }
                case 0 : {
                    break OUT;
                }
                default : {
                    System.out.println("Unknown operation number");
                }
            }
        }
    }

    public void rentCar(Customer customer) throws SQLException {

        if (customer.getRentedCarId() == null) {
            CompanyOperation companyOperation = new CompanyOperation(statement);
            boolean isPresentCompany = companyOperation.printCompanyList();

            if (isPresentCompany) {
                Company company = companyOperation.selectCompany();
                System.out.print('\n');

                if (company != null) {
                    CarOperation carOperation = new CarOperation(statement);
                    List<Car> carList = carOperation.getFreeCarListOfOneCompany(company);
                    boolean isPresentCar = carOperation.printCarList(carList);

                    if (isPresentCar) {
                        Car car = carOperation.selectCar(company);

                        if(car != null) {
                            customerDAO.rentCar(customer, car);
                            System.out.println("You rented '" + car.getName() + "'\n");
                        }
                    }

                }
            }
        } else {
            System.out.println("You've already rented a car!\n");
        }
    }

    public void returnCar(Customer customer) throws SQLException {
        customer = customerDAO.getCustomerByName(customer.getName());
        if (customer.getRentedCarId() == null) {
            System.out.println("You didn't rent a car!\n");
        } else {
            customerDAO.returnCar(customer);
            System.out.println("You've returned a rented car!\n");
        }
    }

    public void printInfoRentCar(Customer customer) throws SQLException {
        customer = customerDAO.getCustomerByName(customer.getName());
        if (customer.getRentedCarId() == null) {
            System.out.println("You didn't rent a car!\n");
        }else {
            CarOperation carOperation = new CarOperation(statement);
            CompanyOperation companyOperation = new CompanyOperation(statement);
            Car car = carOperation.getCarById(customer.getRentedCarId());
            Company company = companyOperation.getCompanyById(car.getCompanyId());

            System.out.println("Your rented car:\n" +
                    car.getName() +
                    "\nCompany:\n" +
                    company.getName() + '\n');
        }
    }

    public void createCustomer() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the customer name:\n" +
                "> ");
        String customerName = scanner.nextLine();
        customerDAO.createCustomer(customerName);
        System.out.println("The customer was added!\n");
    }
}
