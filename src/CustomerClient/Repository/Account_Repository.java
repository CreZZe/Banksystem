
package CustomerClient.Repository;

import CustomerClient.Models.Account;
import CustomerClient.Models.Category;
import CustomerClient.Models.Customer;
import CustomerClient.Models.Employee;
import CustomerClient.Repository.Category_Repository;
import CustomerClient.Repository.Customer_Repository;
import CustomerClient.Repository.Employee_Repository;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class Account_Repository {

    private Customer_Repository cr;
    private Employee_Repository ee;
    private Category_Repository cg;
    private Connection con;
    private Properties p = new Properties();
    

    public Account_Repository(){

        try{
            p.load(new FileInputStream("src/Banksystem/Settings.properties"));
            Class.forName("com.mysql.jdbc.Driver");
        }
         catch (Exception e){
            e.printStackTrace();
        }
    }
     public Account getAccountById(int id){ // eller namn w/e

        Account account = new Account();  
        Customer customer = cr.getCustomerByAccountId(id);
        Employee employee = ee.getEmployeeByAccountId(id);
        Category category = cg.getCategoryByAccountId(id);
        
        
        ResultSet rs = null;
        String query = "select child.id, child.name, child.address, child.nice from child "
                + "inner join country on child.countryId = country.id where child.id = ?";
        
        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                             p.getProperty("name"),
                             p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(query)){
            
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                account = new Account(rs.getInt("id"),rs.getString("name"), rs.getDate("date"),
                        rs.getDouble("balance"), category, customer, employee);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }
}
