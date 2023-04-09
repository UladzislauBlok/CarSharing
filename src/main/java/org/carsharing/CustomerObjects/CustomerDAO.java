package org.carsharing.CustomerObjects;

import org.carsharing.carObjects.Car;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    List<Customer> getCustomerList() throws SQLException;
    Customer getCustomerByName(String name) throws SQLException;
    void createCustomer(String name) throws SQLException;
    void rentCar(Customer customer, Car car) throws SQLException;
    void returnCar(Customer customer) throws SQLException;
}
