/*
 * JavaUtveckling 2018
 */

package AdminClient.Repository;

import AdminClient.Models.Category;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class Category_Repository {

    private Connection con;
    private Properties p = new Properties();

    public Category_Repository() {

        try {
            p.load(new FileInputStream("C:\\Users\\admin\\Documents\\"
                    + "NetBeansProjects\\Banksystem\\src\\AdminClient\\Settings.properties"));
            Class.forName("com.mysql.jdbc.Driver");
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
