package org.carsharing.companyObjects;

import java.sql.SQLException;
import java.util.List;

public interface CompanyDAO {
    void addCompany() throws SQLException;
    List<Company> getCompanyList() throws SQLException;
}
