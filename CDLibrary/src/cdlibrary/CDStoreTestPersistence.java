
package cdlibrary;

/**
 *
 * @author Vasilis Hadjisophocle
 */
public class CDStoreTestPersistence {

    public static void main(String[] args) {
        // Initial test sequence
        // create a new CD store
        CDStore cds = new CDStore("Vasilis CD Store");
        
        // create CDs
        CD cd1 = new CD("Unplugged");
        // set artists
        cd1.setArtist("Alicia Keys");
        
        // create CDs
        CD cd2 = new CD("Subjects");
        // set artists
        cd2.setArtist("Maggie & Jam");

        // create people
        Person p1 = new Person();
        
        // set names of people
        p1.setName("Paul");
        p1.setLimit(3);
        
        // add CDs to CD store
        cds.addDisc(cd1);
        cds.addDisc(cd2);
        
        // add people to CD store
        cds.addPerson(p1);
        
        // print status of CD store
        cds.printStatus();
        
        cds.saveDiscsArray("Discs.txt");
        cds.savePeopleArray("people.txt");
        
        CDStore cds2 = new CDStore("Vasilis CD Store II");
        
        cds2.loadDiscsArray("Discs.txt");
        cds2.loadPeopleArray("people.txt");
        
        cds2.printStatus();
        
    }
}
