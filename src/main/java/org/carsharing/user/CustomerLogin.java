package org.carsharing.user;

import org.carsharing.CustomerObjects.Customer;
import org.carsharing.userOperation.CustomerOperation;

import java.sql.SQLException;
import java.sql.Statement;

public class CustomerLogin {
    public static void doOperation(Statement statement) throws SQLException {
        CustomerOperation customerOperation = new CustomerOperation(statement);
        boolean isPresent = customerOperation.printCustomerList();
        if (isPresent) {
            Customer customer = customerOperation.selectCustomer();
            if (customer != null) {
                customerOperation.selectOperation(customer);
            }
        }
    }

    public static void createCustomer(Statement statement) throws SQLException {
        CustomerOperation customerOperation = new CustomerOperation(statement);
        customerOperation.createCustomer();
    }
}
