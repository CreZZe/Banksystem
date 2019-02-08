/*
 * JavaUtveckling 2018
 */
package AdminClient.Repository;

import AdminClient.Models.Account;
import AdminClient.Models.Category;
import AdminClient.Models.Customer;
import AdminClient.Models.Employee;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class Account_Repository {

    private Customer_Repository cr = new Customer_Repository();
    private Employee_Repository ee = new Employee_Repository();
    private Category_Repository cg = new Category_Repository();
    private Connection con;
    private Properties p = new Properties();

    public Account_Repository() {

        try {
            p.load(new FileInputStream("C:\\Users\\admin\\Documents\\"
                    + "NetBeansProjects\\Banksystem\\src\\AdminClient\\Settings.properties"));
            
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Account getAccountByTransactionsId(int id) {
        Account account = new Account();

        Customer customer = cr.getCustomerByTransactionsId(id);
        Employee employee = ee.getEmployeeByTransactionsId(id); //går förvisso via select-customervägen, dåligt?
        //behöver ju inte vara samma employee till kontot som customer, alltså fel, har fixat nu dock
        Category category = cg.getCategoryByTransactionsId(id);

        ResultSet rs = null;
        String query = "select accounts.id, name, dateofcreation, balance from accounts "
                + "inner join transactions"
                + "on transactions.accountsId = accounts.id where transactions.id = ?;"; //får ut rätt account

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                account = new Account(rs.getInt("id"), rs.getString("name"), rs.getDate("dateofcreation"),
                        rs.getDouble("balance"), category, customer, employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

//    public Account getAccountsByCustomerSSN(String SSN) { // eller namn w/e
//
//        Account account = new Account();
//        Customer customer = cr.getCustomerByAccountId(SSN);
//        Employee employee = ee.getEmployeeByAccountId(SSN);
//        Category category = cg.getCategoryByAccountId(SSN);
//
//        ResultSet rs = null;
//        String query = "select child.id, child.name, child.address, child.nice from child "
//                + "inner join country on child.countryId = country.id where child.id = ?";
//
//        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
//                p.getProperty("name"),
//                p.getProperty("password"));
//                PreparedStatement stmt = con.prepareStatement(query)) {
//
//            stmt.setInt(1, SSN);
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                account = new Account(rs.getInt("id"), rs.getString("name"), rs.getDate("date"),
//                        rs.getDouble("balance"), category, customer, employee);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return account;
//    }

    public List<Account> getAllAccountsBySSN(String SSN) {
        List<Account> account = new ArrayList<>();
        List<Integer> allAccountIds = new ArrayList<>();
        ResultSet rs = null;
        String query = "SELECT accounts.id FROM accounts "
                + "inner join customer on accounts.customerId = customer.id "
                + "where customer.ssnr = ?";
        //String errormessage = "";
        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                CallableStatement stmt = con.prepareCall(query);) {

            stmt.setString(1, SSN);
            rs = stmt.executeQuery();
            while (rs.next()) {
                allAccountIds.add(rs.getInt("id"));
            }
            
            account = allAccountIds.stream().map(i -> getAccountById(i)).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    public String createCustomerAccount(String SSNx, String employeeName, String accName, String categoryName) {
        System.out.println("woop");
        ResultSet rs = null;
        String query = "call createCustomerAccount(?,?,?,?)";
        String errormessage = "";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                CallableStatement stmt = con.prepareCall(query)) {

            stmt.setString(1, SSNx);
            stmt.setString(2, employeeName);
            stmt.setString(3, accName);
            stmt.setString(4, categoryName);
            System.out.println("tjoo");
            rs = stmt.executeQuery();
            while (rs.next()) {
                errormessage = rs.getString("error");
            }
            if (!errormessage.equals("")) {
                return errormessage;
            }
        } catch (SQLException e) {
            return e.getMessage() + "(" + e.getErrorCode() + ")";
        } catch (Exception e) {
            e.printStackTrace();
            return "Could not add account";
        }
        return "Account was successfully created in the database.";
    }

    public String deleteCustomerAccount(String SSNx, String accName) {

        ResultSet rs = null;
        String query = "call deleteCustomerAccount(?,?)";
        String errormessage = "";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                CallableStatement stmt = con.prepareCall(query)) {

            stmt.setString(1, SSNx);
            stmt.setString(2, accName);
            rs = stmt.executeQuery();
            while (rs.next()) {
                errormessage = rs.getString("error");
            }
            if (!errormessage.equals("")) {
                return errormessage;
            }
        } catch (SQLException e) {
            return e.getMessage() + "(" + e.getErrorCode() + ")";
        } catch (Exception e) {
            e.printStackTrace();
            return "Could not delete account";
        }
        return "Account was successfully deleted from the database.";
    }

    public Account getAccountById(int id) { // eller namn w/e
        System.out.println(id);
        System.out.println("hejdå");
        Account account = new Account();
        Customer customer = cr.getCustomerByAccountId(id);
        Employee employee = ee.getEmployeeByAccountId(id);
        Category category = cg.getCategoryByAccountId(id);
        System.out.println("heheheh");
        ResultSet rs = null;
        String query = "select accounts.id, accounts.name, accounts.dateofcreation, balance from accounts "
                + "where accounts.id = ?";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                account = new Account(rs.getInt("id"), rs.getString("name"), rs.getDate("dateofcreation"),
                        rs.getDouble("balance"), category, customer, employee);
                //skulle man kunna slänga in en metod direkt här istället?
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("hohoho");
        System.out.println(account.getBalance());
        return account;
    }
}
