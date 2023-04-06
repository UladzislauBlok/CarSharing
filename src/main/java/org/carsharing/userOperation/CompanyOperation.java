package org.carsharing.userOperation;

import org.carsharing.companyObjects.Company;
import org.carsharing.companyObjects.CompanyDAO;
import org.carsharing.companyObjects.CompanyDAOImpl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class CompanyOperation {

    private final CompanyDAO companyDAO;
    private final Scanner scanner;

    public CompanyOperation(Statement statement) {
        this.companyDAO = new CompanyDAOImpl(statement);
        this.scanner = new Scanner(System.in);
    }

    public boolean printAllCompany() throws SQLException {
        List<Company> companyList = companyDAO.getCompanyList();
        if (companyList.isEmpty()) {
            System.out.println("The company list is empty!\n");
            return true;
        } else {
            System.out.println("Company list:");
            for (int count = 0; count < companyList.size(); count++) {
                System.out.println(count+1 + ". " + companyList.get(count).getName());
            }
            System.out.print("0. Back\n" +
                    "> ");
            return false;
        }
    }

    public void chooseCompany(Statement statement) throws SQLException {
        int choice = scanner.nextInt();
        System.out.print('\n');

        List<Company> companyList = companyDAO.getCompanyList();

        if (choice > 0 && choice <= companyList.size()) {
            Company company = companyList.get(choice - 1);
            CarOperation carOperation = new CarOperation(statement, company);
            carOperation.doOperation();
        } else if (choice != 0) {
            System.out.println("Wrong company number");
        }
    }

    public void addCompany() throws SQLException {
        companyDAO.addCompany();
    }
}
