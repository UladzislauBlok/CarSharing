package org.carsharing.CustomerObjects;

import org.carsharing.carObjects.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO{

    private final Statement statement;

    public CustomerDAOImpl(Statement statement) {
        this.statement = statement;
    }

    @Override
    public List<Customer> getCustomerList() throws SQLException {
        String sql = "SELECT * FROM CUSTOMER " +
                "ORDER BY id;";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Customer> list = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Integer rentedCarId = resultSet.getObject("RENTED_CAR_ID", Integer.class);
            Customer customer = new Customer(id, name, rentedCarId);
            list.add(customer);
        }
        return list;
    }

    @Override
    public Customer getCustomerByName(String name) throws SQLException {
        String sql = "SELECT * FROM CUSTOMER " +
                "WHERE NAME = '" + name + "';";
        ResultSet resultSet = statement.executeQuery(sql);

        int id = 0;
        Integer rentedCarId = null;

        while (resultSet.next()) {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            rentedCarId = resultSet.getObject("RENTED_CAR_ID", Integer.class);
        }
        return new Customer(id, name, rentedCarId);
    }

    @Override
    public void createCustomer(String name) throws SQLException {
        String sql = "INSERT INTO CUSTOMER (name)" +
                "VALUES (" + "'" +  name + "'" + ");";

        statement.execute(sql);
    }

    @Override
    public void rentCar(Customer customer, Car car) throws SQLException {
        String sql = "UPDATE CUSTOMER" +
                " SET RENTED_CAR_ID = " + car.getId() +
                " WHERE NAME = '" + customer.getName() + "';";

        statement.execute(sql);
    }

    @Override
    public void returnCar(Customer customer) throws SQLException {
        String sql = "UPDATE CUSTOMER " +
                "SET RENTED_CAR_ID = NULL " +
                "WHERE NAME = '" + customer.getName() + "';";

        statement.execute(sql);
    }
}
