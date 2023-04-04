package org.carsharing.companyObjects;

import java.sql.SQLException;

public interface CompanyDAO {
    void addCompany() throws SQLException;
    void printAllCompany() throws SQLException;
}
