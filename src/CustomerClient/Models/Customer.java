
package CustomerClient.Models;

public class Customer {

    private int id;
    private String SSN;
    private String firstname;
    private String lastname;
    private String telephoneNr;
    private String email;
    private int pincode;
    private Employee employee;
    
    public Customer(int id, String SSN, String firstname, String lastname, String telephoneNr, 
            String email, int pincode, Employee employee){

        this.id = id;
        this.SSN = SSN;
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephoneNr = telephoneNr;
        this.email = email;
        this.pincode = pincode;
        this.employee = employee;
    }

    public Customer(){}
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTelephoneNr() {
        return telephoneNr;
    }

    public void setTelephoneNr(String telephoneNr) {
        this.telephoneNr = telephoneNr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
