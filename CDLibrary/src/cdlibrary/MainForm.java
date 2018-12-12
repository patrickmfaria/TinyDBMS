package cdlibrary;

import javax.swing.JOptionPane;

/*
 * MainForm.java
 *
 * Created on March 9, 2009, 9:23 AM
 */



/**
 *
 * @author  Vasilis Hadjisophocle
 */
public class MainForm extends javax.swing.JFrame {
    
    CDStore testStore = new CDStore("CD Library");
    
    /** Creates new form MainForm */
    public MainForm() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldLimit = new javax.swing.JTextField();
        jButtonCreatePerson = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaStatus = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldTitle = new javax.swing.JTextField();
        jTextFieldArtist = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButtonAddCD = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButtonTakeOut = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jComboBoxPerson = new javax.swing.JComboBox();
        jComboBoxCD = new javax.swing.JComboBox();
        jButtonTakeIn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CD Library Assignment 2");
        setResizable(false);

        jLabel1.setText("Enter the Name:");

        jLabel2.setText("Enter Limit:");

        jButtonCreatePerson.setText("Add Person");
        jButtonCreatePerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreatePersonActionPerformed(evt);
            }
        });

        jTextAreaStatus.setColumns(20);
        jTextAreaStatus.setEditable(false);
        jTextAreaStatus.setRows(5);
        jScrollPane1.setViewportView(jTextAreaStatus);

        jLabel3.setText("Enter the Title:");

        jLabel4.setText("Enter the Artist:");

        jButtonAddCD.setText("Add CD");
        jButtonAddCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddCDActionPerformed(evt);
            }
        });

        jLabel5.setText("Choose the Person:");

        jLabel6.setText("Choose the CD:");

        jButtonTakeOut.setLabel("Take Out");
        jButtonTakeOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTakeOutActionPerformed(evt);
            }
        });

        jButtonTakeIn.setActionCommand("Take In");
        jButtonTakeIn.setLabel("Take In");
        jButtonTakeIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTakeInActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setText("New");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Save");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Load");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel1)
                                    .add(jLabel2))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(jTextFieldName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 134, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 132, Short.MAX_VALUE))
                                    .add(layout.createSequentialGroup()
                                        .add(jTextFieldLimit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 114, Short.MAX_VALUE)
                                        .add(jButtonCreatePerson, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 106, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel5)
                            .add(jLabel6))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(jComboBoxCD, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jComboBoxPerson, 0, 135, Short.MAX_VALUE))
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jButtonTakeOut, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .add(jButtonTakeIn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 103, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel3)
                                    .add(jLabel4))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextFieldArtist)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextFieldTitle, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 38, Short.MAX_VALUE)
                                .add(jButtonAddCD, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                .add(18, 18, 18)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 334, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(20, 20, 20)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel2))
                            .add(layout.createSequentialGroup()
                                .add(jTextFieldName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jTextFieldLimit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jButtonCreatePerson))))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel3)
                            .add(jTextFieldTitle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel4)
                            .add(jTextFieldArtist, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jButtonAddCD))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jComboBoxPerson, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel5)
                            .add(jButtonTakeOut))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel6)
                            .add(jComboBoxCD, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jButtonTakeIn)))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButtonCreatePersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreatePersonActionPerformed
    // Verifiy fullfillment
    if(jTextFieldName.getText().length()<=0 || jTextFieldLimit.getText().length() <=0)
    {
         JOptionPane.showMessageDialog(null, "You must enter with name and limit", "ERROR", JOptionPane.ERROR_MESSAGE);
         return;
    }

    // Create new person
    Person p = new Person();
    p.setName(jTextFieldName.getText());
    p.setLimit(Integer.parseInt(jTextFieldLimit.getText()));
    testStore.addPerson(p);
    
    // Update the status
    jTextAreaStatus.setText(testStore.getStatus());
    
    // Add to the combobox
    jComboBoxPerson.addItem(p);
    
    // Clear the fieds
    jTextFieldName.setText("");
    jTextFieldLimit.setText("");
}//GEN-LAST:event_jButtonCreatePersonActionPerformed

