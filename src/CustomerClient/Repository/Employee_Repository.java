/*
 * JavaUtveckling 2018
 */

package CustomerClient.Repository;

import CustomerClient.Models.Employee;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class Employee_Repository {

    private Connection con;
    private Properties p = new Properties();

    public Employee_Repository() {

        try {
            p.load(new FileInputStream("C:\\Users\\admin\\Documents\\"
                    + "NetBeansProjects\\Banksystem\\src\\AdminClient\\Settings.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Employee getEmployeeByFirstname(String firstname) { //good stuff
        Employee employee = new Employee();
        ResultSet rs = null;
        String query = "select employee.id, employee.firstname, employee.lastname "
                + "from employee "
                + "where employee.firstname = ?";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, firstname + "");
            rs = stmt.executeQuery();

            while (rs.next()) {
                employee = new Employee(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }
    
    public Employee getEmployeeByCustomerSSN(String SSN) {
        Employee employee = new Employee();
        ResultSet rs = null;
        String query = "select employee.id, employee.name from employee "
                + "inner join customer on customer.employeeId=employee.id where customer.SSN = ?";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, SSN + "");
            rs = stmt.executeQuery();

            while (rs.next()) {
                employee = new Employee(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    public Employee getEmployeeByTransactionsIdViaCust(int id) {
        Employee employee = new Employee();
        ResultSet rs = null;
        String query = "select employee.id, employee.firstname, employee.lastname from employee"
                + "inner join customer"
                + "on customer.employeeId = employee.id "
                + "inner join accounts"
                + "on accounts.customerId = customer.id"
                + "inner join transactions"
                + "on transactions.accountsId = accounts.id"
                + "where transactions.id = ?";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, id + "");
            rs = stmt.executeQuery();

            while (rs.next()) {
                employee = new Employee(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }
    
    public Employee getEmployeeByTransactionsId(int id) {
        Employee employee = new Employee();
        ResultSet rs = null;
        String query = "select employee.id, employee.firstname, employee.lastname from employee"
                + "inner join accounts"
                + "on accounts.employeeId = employee.id"
                + "inner join transactions"
                + "on transactions.accountsId = accounts.id"
                + "where transactions.id = ?";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, id + "");
            rs = stmt.executeQuery();

            while (rs.next()) {
                employee = new Employee(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    public Employee getEmployeeByAccountId(int accountIdx) {
        Employee employee = new Employee();
        ResultSet rs = null;
        String query = "select employee.id, employee.firstname, employee.lastname from employee "
                + "inner join accounts on accounts.employeeId=employee.id where accounts.id = ?";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, accountIdx + "");
            rs = stmt.executeQuery();

            while (rs.next()) {
                employee = new Employee(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("fångade employee");
        return employee;
    }
    public Employee getEmployeeByAccountIdViaCust(int id) {
        Employee employee = new Employee();
        ResultSet rs = null;
        System.out.println("tjohoo");
        String query = "select employee.id, employee.firstname, employee.lastname from employee "
                + "inner join customer "
                + "on customer.employeeId = employee.id "
                + "inner join accounts "
                + "on accounts.customerId = customer.id "
                + "where accounts.id = ?";
        System.out.println("weee");
        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            System.out.println("blabla");
            while (rs.next()) {
                employee = new Employee(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("fångade");
        return employee;
    }
}
