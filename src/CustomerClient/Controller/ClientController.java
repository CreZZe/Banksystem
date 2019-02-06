/*
 * JavaUtveckling 2018
 */

package CustomerClient.Controller;

import CustomerClient.Repository.Account_Repository;
import CustomerClient.Repository.Category_Repository;
import CustomerClient.Repository.Customer_Repository;
import CustomerClient.Repository.Employee_Repository;
import CustomerClient.Repository.Transactions_Repository;


public class ClientController {

    Account_Repository ar = new Account_Repository();
    Category_Repository cr = new Category_Repository();
    Customer_Repository cus = new Customer_Repository();
    Employee_Repository er = new Employee_Repository();
    Transactions_Repository tr = new Transactions_Repository();
}
