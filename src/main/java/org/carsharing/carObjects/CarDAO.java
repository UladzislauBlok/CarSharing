package org.carsharing.carObjects;

import org.carsharing.companyObjects.Company;

import java.sql.SQLException;

public interface CarDAO {
    void addCar(Company company) throws SQLException;
    void printAllCar(Company company) throws SQLException;
}
