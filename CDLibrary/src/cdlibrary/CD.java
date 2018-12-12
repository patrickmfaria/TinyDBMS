package cdlibrary;

import java.io.Serializable;

/**
 *
 * @author Vasilis Hadjisophocle
 */
public class CD implements Serializable {
    String title;
    String artist;
    Person person; //add a person to the CD class (i.e. who has the CD)
    
    public CD(String aTitle) {
        title = aTitle;
        artist = "";
        person = null;
    }
    
    public String getArtist() {
        return artist;
    }
    
    public void setArtist(String aName) {
        artist = aName;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setPerson(Person aPerson) {
        person = aPerson;
    }
    
    public Person getPerson() {
        return person;
    }
    
    @Override
    public String toString()
    { 
        return title + " - " + artist ;            
    }
}
