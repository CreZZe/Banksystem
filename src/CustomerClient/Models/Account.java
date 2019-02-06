
package CustomerClient.Models;

import CustomerClient.Models.Customer;
import CustomerClient.Models.Employee;
import java.sql.Date;

public class Account {

    private int id;
    private String name;
    private Date date;
    private double balance;
    private Category category;
    private Customer customer;
    private Employee employee;
    
    public Account(int id, String name, Date date, double balance, 
            Category category, Customer customer, Employee employee){

        this.id = id;
        this.name = name;
        this.date = date;
        this.balance = balance;
        this.category = category;
        this.customer = customer;
        this.employee = employee;
    }

    public Account(){}
    
        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
