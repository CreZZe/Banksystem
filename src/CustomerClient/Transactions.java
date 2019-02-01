/*
 * JavaUtveckling 2018
 */
package CustomerClient;

import java.sql.Date;

public class Transactions {

    private int id;
    private Date date;
    private double amount;
    private Account account;

    public Transactions(int id, Date date, double amount,
            Account account) {

        this.id = id;
        this.date = date;
        this.amount = amount;
        this.account = account;
    }

    public Transactions() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
