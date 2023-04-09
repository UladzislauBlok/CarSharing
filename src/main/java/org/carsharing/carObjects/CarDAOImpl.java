package org.carsharing.carObjects;

import org.carsharing.companyObjects.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO{

    private final Statement statement;

    public CarDAOImpl(Statement statement){
        this.statement = statement;
    }

    @Override
    public List<Car> getCarListOfOneCompany(Company company) throws SQLException {
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

        return list;
    }

    @Override
    public List<Car> getFreeCarListOfOneCompany(Company company) throws SQLException {
        String sql = "SELECT car.id, car.name, car.company_id FROM CAR " +
                "LEFT JOIN CUSTOMER " +
                "ON car.id = customer.rented_car_id " +
                "WHERE COMPANY_ID = " + company.getId() + " AND CUSTOMER.RENTED_CAR_ID IS NULL;";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Car> list = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int companyId = resultSet.getInt("COMPANY_ID");
            Car car = new Car(id, name, companyId);
            list.add(car);
        }

        return list;
    }

    @Override
    public Car getCarById(int id) throws SQLException {
        String sql = "SELECT * FROM CAR " +
                "WHERE ID = " + id + ";";

        ResultSet resultSet = statement.executeQuery(sql);

        String name = "";
        int companyId = 0;

        while(resultSet.next()) {
            name = resultSet.getString("name");
            companyId = resultSet.getInt("COMPANY_ID");
        }

        return new Car(id, name, companyId);
    }

    @Override
    public void createCar(String carName, Company company) throws SQLException {
        String sql = "INSERT INTO CAR (NAME, COMPANY_ID)" +
                "VALUES (" + "'" +  carName + "', " + company.getId() + ");";

        statement.execute(sql);
    }
}
