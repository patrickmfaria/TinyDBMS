package Disk;

import java.util.*;

public class Tuple {
  private Vector<Integer> i;  //stores integers
  private Vector<String> s; // stores strings

  public Tuple(Schema schema) {
    int j;
    i = new Vector<Integer>(schema.getNumOfInt());
    s = new Vector<String>(schema.getNumOfString());
    for (j=0;j<schema.getNumOfInt();j++)
      i.add(null);
    for (j=0;j<schema.getNumOfString();j++)
      s.add(null);
  }

  public Tuple(Tuple tuple) {
    int j;
    i = new Vector<Integer>(tuple.i.size());
    s = new Vector<String>(tuple.s.size());
    for (j=0;j<tuple.i.size();j++)
      i.add(new Integer(tuple.i.get(j)));
    for (j=0;j<tuple.s.size();j++)
      s.add(new String(tuple.s.get(j)));
  }

  public void setInt(int pos, int val){
    if (pos < i.size() && pos >= 0) {
      i.set(pos, val);
    } else {
      throw new IndexOutOfBoundsException("Error: in setInt, pos " + pos + " is out of boundary!");
    }
  }

  public void setString(int pos, String val) {
    if (pos < s.size() && pos >= 0) {
      s.set(pos, val);
    } else {
      throw new IndexOutOfBoundsException("Error: in setString, pos " + pos + " is out of boundary!");
    }
  } 

  public void setInts(Vector<Integer> vals){
    if(i.size() == vals.size()){
      i = vals;
    } else {
      throw new IllegalArgumentException("Error: in setInts, incorrect size");
    }
  }

  public void setStrings(Vector<String> vals){
    if(s.size() == vals.size()){
      s = vals;
    } else {
      throw new IllegalArgumentException("Error: in setStrings, incorrect size");
    }
  }

  public int getSizeInt()
  {
      return i.size();
  }
  
  public int getSizeString()
  {
      return s.size();
  }
  
  public int getInt(int pos){
    if(pos < i.size() && pos >= 0){
      return i.get(pos);
    } else {
      throw new IndexOutOfBoundsException("Error: in getInt, pos " + pos + " is out of boundary!");
    }
  }

  public String getString(int pos){
    if(pos < s.size() && pos >= 0){
      return s.get(pos);
    } else {
      throw new IndexOutOfBoundsException("Error: in getString, pos " + pos + " is out of boundary!");
    }
  }

  public void printTuple(){
    int j;
    for(j=0;j<i.size();j++){
      System.out.print(i.get(j) + "\t");
    }
    for(j=0;j<s.size();j++){
      System.out.print(s.get(j) + "\t");
    }
    System.out.print("\n");
  }
}
