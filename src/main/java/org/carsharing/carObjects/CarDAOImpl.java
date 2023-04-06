package org.carsharing.carObjects;

import org.carsharing.companyObjects.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarDAOImpl implements CarDAO{
    Statement statement;

    public CarDAOImpl (Statement statement) {
        this.statement = statement;
    }

    @Override
    public void addCar(Company company) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the car name:\n" +
                "> ");
        String carName = scanner.nextLine();

        String sql = "INSERT INTO CAR (NAME, COMPANY_ID)" +
                "VALUES (" + "'" +  carName + "', " + company.getId() + ");";

        statement.execute(sql);

        System.out.println("The car was added!\n");
    }

    @Override
    public void printAllCar(Company company) throws SQLException {
        String sql = "SELECT * FROM CAR " +
                "WHERE COMPANY_ID = " + company.getId();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Car> list = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int companyId = resultSet.getInt("COMPANY_ID");
            Car car = new Car(id, name, companyId);
            list.add(car);
        }

        if (list.isEmpty()) {
            System.out.println("The car list is empty!\n");
        } else {
            System.out.println("Car list:");
            for (int count = 0; count < list.size(); count++) {
                System.out.println(count+1 + ". " + list.get(count).getName());
            }
            System.out.print('\n');
        }
    }
}
