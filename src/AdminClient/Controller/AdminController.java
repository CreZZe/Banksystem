/*
 * JavaUtveckling 2018
 */

package AdminClient.Controller;

import AdminClient.Models.Account;
import AdminClient.Models.Customer;
import AdminClient.Models.Employee;
import AdminClient.Repository.Customer_Repository;
import AdminClient.Repository.Category_Repository;
import AdminClient.Repository.Account_Repository;
import AdminClient.Repository.Employee_Repository;
import AdminClient.Repository.Transactions_Repository;


public class AdminController { //Se över användandet av String vs voidmetoder

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
        Customer customer = new Customer();
        if (cus.getCustomerBySSN(SSNx) != null)
        {
            System.out.println("hallå");
            customer = cus.getCustomerBySSN(SSNx);
            System.out.println("WOOPWOOP");
        }
        System.out.println(customer.getFirstname());
        //Customer customer = cus.getCustomerBySSN(SSNx);
        
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
        System.out.println(SSN + " " + firstname + " " + lastname + " " + telephoneNr + " " + email + " " + pincode);
        
        cus.addCustomerInformation(SSN, firstname, lastname, telephoneNr, email, pincode);
        System.out.println("klart!");
    } //TODO
    
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
        System.out.println("hej");
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
    public void changeInterest(int id, double interest)
    {
        cr.changeInterest(id, interest);
    }
    
}
