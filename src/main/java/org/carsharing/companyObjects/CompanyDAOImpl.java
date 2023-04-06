package org.carsharing.companyObjects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompanyDAOImpl implements CompanyDAO{
    private final Statement statement;

    public CompanyDAOImpl(Statement statement) {
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
    public List<Company> getCompanyList() throws SQLException {
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
        return list;
    }
}
