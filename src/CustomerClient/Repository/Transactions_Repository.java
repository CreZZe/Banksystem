/*
 * JavaUtveckling 2018
 */
package CustomerClient.Repository;

import CustomerClient.Models.Account;
import CustomerClient.Models.Transactions;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.List;
import java.util.stream.Collectors;

public class Transactions_Repository {

    private Connection con;
    private Properties p = new Properties();
    private Account_Repository ar = new Account_Repository();

    public Transactions_Repository() {

        try {
            p.load(new FileInputStream("C:\\Users\\admin\\Documents\\"
                    + "NetBeansProjects\\Banksystem\\src\\AdminClient\\Settings.properties"));
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Lista på alla transaktioner kopplat till ett specifikt konto
    public List<Transactions> getAllTransactions(int accountId, Date fromDate, Date toDate) {
        List<Transactions> transactions = new ArrayList<>();
        List<Integer> allTransactionIds = new ArrayList<>();

        ResultSet rs = null; //möjligt att denna ska bli en procedure med möjlighet till errorhantering
        String query = "select id from transactions where accountsId= ?"
                + "and dateoftransaction >= ? and dateoftransaction < ?";
        String errormessage = "";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                CallableStatement stmt = con.prepareCall(query)) {
            /*
            select id from transactions where accountsId=2
            and dateoftransaction >= '2019/01/01' and dateoftransaction < '2019/06/28'
             */
            //select id from transaction where accId and date and date
            stmt.setInt(1, accountId);
            stmt.setDate(2, fromDate);
            stmt.setDate(3, toDate);

            rs = stmt.executeQuery();
            while (rs.next()) {
                allTransactionIds.add(rs.getInt("id"));
            }

            transactions = allTransactionIds.stream().map(i -> getTransactionsById(i)).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public Transactions getTransactionsById(int id) {

        Transactions transaction = new Transactions();
        Account account = ar.getAccountByTransactionsId(id);
        ResultSet rs = null;
        String query = "select transactions.id, dateoftransaction, amount from transactions "
                + "inner join accounts on transactions.accountsId = accounts.id where transactions.id = ?";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                transaction = new Transactions(rs.getInt("id"), rs.getDate("dateoftransaction"), rs.getDouble("amount"),
                        account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transaction;
    }

    public String withdrawTransaction(int accountId, double amount) {

        ResultSet rs = null;
        String query = "call withdraw(?,?)";
        String errormessage = "";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                CallableStatement stmt = con.prepareCall(query)) {

            stmt.setInt(1, accountId);
            stmt.setDouble(2, amount);
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

    //Denna liksom den ovan ska alltså både dyka upp som en transaktion i tabellen
    //men samtidigt som en trigger uppdatera summan på kontot på relevant sätt
    //kanske en IF category loan behövs för att påverkan ska vara annorlunda
    //
    public String depositTransaction(int accountId, double amount) {
        System.out.println("hejpls");
        ResultSet rs = null;
        String query = "call deposit(?,?)";
        String errormessage = "";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                CallableStatement stmt = con.prepareCall(query)) {
            System.out.println("halvvägs igen");
            stmt.setInt(1, accountId);
            stmt.setDouble(2, amount);
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
        System.out.println("lolele");
        return "Account was successfully deleted from the database.";
    }
}
