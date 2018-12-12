package Disk;

import java.util.*;

public class Table {
  private List<Tuple> data;
  private int tuples_per_block, last_block_ind;
  private String table_name;
  private Disk disk;//This object stores the disk parameters

  public Table(String table_name, Schema schemaPtr, Disk d){
    this.table_name = table_name;
    tuples_per_block = schemaPtr.getTuplesPerBlock();
    last_block_ind = 0;  // blocks in a table start from 1
    data = new LinkedList<Tuple>();//Here I use LinkedList to implement List
    //If you have a better implementation for List, you can use it
    disk = d;
  }

  public String getTableName(){
    return table_name;
  }

  public int getTableSize(){
    return data.size();
  }

	/* read a block from table into tuples, 
	   return the actual numbers of tuples read */
  public int tableRead(List<Tuple> tuples, int block) {
    if (block > last_block_ind) 
    {
      throw new IndexOutOfBoundsException("ERROR: in tableRead, block " + block + " is out of boundary");
    }
    ListIterator<Tuple> it;
    int num, index;
    index = (block - 1) * tuples_per_block;
    it = data.listIterator(index);
    for (num=0;it.hasNext() && (num<tuples_per_block);num++) {//appends the tuples from this block to tuples
      tuples.add(it.next());
    }
    //With the block reads counter, this delay simulator is not necessary
    //disk.delay();
    disk.readBlock();//Increments the block reads counter
    //  cout<<"in tableRead, tuples.size="<<tuples.size()<<" num="<<num<<endl;
    return num;//returns the number of tuples read
  }

	/* write tuples to block in table */
  public void tableWrite(int block, List<Tuple> tuples) {
    if (last_block_ind < block - 1 || block < 1) {//if block is greater than last_block_ind or less than 1
      throw new IndexOutOfBoundsException("ERROR: in tableWrite, block " + block + " is out of boundary");
    }
    if (last_block_ind > block && tuples.size() < tuples_per_block) {
      throw new IllegalArgumentException("ERROR: in tableWrite, block " + block + " has too few tuples!");
    }
    //With the block writes counter, this delay simulator is not necessary
    //disk.delay();
    disk.writeBlock();//Increments the block writes counter
    int i, index;
    Iterator<Tuple> it;
    it = tuples.iterator();
    if (last_block_ind == block - 1) { // writing in the same block
      /* block is appneded to the last_block_ind */
      if (data.size() < last_block_ind * tuples_per_block) {
        throw new IllegalArgumentException("ERROR: in tableWrite, block " + last_block_ind + " is not full yet");
      } else {
        while (it.hasNext())
          data.add(new Tuple(it.next()));//adds a clone of it.next()
        last_block_ind++;
      }
    } else {
      index = (block - 1) * tuples_per_block;
      //for (i=0;it.hasNext() && (i<tuples_per_block) && (index+i < data.size());i++)
      //  data.set((index + i), new Tuple(it.next()));//sets data[index + i] to a clone of it.next()
      it = tuples.iterator();
      for (i=0;it.hasNext() && (i<tuples_per_block);i++)
        data.add(new Tuple(it.next()));//adds a clone of it.next()
    }
  }
}
