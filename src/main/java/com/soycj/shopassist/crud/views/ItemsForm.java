package com.soycj.shopassist.crud.views;
import com.soycj.shopassist.crud.DAO.ItemsDAO;
import com.soycj.shopassist.crud.DAO.exceptions.NonexistentEntityException;
import com.soycj.shopassist.crud.models.Items;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
/**
 *
 * @author elnor
 */
public class ItemsForm extends javax.swing.JFrame {

    private Items item = null;
    private ItemsDAO itemDAO;
    
    public ItemsForm() {
        initComponents();
        try{
            itemDAO = new ItemsDAO();
        }catch(Exception e)
        {

            JOptionPane.showMessageDialog(null, 
                    "Couldn't connect to database!", 
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
            
            System.exit(-1);
        }
    }
    
    private void clearAllFields()
    {
        txtAddCode.setText("");
        txtAddDescription.setText("");
        txtAddName.setText("");
        txtAddTax.setText("");
        txtAddUnitPrice.setText("");
        txtAddPosition.setText("");
        
        txtUpdateCode.setText("");
        txtUpdateDescription.setText("");
        txtUpdateName.setText("");
        txtUpdateTax.setText("");
        txtUpdateUnitPrice.setText("");
        txtUpdatePosition.setText("");
        
        txtDeleteCode.setText("");
        
        updateFieldsToggle(false);
    }

    private void saveItem()
    {
        try{
            item = new Items();
            item.setCode(txtAddCode.getText());
            item.setDescription(txtAddDescription.getText());
            item.setName(txtAddName.getText());
            item.setPosition(txtAddPosition.getText());
            item.setUnitPrice(new BigDecimal(txtAddUnitPrice.getText()));
            item.setTaxRate(new BigDecimal(txtAddTax.getText()));

            itemDAO.create(item);

            JOptionPane.showMessageDialog(this, 
                        "Item added successfully", 
                        "Error!", JOptionPane.INFORMATION_MESSAGE);
            
            clearAllFields();
            updateFieldsToggle(false);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, 
                    "Couldn't not create item!", 
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void updateFieldsToggle(boolean estate)
    {
        btnUpdate.setEnabled(estate);
        txtUpdateDescription.setEditable(estate);
        txtUpdateName.setEditable(estate);
        txtUpdateTax.setEditable(estate);
        txtUpdateUnitPrice.setEditable(estate);
        txtUpdatePosition.setEditable(estate);
        
    }
    
    private void updateItem()
    {
        item.setName(txtUpdateName.getText());
        item.setDescription(txtUpdateDescription.getText());
        item.setPosition(txtUpdatePosition.getText());
        item.setUnitPrice(new BigDecimal(txtUpdateUnitPrice.getText()));
        item.setTaxRate(new BigDecimal(txtUpdateTax.getText()));
        
        try
        {
            itemDAO.edit(item);
            clearAllFields();
            updateFieldsToggle(false);
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, 
                    "Couldn't update item!", 
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void searchItem()
    {
        try
        {
            item = itemDAO.findByCode(txtUpdateCode.getText());
            
            if(item == null)
                throw new NonexistentEntityException("Item not found!");
            
            updateFieldsToggle(true);
            
            txtUpdateDescription.setText(item.getDescription());
            txtUpdateName.setText(item.getName());
            txtUpdateTax.setText(item.getTaxRate().toPlainString());
            txtUpdateUnitPrice.setText(item.getUnitPrice().toPlainString());
            txtUpdatePosition.setText(item.getPosition());
        }
        catch(NonexistentEntityException e)
        {
            JOptionPane.showMessageDialog(this, 
                    e.getMessage(),
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void deleteItem()
    {
        try
        {
            itemDAO.destroy(
                    itemDAO.findByCode(txtDeleteCode.getText()).getId()
            );
            
            JOptionPane.showMessageDialog(this, 
                    "Item deleted successfully.", 
                    "", JOptionPane.INFORMATION_MESSAGE);
            clearAllFields();
            updateFieldsToggle(false);
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, 
                    "Error deleting item!", 
                    "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtAddCode = new javax.swing.JTextField();
        txtAddName = new javax.swing.JTextField();
        txtAddDescription = new javax.swing.JTextField();
        txtAddUnitPrice = new javax.swing.JTextField();
        txtAddTax = new javax.swing.JTextField();
        txtAddPosition = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtUpdateCode = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtUpdateName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtUpdateDescription = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtUpdateUnitPrice = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtUpdateTax = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtUpdatePosition = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtDeleteCode = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Items manager");

        jLabel1.setText("Item Code:");

        jLabel2.setText("Name:");

        jLabel3.setText("Description");

        jLabel4.setText("Unit Price:");

        jLabel5.setText("Tax:");

        btnSave.setText("Save");
        btnSave.setNextFocusableComponent(btnClear);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.setNextFocusableComponent(txtAddCode);
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel6.setText("Position");

        txtAddCode.setNextFocusableComponent(txtAddName);

        txtAddName.setNextFocusableComponent(txtAddDescription);

        txtAddDescription.setNextFocusableComponent(txtAddUnitPrice);

        txtAddUnitPrice.setNextFocusableComponent(txtAddTax);

        txtAddTax.setNextFocusableComponent(txtAddPosition);

        txtAddPosition.setNextFocusableComponent(btnSave);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtAddName, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtAddCode, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAddTax, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAddUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAddDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAddPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(btnSave)
                        .addGap(71, 71, 71)
                        .addComponent(btnClear))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtAddCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtAddName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAddDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAddUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtAddTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtAddPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnClear))
                .addGap(32, 32, 32))
        );

        jTabbedPane1.addTab("Add Item", jPanel1);

        jLabel7.setText("Item Code:");

        txtUpdateCode.setNextFocusableComponent(btnSearch);
        txtUpdateCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUpdateCodeKeyPressed(evt);
            }
        });

        jLabel8.setText("Name:");

        txtUpdateName.setEditable(false);
        txtUpdateName.setNextFocusableComponent(txtUpdateDescription);

        jLabel9.setText("Description");

        txtUpdateDescription.setEditable(false);
        txtUpdateDescription.setNextFocusableComponent(txtUpdateUnitPrice);

        jLabel10.setText("Unit Price:");

        txtUpdateUnitPrice.setEditable(false);
        txtUpdateUnitPrice.setNextFocusableComponent(txtUpdateTax);

        jLabel11.setText("Tax:");

        txtUpdateTax.setEditable(false);
        txtUpdateTax.setNextFocusableComponent(txtUpdatePosition);

        jLabel12.setText("Position");

        txtUpdatePosition.setEditable(false);
        txtUpdatePosition.setNextFocusableComponent(btnUpdate);

        btnSearch.setText("Search");
        btnSearch.setNextFocusableComponent(txtUpdateName);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.setEnabled(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtUpdateCode, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(41, 41, 41)
                                .addComponent(txtUpdateName, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUpdateDescription)
                                    .addComponent(txtUpdateUnitPrice, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtUpdateTax, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtUpdatePosition, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(btnUpdate)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtUpdateCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtUpdateName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtUpdateDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtUpdateUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtUpdateTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtUpdatePosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnUpdate)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Modify Item", jPanel2);

        jLabel13.setText("Item Code:");

        txtDeleteCode.setNextFocusableComponent(btnDelete);
        txtDeleteCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDeleteCodeKeyPressed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtDeleteCode, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(btnDelete)))
                .addContainerGap(143, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtDeleteCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Delete Item", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearAllFields();
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtDeleteCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDeleteCodeKeyPressed
        if(evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            deleteItem();
        }
    }//GEN-LAST:event_txtDeleteCodeKeyPressed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteItem();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtUpdateCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUpdateCodeKeyPressed
        if(evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            searchItem();
        }
    }//GEN-LAST:event_txtUpdateCodeKeyPressed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateItem();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        saveItem();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        searchItem();
    }//GEN-LAST:event_btnSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField txtAddCode;
    private javax.swing.JTextField txtAddDescription;
    private javax.swing.JTextField txtAddName;
    private javax.swing.JTextField txtAddPosition;
    private javax.swing.JTextField txtAddTax;
    private javax.swing.JTextField txtAddUnitPrice;
    private javax.swing.JTextField txtDeleteCode;
    private javax.swing.JTextField txtUpdateCode;
    private javax.swing.JTextField txtUpdateDescription;
    private javax.swing.JTextField txtUpdateName;
    private javax.swing.JTextField txtUpdatePosition;
    private javax.swing.JTextField txtUpdateTax;
    private javax.swing.JTextField txtUpdateUnitPrice;
    // End of variables declaration//GEN-END:variables
}
