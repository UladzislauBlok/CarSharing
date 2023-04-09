package org.carsharing.carObjects;

import org.carsharing.companyObjects.Company;

import java.sql.SQLException;
import java.util.List;

public interface CarDAO {

    List<Car> getCarListOfOneCompany(Company company) throws SQLException;
    List<Car> getFreeCarListOfOneCompany(Company company) throws SQLException;
    Car getCarById(int id) throws SQLException;
    void createCar(String carName, Company company) throws SQLException;
}
