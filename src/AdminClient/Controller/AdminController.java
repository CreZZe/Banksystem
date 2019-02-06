/*
 * JavaUtveckling 2018
 */

package AdminClient.Controller;

import AdminClient.Models.Customer;
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
            String email, int pincode)
    {
        cus.addCustomer(SSN, firstname, lastname, telephoneNr, email, pincode, pincode); //får se med denna, anställd...
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
    public void withdraw(double amount, int accountId)
    {
        tr.withdrawTransaction(amount, accountId);
    }
    
    /**
     * Sätter in pengar på kunds konto
     * @param amount
     * @param accountId 
     */
    public void deposit(double amount, int accountId)
    {
        tr.depositTransaction(amount, accountId);
    }
}
