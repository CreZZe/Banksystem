/*
 * JavaUtveckling 2018
 */

package AdminClient.Models;


public class Category {

    private int id;
    private String name;
    private double interest;
    private double amortization;
    
 public Category(int id, String name, double interest, double amortization){

        this.id = id;
        this.name = name;
        this.interest = interest;
        this.amortization = amortization;
    }

    public Category(){}
    
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

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }
    public double getAmortization() {
        return amortization;
    }

    public void setAmortization(double amortization) {
        this.amortization = amortization;
    }

}
