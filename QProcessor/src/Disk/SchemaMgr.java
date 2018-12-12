package Disk;

import java.util.*;

public class SchemaMgr
{
  private Map<String,Integer> mgr;
  private Vector<Infor> infors;
  private Disk disk;//This object stores the disk parameters

  public SchemaMgr(Disk d) {
    mgr = new HashMap<String,Integer>();//Here I use HashMap to implement Map
    //If you have a better implementation for Map, you can use it
    infors = new Vector<Infor>();
    disk = d;
  }

  public void addSchema(String tblName, Schema s) {
    int pos = infors.size(); 
    Table tb;
  //cout<<"in SchemaMgr::addSchema pos= "<<pos<<endl;
    tb = new Table(tblName, s, disk);
    infors.setSize(pos + 1);
    infors.set(pos, new Infor());
    infors.get(pos).tablePtr = tb;
  //cout<<"pos="<<pos<<endl;
    infors.get(pos).schemaPtr = s;
  //cout<<"pos="<<pos<<endl;
    mgr.put(tblName, pos);//inserts pos into the Map with tblName as the key
 // mgr[tblName]=pair<Table*,Schema*>(new Table(tblName,*s),s);
  }

  public Schema getSchema(String tblName){
    if (mgr.get(tblName) == null) {//If the key tblName is not in the map
      return null;
    } else {
      return infors.get(mgr.get(tblName)).schemaPtr;
    }
  }

  public Table getTable(String tblName){
    if (mgr.get(tblName) == null) {//If the key tblName is not in the map
      return null;
    } else{
      return infors.get(mgr.get(tblName)).tablePtr;
    }
  }

  public void rmSchema(String tblName){
    int pos = mgr.remove(tblName);//Removes the key tblName's entry from the Map and stores it as pos
    infors.remove(pos);//Removes the Infor at index pos from the infors Vector
  }
}