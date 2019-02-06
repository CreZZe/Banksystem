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
            p.load(new FileInputStream("src/Banksystem/Settings.properties"));
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Employee getEmployeeByAccountId(int loanId) {
        Employee employee = new Employee();
        ResultSet rs = null;
        String query = "select employee.id, employee.firstname from country inner join child on child.countryId=country.id where child.id = ?";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, loanId + "");
            rs = stmt.executeQuery();

            while (rs.next()) {
                employee = new Employee(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }
}
