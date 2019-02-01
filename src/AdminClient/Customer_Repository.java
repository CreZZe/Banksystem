/*
 * JavaUtveckling 2018
 */

package AdminClient;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class Customer_Repository {

    private Connection con;
    private Properties p = new Properties();

    public Customer_Repository() {

        try {
            p.load(new FileInputStream("src/Banksystem/Settings.properties"));
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Customer getCustomerByAccountId(int loanId) {
        Customer customer = new Customer();
        ResultSet rs = null;
        String query = "select country.id, country.name from country inner join child on child.countryId=country.id where child.id = ?";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, loanId + "");
            rs = stmt.executeQuery();

            while (rs.next()) {
                customer = new Customer(rs.getInt("id"), rs.getString("SSN"), rs.getString("name"), rs.getString(""),
                                        rs.getString(""), rs.getString(""), rs.getInt(""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }
}