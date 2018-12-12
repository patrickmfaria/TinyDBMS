package Disk;

import java.util.*;

public class Schema 
{
  /* pair<string,int> contain type(string) and position(int) */
  private Map<String, Pair> fields;
  private int num_of_strings, num_of_integers, tuples_per_block;

  public Schema(Vector<String> names, Vector<String> types, Disk disk) 
  {
    if(names.size() != types.size()){
      throw new IllegalArgumentException("Error! creating schema, wrong arguments");
    }
    
    num_of_integers = 0;
    num_of_strings = 0;
    
    fields = new HashMap<String, Pair>();//Here I use HashMap to implement Map
    
    //If you have a better implementation for Map, you can use it
    
    for(int i=0;i<names.size();i++) {
     if (types.get(i).equalsIgnoreCase("int")) {
       fields.put(names.get(i), new Pair(types.get(i), num_of_integers));
       num_of_integers++;
       if(num_of_integers > disk.max_num_of_fields) 
       {
         throw new IndexOutOfBoundsException("Error! in Schema constructor, at most " + disk.max_num_of_fields + " intgere fields are allowed");
       }
     } else if(types.get(i).equalsIgnoreCase("string")) {
       fields.put(names.get(i), new Pair(types.get(i), num_of_strings));
       num_of_strings++;
       if(num_of_integers > disk.max_num_of_fields) {
         throw new IndexOutOfBoundsException("Error! in Schema constructor, at most " + disk.max_num_of_fields + " string fields are allowed");
       }
     } else {
       throw new IllegalArgumentException("Error! " + types.get(i) + " is not supported now");
     }
   }
    //tuples_per_block=2;
   tuples_per_block = disk.block_size / (num_of_integers + 2 * num_of_strings);
  }

  public void changeField(String oldName, String newName) {
    fields.put(newName, fields.remove(oldName));
  }

  public void printSchema(){
    Iterator<Map.Entry<String, Pair>> it;
    Map.Entry<String, Pair> temp;
    it = fields.entrySet().iterator();
    System.out.println("name\t type\t position");
    while (it.hasNext()) {
      temp = it.next();
      System.out.println(temp.getKey() + "\t" + temp.getValue().type + "\t" + temp.getValue().position);
    }
  }
  
  public Vector<String> getFields()
  {
    Vector<String> tmp_fields = new Vector<String>();
    Iterator<Map.Entry<String, Pair>> it;
    Map.Entry<String, Pair> temp;
    it = fields.entrySet().iterator();
    while (it.hasNext()) 
    {
      temp = it.next();
      tmp_fields.add(temp.getKey());
    }
    return tmp_fields;
  }
  
  public Vector<String> getTypes()
  {
    Vector<String> tmp_types = new Vector<String>();
    Iterator<Map.Entry<String, Pair>> it;
    Map.Entry<String, Pair> temp;
    it = fields.entrySet().iterator();
    while (it.hasNext()) 
    {
      temp = it.next();
      tmp_types.add(temp.getValue().type);
    }
    return tmp_types;
  }

  public String getType(String fieldName){
    return fields.get(fieldName).type;
  }

  public int getPos(String fieldName){
    return fields.get(fieldName).position;
  }

  public int getNumOfInt(){
    return num_of_integers;
  }
  
  // New method
  public boolean isFromSchema(String field)
  {
    Iterator<Map.Entry<String, Pair>> it;
    Map.Entry<String, Pair> temp;
    it = fields.entrySet().iterator();
    while (it.hasNext()) 
    {
      temp = it.next();
      if(temp.getKey().equalsIgnoreCase(field))
              return true;
    }
      return false;
  }

  public int getNumOfString(){
    return num_of_strings;
  }

  public int getTuplesPerBlock(){
    //return BLOCK_SIZE/(num_of_integers+2*num_of_strings);
    return tuples_per_block;
  }

  public class Pair {
    int position;
    String type;

    public Pair(String b, int a) {
      position = a;
      type = b;
    }
  }
}
