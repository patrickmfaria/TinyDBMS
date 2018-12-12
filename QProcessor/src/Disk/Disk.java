/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Disk;

public class Disk {
  public int block_size;
  public int max_num_of_fields;
  private int delay_time;
  private int blocksRead;
  private int blocksWritten;

  public Disk(int arg0, int arg1, int arg2) {
    block_size = arg0;
    max_num_of_fields = arg1;
    delay_time = arg2;
    blocksRead = 0;
    blocksWritten = 0;
  }


  public void setDelay(int delay) {
    delay_time = delay;
  }

  public void delay() {
    try {
      Thread.sleep(delay_time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void readBlock() {
    blocksRead++;
  }

  public void writeBlock() {
    blocksWritten++;
  }

  public int blocksRead() {
    return blocksRead;
  }

  public int blocksWritten() {
    return blocksWritten;
  }
}
