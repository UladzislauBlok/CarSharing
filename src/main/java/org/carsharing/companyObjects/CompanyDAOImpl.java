package org.carsharing.companyObjects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAOImpl implements CompanyDAO{

    private final Statement statement;

    public CompanyDAOImpl(Statement statement) {
        this.statement = statement;
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

    @Override
    public Company getCompanyById(int id) throws SQLException {
        String sql = "SELECT * FROM COMPANY " +
                "WHERE ID = " + id + ";";

        ResultSet resultSet = statement.executeQuery(sql);

        String name = "";

        while(resultSet.next()) {
            name = resultSet.getString("name");
        }

        return new Company(id, name);
    }

    @Override
    public void createCompany(String companyName) throws SQLException {
        String sql = "INSERT INTO COMPANY (name)" +
                "VALUES (" + "'" +  companyName + "'" + ");";

        statement.execute(sql);
    }
}
