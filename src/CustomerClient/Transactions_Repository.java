/*
 * JavaUtveckling 2018
 */
package CustomerClient;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

public class Transactions_Repository {

    private Connection con;
    private Properties p = new Properties();

    public Transactions_Repository() {

        try {
            p.load(new FileInputStream("src/Banksystem/Settings.properties"));
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