private void jButtonAddCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddCDActionPerformed
    // Verify if the fields are empty
    if(jTextFieldTitle.getText().length()<=0 || jTextFieldArtist.getText().length() <=0)
    {
         JOptionPane.showMessageDialog(null, "You must enter with artist and title", "ERROR", JOptionPane.ERROR_MESSAGE);
         return;
    }
    // Create a new CD
    CD c = new CD(jTextFieldTitle.getText());
    c.setArtist(jTextFieldArtist.getText());
    testStore.addDisc(c);
    // Update the status field
    jTextAreaStatus.setText(testStore.getStatus());
    
    // Add to the combobox
    jComboBoxCD.addItem(c);
    
    // Clear fields
    jTextFieldTitle.setText("");
    jTextFieldArtist.setText("");

}//GEN-LAST:event_jButtonAddCDActionPerformed

private void jButtonTakeOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTakeOutActionPerformed
    
    if (jComboBoxCD.getSelectedItem() == null || jComboBoxPerson.getSelectedItem() == null)
    {
         JOptionPane.showMessageDialog(null, "You must choose the person and CD", "ERROR", JOptionPane.ERROR_MESSAGE);
         return;
    }
    
    // Get the person and cd selected
    CD pcd = (CD)jComboBoxCD.getSelectedItem();
    Person pperson = (Person)jComboBoxPerson.getSelectedItem();
    
    // Perfom the action
    if(!testStore.takeOut(pcd, pperson))
         JOptionPane.showMessageDialog(null, "Take Out Failed: Either this Person has exceed the imit or this CD is already taked out", "ERROR", JOptionPane.ERROR_MESSAGE);
    
    // Update the satus field
    jTextAreaStatus.setText(testStore.getStatus());
    
}//GEN-LAST:event_jButtonTakeOutActionPerformed

private void jButtonTakeInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTakeInActionPerformed
    if (jComboBoxCD.getSelectedItem() == null )
    {
         JOptionPane.showMessageDialog(null, "You must choose the CD", "ERROR", JOptionPane.ERROR_MESSAGE);
         return;
    }
    CD pcd = (CD)jComboBoxCD.getSelectedItem();
    if(!testStore.takeIn(pcd))
         JOptionPane.showMessageDialog(null, "Take In Failed: This CD is not Lended", "ERROR", JOptionPane.ERROR_MESSAGE);
    jTextAreaStatus.setText(testStore.getStatus());

}//GEN-LAST:event_jButtonTakeInActionPerformed

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    testStore = null;
    testStore = new CDStore("CD Library");
    JOptionPane.showMessageDialog(null, "Database has been initialized", "INFO", JOptionPane.INFORMATION_MESSAGE);
    jTextAreaStatus.setText(testStore.getStatus());
   
}//GEN-LAST:event_jMenuItem1ActionPerformed

private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    testStore.saveCDStoreToSerialFile("cdlibrary.dat");
    JOptionPane.showMessageDialog(null, "Database has been Saved", "INFO", JOptionPane.INFORMATION_MESSAGE);
    jTextAreaStatus.setText(testStore.getStatus());
            
}//GEN-LAST:event_jMenuItem2ActionPerformed

private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    testStore =  testStore.loadCDStoreFromSerialFile("cdlibrary.dat");
    
     for (CD tCD : testStore.discsArray) 
        jComboBoxCD.addItem(tCD);
    
     for (Person tperson : testStore.peopleArray) 
        jComboBoxPerson.addItem(tperson);

     JOptionPane.showMessageDialog(null, "Database has been Loaded", "INFO", JOptionPane.INFORMATION_MESSAGE);
    jTextAreaStatus.setText(testStore.getStatus());
}//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddCD;
    private javax.swing.JButton jButtonCreatePerson;
    private javax.swing.JButton jButtonTakeIn;
    private javax.swing.JButton jButtonTakeOut;
    private javax.swing.JComboBox jComboBoxCD;
    private javax.swing.JComboBox jComboBoxPerson;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTextAreaStatus;
    private javax.swing.JTextField jTextFieldArtist;
    private javax.swing.JTextField jTextFieldLimit;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldTitle;
    // End of variables declaration//GEN-END:variables

}