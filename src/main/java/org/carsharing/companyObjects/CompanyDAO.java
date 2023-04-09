package org.carsharing.companyObjects;

import java.sql.SQLException;
import java.util.List;

public interface CompanyDAO {
    List<Company> getCompanyList() throws SQLException;
    Company getCompanyById(int id) throws SQLException;
    void createCompany(String companyName) throws SQLException;
}
