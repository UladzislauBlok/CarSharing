package org.carsharing.companyObjects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompanyDAOImpl implements CompanyDAO{
    Connection connection;
    Statement statement;

    public CompanyDAOImpl(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    @Override
    public void addCompany() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the company name:\n" +
                "> ");
        String companyName = scanner.nextLine();

        String sql = "INSERT INTO COMPANY (name)" +
                "VALUES (" + "'" +  companyName + "'" + ");";

        statement.execute(sql);

        System.out.println("The company was created!\n");
    }

    @Override
    public void printAllCompany() throws SQLException {
        String sql = "SELECT * FROM COMPANY " +
                "ORDER BY id;";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Company> list = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Company company = new Company(id, name);
            list.add(company);
        }

        if (list.isEmpty()) {
            System.out.println("The company list is empty!\n");
        } else {
            System.out.println("Company list:");
            for (int count = 0; count < list.size(); count++) {
                System.out.println(count+1 + ". " + list.get(count).getName());
            }
            System.out.print('\n');
        }
    }
}
