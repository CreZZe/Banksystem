package CustomerClient.Repository;

import CustomerClient.Models.Customer;
import CustomerClient.Models.Employee;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Customer_Repository {

    private Connection con;
    private Properties p = new Properties();
    private Employee_Repository er = new Employee_Repository();

    public Customer_Repository() {

        try {
            p.load(new FileInputStream("src\\CustomerClient\\Settings.properties"));
//            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Customer getCustomerBySSN(String SSNx) { //good stuff
        Customer customer = new Customer();
        Employee employee = er.getEmployeeByCustomerSSN(SSNx);
        ResultSet rs = null;
        String query = "select customer.id, customer.SSN, customer.firstname, customer.lastname, "
                + "customer.telephoneNr, customer.email, customer.pincode from customer "
                + "where customer.SSN = ?";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, SSNx + "");
            rs = stmt.executeQuery();

            while (rs.next()) {
                customer = new Customer(rs.getInt("id"), rs.getString("SSN"), rs.getString("name"), rs.getString(""),
                        rs.getString(""), rs.getString(""), rs.getInt(""), employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    public String deleteCustomerBySSN(String SSNx) {

        ResultSet rs = null;
        String query = "call deleteCustomer(?)";
        String errormessage = "";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                CallableStatement stmt = con.prepareCall(query)) {

            stmt.setString(1, SSNx);
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
            return "Could not add elf " + SSNx;
        }
        return SSNx + " was deleted from the database.";
    }

    public Customer getCustomerByTransactionsId(int id) {
        Customer customer = new Customer();
        Employee employee = er.getEmployeeByTransactionsIdViaCust(id);
        ResultSet rs = null;
        String query = "select customer.id, customer.SSNr, customer.firstname, customer.lastname,"
                + " customer.telephoneNr, customer.email, customer.pincode from customer"
                + " inner join accounts"
                + " on accounts.customerId = customer.id"
                + " inner join transactions"
                + " on transactions.accountsId = accounts.id"
                + " where transactions.id = ?";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, id + "");
            rs = stmt.executeQuery();

            while (rs.next()) {
                customer = new Customer(rs.getInt("id"), rs.getString("ssNr"), rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("telephoneNr"), rs.getString("email"), rs.getInt("pincode"), employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    public Customer getCustomerByAccountId(int accountId) {
        System.out.println("hejhejehje");
        Customer customer = new Customer();
        Employee employee = er.getEmployeeByAccountIdViaCust(accountId);
        System.out.println("passerade");
        ResultSet rs = null;
        String query = "select customer.id, customer.ssNr, customer.firstname, customer.lastname, "
                + "customer.telephoneNr, customer.email, customer.pincode from customer "
                + "inner join accounts on accounts.customerId=customer.id where accounts.id = ?";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                PreparedStatement stmt = con.prepareStatement(query)) {
            System.out.println("halvvägs");
            stmt.setInt(1, accountId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                customer = new Customer(rs.getInt("id"), rs.getString("ssNr"), rs.getString("firstname"), 
                                        rs.getString("lastname"),
                                        rs.getString("telephoneNr"), rs.getString("email"), rs.getInt("pincode"), 
                        employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("fångade customer");
        return customer;
    }
    public String addCustomer(String SSN, String firstname, String lastname, String telephoneNr,
            String email, int pincode, int employeeId) { //eller utan errorhantering
        System.out.println("hola!");
        ResultSet rs = null;
        String query = "call addCustomer(?,?,?,?,?,?,?)";
        String errormessage = "";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                CallableStatement stmt = con.prepareCall(query)) {

            stmt.setString(1, SSN);
            stmt.setString(2, firstname);
            stmt.setString(3, lastname);
            stmt.setString(4, telephoneNr);
            stmt.setString(5, email);
            stmt.setInt(6, pincode);
            stmt.setInt(7, employeeId);

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
            return "Could not add elf " + SSN;
        }
        System.out.println("waow");
        return SSN + " was added to database.";
    }

    public String addCustomerInformation(String SSN, String firstname, String lastname, String telephoneNr,
            String email, int pincode) { //eller utan errorhantering

        ResultSet rs = null;
        String query = "call addCustomerInformation(?,?,?,?,?,?)";
        String errormessage = "";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                CallableStatement stmt = con.prepareCall(query)) {

            stmt.setString(1, SSN);
            stmt.setString(2, firstname);
            stmt.setString(3, lastname);
            stmt.setString(4, telephoneNr);
            stmt.setString(5, email);
            stmt.setInt(6, pincode);

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
            return "Could not add elf " + SSN;
        }
        return SSN + " was added to database.";
    }
    
    public boolean checkPincode(String SSN, int pin) {
        
        ResultSet rs = null;
        String query = "CALL pinChecker(?, ?, ?)";
        String errormessage = "";
        boolean isPinCorrect = false;
        
        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
                p.getProperty("name"), 
                p.getProperty("password"));
                CallableStatement stmt = con.prepareCall(query)) {
            stmt.setString(1, SSN);
            stmt.setInt(2, pin);
            stmt.registerOutParameter(3, java.sql.Types.BOOLEAN);
            
            rs = stmt.executeQuery();

            
            isPinCorrect = stmt.getBoolean(3);            
            
        } catch (SQLException ex) {
            Logger.getLogger(Customer_Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isPinCorrect;
    }
}