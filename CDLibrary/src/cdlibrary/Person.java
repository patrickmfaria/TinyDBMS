package cdlibrary;

import java.io.Serializable;

/**
 *
 * @author Vasilis Hadjisophocle
 */
public class Person implements Serializable{
    
    // fields
    private String name; // name of the person
    private int cdLimit; // maximum number of borrowed CDs
    
    public Person() {
        name = "";
        cdLimit = 0;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String aName) {
        name = aName;
    }
    
    public int getCDLimit() {
        return cdLimit;
    }
    
    public void setLimit(int maxNumber) {
        cdLimit = maxNumber;
    }
    
    @Override
    public String toString() {
        return name + " - " + cdLimit;
    }
}
