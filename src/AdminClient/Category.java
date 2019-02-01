/*
 * JavaUtveckling 2018
 */

package AdminClient;


public class Category {

    private int id;
    private String name;
    private double interest;
    
 public Category(int id, String name, double interest){

        this.id = id;
        this.name = name;
        this.interest = interest;
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
}
