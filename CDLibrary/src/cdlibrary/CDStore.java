package cdlibrary;

import java.util.ArrayList;
import java.io.*;

/**
 *
 * @author Vasilis Hadjisophocle
 */
public class CDStore implements Serializable {
    String name;
    ArrayList<CD> discsArray;
    ArrayList<Person> peopleArray;
    
    public CDStore(String name) {
        this.name = name;
        discsArray = new ArrayList<CD>() ;
        peopleArray = new ArrayList<Person>();
    }
    
    public String getName() {
        return name;
    }
    
    public ArrayList<CD> getDiscs() {
        return discsArray;
    }
    
    public ArrayList<Person> getPeople() {
        return peopleArray;
    }
    
    public void addDisc(CD aCD) {
        discsArray.add(aCD);
    }
    
    public void removeDisc(CD aCD) {
        discsArray.remove(aCD);
    }
    
    public void addPerson(Person aPerson) {
        peopleArray.add(aPerson);
    }


    public void removePerson(Person aPerson) {
        peopleArray.remove(aPerson);
    }
    
    public boolean takeOut(CD aCD, Person aPerson) {
        // Verifu=y the limit
        if (getDiscsForPerson(aPerson).size() >= aPerson.getCDLimit()) 
                return false;
        // Find the CD
        for (CD tCD : discsArray) 
            if(tCD.equals(aCD))
                if(tCD.getPerson() != null) // If there is someone ...
                    return false;
                else
                {   // Assign the right person
                    aCD.setPerson(aPerson);
                    return true;
                }
        
        return false;
    }
    
    public boolean takeIn(CD aCD) {
        for (CD tCD : discsArray) 
            if(tCD.equals(aCD))
            {
                if (tCD.person != null) // Take CD in
                {
                    tCD.setPerson(null);
                    return true;
                }
                else return false; // CD hadn't been lended
            }
        return false; //  CD doens't exist
    }
    
    // Scan the CD array, verify if the person is the same of the parameter and add
    // into a new arraylist
    public ArrayList<CD> getDiscsForPerson(Person aPerson) {
        ArrayList<CD> discsforPesonArray = new ArrayList<CD>();
        for (CD tCD : discsArray) 
            if (tCD.getPerson() != null)
                if(tCD.getPerson().equals(aPerson))
                    discsforPesonArray.add(tCD);
        
        return discsforPesonArray;
        
    }
    
    // Scan all CDs checking with person = null, add into another array
public ArrayList<CD> getAvailableDiscs() {
        ArrayList<CD> discsAvalArray = new ArrayList<CD>();
        for (CD tCD : discsArray) 
            if(tCD.getPerson() == null)
                discsAvalArray.add(tCD);
        
        return discsAvalArray;
}

// The same of the previos but with the opposite comparation
public ArrayList<CD> getUnavailableDiscs() {
        ArrayList<CD> discsUnavalArray = new ArrayList<CD>();
        for (CD tCD : discsArray) 
            if(tCD.getPerson() != null)  // verify which cd has person assigned
                discsUnavalArray.add(tCD);
        
        return discsUnavalArray;
}

    @Override
public String toString() {
    return name + " - " + discsArray.size() + " - " + peopleArray.size();
}

public String getStatus(){
    StringBuffer buffer=new StringBuffer();
    buffer.append("Status Repost of " + name + "\n");
    buffer.append("-----------------------------------\n");
    buffer.append("CD Library: " + discsArray.size() + " discs; " + peopleArray.size() + " people. \n");
    for (CD tCD : discsArray) 
        buffer.append("CD Title = " +  tCD.getTitle() + " Artist = " + tCD.getArtist() + " \n");
    buffer.append("\n");
    for (Person tp : peopleArray) 
        buffer.append(tp.getName() + " (" + tp.getCDLimit() + " CDs) (has " + getDiscsForPerson(tp).size() + " of my CDs) \n");
    buffer.append("\n");
    buffer.append("CDs Available " + getAvailableDiscs().size() + "\n");
    buffer.append("---------------------------------------\n");
    return buffer.toString();
}

public void printStatus() {
    System.out.println(getStatus());
}

public void saveCDStoreToSerialFile(String fileName)
{ 
    try {
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
        try {
            oos.writeObject(this);
        } 
        finally {
            oos.close();
        }
    } catch (Exception ex) {
        ex.printStackTrace();

    }
    
}

public CDStore loadCDStoreFromSerialFile(String fileName)
{
    CDStore store = null;
    try {
        ObjectInputStream os = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(fileName)));
        try {
            Object obj = os.readObject();
            if (obj instanceof CDStore) {
                store = (CDStore) obj;
        }
        } finally {
            os.close();
        }
    } catch (Exception ex) {
        System.out.println("File not found");
    }
    return store;
}

public void savePeopleArray(String fileName) {
    try {
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        
        out.println("name,CDlimit");
        
        for (int i = 0; i < peopleArray.size(); i++) {
            Person p = (Person)peopleArray.get(i);
            String aName=p.getName();
            String aLimit=String.valueOf(p.getCDLimit());
            out.println(aName+","+aLimit);
        }
        out.close();
        bw.close();
        fw.close();
    } catch (Exception e) {
        String errMessage = e.getMessage();
        System.out.println("Error: " + errMessage);
    }
}

public void saveDiscsArray(String fileName) {
    try {
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        
        out.println("title,artist");
        
        for (int i = 0; i < discsArray.size(); i++) {
            CD cd = (CD)discsArray.get(i);
            String aTitle=cd.getTitle();
            String aArtist = cd.getArtist();
            out.println(aTitle+","+aArtist);
        }
        out.close();
        bw.close();
        fw.close();
    } catch (Exception e) {
        String errMessage = e.getMessage();
        System.out.println("Error: " + errMessage);
    }
}

public void loadPeopleArray(String fileName){
    try {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String str;
        boolean header = true;
        peopleArray.clear();
        while ((str = in.readLine()) != null) {
            if(!header)
            {
                String [] temp = null;
                // Read the line and split by ","
                temp = str.split(",");
                Person p = new Person();
                // The fist is name and the second is the limit
                p.setName(temp[0]);
                p.setLimit(Integer.parseInt(temp[1]));
                peopleArray.add(p);
            }
            header = false;
        }
        in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
}

public void loadDiscsArray(String fileName) {
    try {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String str;
        boolean header = true;
        discsArray.clear();
        while ((str = in.readLine()) != null) {
            if(!header)
            {
                String [] temp = null;
                temp = str.split(",");
                CD c = new CD(temp[0]);
                c.setArtist(temp[1]);
                discsArray.add(c);
            }
            header = false;
        }
        in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
}

}
