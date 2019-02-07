/*
 * JavaUtveckling 2018
 */

package CustomerClient.Controller;

import CustomerClient.Models.Account;
import CustomerClient.Models.Customer;
import CustomerClient.Models.Employee;
import CustomerClient.Models.Transactions;
import CustomerClient.Repository.Account_Repository;
import CustomerClient.Repository.Category_Repository;
import CustomerClient.Repository.Customer_Repository;
import CustomerClient.Repository.Employee_Repository;
import CustomerClient.Repository.Transactions_Repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ClientController {

    Account_Repository ar = new Account_Repository();
    Category_Repository cr = new Category_Repository();
    Customer_Repository cus = new Customer_Repository();
    Employee_Repository er = new Employee_Repository();
    Transactions_Repository tr = new Transactions_Repository();
    public Employee getEmployee(String firstname)
    {
        Employee employee = new Employee();
        if (er.getEmployeeByFirstname(firstname) != null)
            employee = er.getEmployeeByFirstname(firstname);
        return employee;
    }
    /**
     * Används vid "inloggningen"
     * @param SSNx
     * @return customer-objekt
     */
    public Customer getCustomer(String SSNx){ 
        Customer customer = cus.getCustomerBySSN(SSNx);
        
        return customer;
    }
    /**
     * delete customer in the sql database
     * based on its' SSN
     * @param SSNx 
     */
    public void deleteCustomer(String SSNx){
        cus.deleteCustomerBySSN(SSNx);
    }
    
    /**
     * Add/Change/Update customer information
     * @param SSN
     * @param firstname
     * @param lastname
     * @param telephoneNr
     * @param email
     * @param pincode 
     */
    public void UpdateCustomerInformation(String SSN, String firstname, String lastname, String telephoneNr,
            String email, int pincode)
    {
        cus.addCustomerInformation(SSN, firstname, lastname, telephoneNr, email, pincode);
    }
    
    public void addCustomer(String SSN, String firstname, String lastname, String telephoneNr,
            String email, int pincode, int employeeId)
    {
        cus.addCustomer(SSN, firstname, lastname, telephoneNr, email, pincode, employeeId); //får se med denna, anställd...
    }
    
    
        /**
     * create a customer account
     * @param SSNx
     * @param employeeName
     * @param accName
     * @param categoryName 
     */
    public void createCustomerAccount(String SSNx, String employeeName, String accName, String categoryName) {
        ar.createCustomerAccount(SSNx, employeeName, accName, categoryName);
    }
    /**
     * delete customer account
     * @param SSNx
     * @param accName 
     */
    public void deleteCustomerAccount(String SSNx, String accName) {
        ar.deleteCustomerAccount(SSNx, accName);
    }
    
    
    /**
     * Tar ut pengar från kunds konto
     * @param amount
     * @param accountId 
     */
    public void withdraw(int accountId, double amount)
    {
        tr.withdrawTransaction(accountId, amount);
    }
    
    /**
     * Sätter in pengar på kunds konto
     * @param amount
     * @param accountId 
     */
    public void deposit(int accountId, double amount)
    {
        tr.depositTransaction(accountId, amount);
    }
    
    public Account getAccountById(int id)
    {
        Account account = new Account();
        if (ar.getAccountById(id) != null)
            account = ar.getAccountById(id);
        System.out.println(account.getName());
        return account;
        
    }
    
    /**
     * Hämtar alla konton för en viss kund
     * @param SSN
     * @return List<Account>
     */
    public List<Account> getAllAccountsBySSN(String SSN) {
        return ar.getAllAccountsBySSN(SSN);
    }
    
    /**
     * Kontrollerar inloggning (pin och personnummer)
     * @param SSN
     * @param pin
     * @return true/false
     */
    public boolean checkPincode(String SSN, int pin) {
        return cus.checkPincode(SSN, pin);
    }
    
    public List<Transactions> getLatestTransactions(int accountId) {
        Calendar cal = Calendar.getInstance();
        
        cal.add(Calendar.MONTH, -1);
        Date aMonthBack = (Date) cal.getTime();
        return tr.getAllTransactionsFromLastMonth(accountId, new java.sql.Date(aMonthBack.getTime()));
    }
}

