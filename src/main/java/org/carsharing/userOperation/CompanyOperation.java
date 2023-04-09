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

    public CompanyOperation(Statement statement) {
        companyDAO = new CompanyDAOImpl(statement);
    }

    public boolean printCompanyList() throws SQLException {
        List<Company> companyList = companyDAO.getCompanyList();

        if (!companyList.isEmpty()) {
            System.out.println("Choose a company:");
            for (int count = 0; count < companyList.size(); count++) {
                System.out.println(count+1 + ". " + companyList.get(count).getName());
            }
            System.out.print("0. Back\n" +
                    "> ");
            return true;
        } else {
            System.out.println("The company list is empty!\n");
            return false;
        }
    }

    public Company selectCompany() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        List<Company> companyList = companyDAO.getCompanyList();
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= companyList.size()) {
            return companyList.get(choice-1);
        } else if (choice != 0) {
            System.out.println("Wrong company number");
            return null;
        }
        return null;
    }

    public Company getCompanyById(int id) throws SQLException {
        return companyDAO.getCompanyById(id);
    }

    public void addCompany() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the company name:");
        String companyName = scanner.nextLine();
        companyDAO.createCompany(companyName);
        System.out.println("The company was created!\n");
    }
}
