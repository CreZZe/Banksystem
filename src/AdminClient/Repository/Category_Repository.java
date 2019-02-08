/*
 * JavaUtveckling 2018
 */

package AdminClient.Repository;

import AdminClient.Models.Category;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Category_Repository {

    private Connection con;
    private Properties p = new Properties();

    public Category_Repository() {

        try {
            p.load(new FileInputStream("src\\AdminClient\\Settings.properties"));
//            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ändra ränta på konto-här eller på account
    
    public Category getCategoryByTransactionsId(int id)
    {
        Category category = new Category();
        ResultSet rs = null;
        String query = "select category.id, category.name, category.interest, "
                + "category.amortization from category"
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
                category = new Category(rs.getInt("id"), rs.getString("SSN"), rs.getDouble("name"),
                        rs.getDouble("amortization"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }
    public String changeInterest(int accId, double newInterest) {

        ResultSet rs = null;
        String query = "call changeInterest(?,?)";
        String errormessage = "";
        //System.out.println("kom hit iaf");

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                CallableStatement stmt = con.prepareCall(query)) {

            stmt.setInt(1, accId);
            stmt.setDouble(2, newInterest);

            rs = stmt.executeQuery();
            System.out.println("gick bra");
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
            return "Could not add elf";
        }
        return " was added to database.";
    }
    
    public Category getCategoryByAccountId(int accountId) {
        Category category = new Category();
        ResultSet rs = null;
        String query = "select category.id, category.name, category.interest, category.amortization from category "
                + "inner join accounts on accounts.categoryId=category.id where accounts.id = ?";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, accountId + "");
            rs = stmt.executeQuery();

            while (rs.next()) {
                category = new Category(rs.getInt("id"), rs.getString("name"), rs.getDouble("interest"), 
                rs.getDouble("amortization"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("fångade category");
        return category;
    }
}
