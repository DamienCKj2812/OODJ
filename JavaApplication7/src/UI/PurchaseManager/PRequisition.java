/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.PurchaseManager;

import UI.Admin.AdminHomeUI;
import UI.Authentication.LoginUI;
import UI.PurchaseManager.PRequisition;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import models.Admin;
import models.PurchaseManager;
import models.PurchaseOrder;
import models.PurchaseOrderAction;
import models.Requisition;
import models.RequisitionAction;
import models.User;
import state.UserSession;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import javax.swing.SpinnerListModel;

/**
 *
 * @author zhuow
 */
public class PRequisition extends javax.swing.JFrame {

    private User user;
    private PurchaseManager purchaseManager;
    private List<PRequisition.ItemData> InventoryItems = new ArrayList<>();
    UserSession userState = UserSession.getInstance();
    Admin admin = userState.getLoggedInAdmin();
    /**
     * Creates new form PurchaseManagerDashBoard
     */
    public PRequisition(User user) {
        this.user = user;
        initComponents();
        loadInventoryData();
        setupQuantitySpinner();
        setupStatusSpinner();

        if (admin != null) {
            adminpageButton.setVisible(true);
        } else {
            adminpageButton.setVisible(false);
        }
    }
    
    private void loadInventoryData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/InventoryData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] itemDetails = line.split("\\|");
                if (itemDetails.length >= 5) {
                    String itemCode = itemDetails[0];
                    String itemName = itemDetails[1];
                    double unitPrice = Double.parseDouble(itemDetails[3]);
                    int quantityInStock = Integer.parseInt(itemDetails[4]);
                    InventoryItems.add(new PRequisition.ItemData(itemCode, itemName, unitPrice, quantityInStock));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading inventory data: " + e.getMessage());
        }
    }
    
    private class ItemData {
        private String itemCode;
        private String itemName;
        private double unitPrice;
        private int quantityInStock;

        public ItemData(String itemCode, String itemName, double unitPrice, int quantityInStock) {
            this.itemCode = itemCode;
            this.itemName = itemName;
            this.unitPrice = unitPrice;
            this.quantityInStock = quantityInStock;
        }

        public String getItemCode() {
            return itemCode;
        }

        public void setItemCode(String itemCode) {
            this.itemCode = itemCode;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public int getQuantityInStock() {
            return quantityInStock;
        }

        public void setQuantityInStock(int quantityInStock) {
            this.quantityInStock = quantityInStock;
        }
    }
    
    private void displaySelectedItemDetails(String itemCode) {
        for (PRequisition.ItemData item : InventoryItems) {
            if (item.getItemCode().equals(itemCode)) {
                // Set the item details in the text fields
                ItemNameTextfield.setText(item.getItemName());
                break;
            }
        }
    }
    
    private void setupQuantitySpinner() {
        QuantitySpinner.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
    }
    
    private void setupStatusSpinner() {
    StatusSpinner = new javax.swing.JSpinner();
    // Assuming you're using an array of strings for statuses.
    SpinnerListModel statusModel = new SpinnerListModel(Arrays.asList("Pending", "Approved", "Rejected"));
    StatusSpinner.setModel(statusModel);
    
    // Add ChangeListener to handle status change
    StatusSpinner.addChangeListener(e -> {
        String selectedStatus = (String) StatusSpinner.getValue();
        System.out.println("Status selected: " + selectedStatus);
    });

    // Add the spinner to your form (example using GroupLayout)
    StatusSpinner.setBounds(150, 300, 120, 30); // Adjust bounds as per your UI
    this.add(StatusSpinner);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        PRlabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        adminpageButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ItemCodeComboBox = new javax.swing.JComboBox<>();
        ItemNameTextfield = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        QuantitySpinner = new javax.swing.JSpinner();
        CreateRequisitionButton = new javax.swing.JButton();
        ViewRequisitionButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        backtodashboardbutton = new javax.swing.JButton();
        logoutbutton = new javax.swing.JButton();
        RequiredDateTextfield = new javax.swing.JTextField();
        StatusLabel = new javax.swing.JLabel();
        StatusSpinner = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));

        PRlabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        PRlabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/purchase requisition.png"))); // NOI18N
        PRlabel.setText("Purchase Requisition");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PRlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PRlabel)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("WELCOME!");

        adminpageButton.setText("Admin Page");
        adminpageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminpageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adminpageButton)
                    .addComponent(jLabel3))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                .addComponent(adminpageButton)
                .addGap(46, 46, 46))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Item Code");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Quantity");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Item Name");

        ItemCodeComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ItemCodeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ItemCodeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemCodeComboBoxActionPerformed(evt);
            }
        });

        ItemNameTextfield.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Required Date");

        QuantitySpinner.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        CreateRequisitionButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CreateRequisitionButton.setText("Create");
        CreateRequisitionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateRequisitionButtonActionPerformed(evt);
            }
        });

        ViewRequisitionButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ViewRequisitionButton.setText("View");
        ViewRequisitionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewRequisitionButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Click \"View\" Button to view the list of requisition.");

        backtodashboardbutton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        backtodashboardbutton.setText("Back to Dashboard");
        backtodashboardbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backtodashboardbuttonActionPerformed(evt);
            }
        });

        logoutbutton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        logoutbutton.setText("LOGOUT");
        logoutbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutbuttonActionPerformed(evt);
            }
        });

        RequiredDateTextfield.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        StatusLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        StatusLabel.setText("Status");

        StatusSpinner.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ItemCodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(QuantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StatusSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 436, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(197, 197, 197)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(RequiredDateTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ItemNameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewRequisitionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logoutbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CreateRequisitionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(backtodashboardbutton))
                        .addGap(133, 133, 133))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(CreateRequisitionButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ItemNameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ItemCodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(backtodashboardbutton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addGap(55, 55, 55)
                                .addComponent(logoutbutton)
                                .addGap(34, 34, 34))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(RequiredDateTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(QuantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(34, 34, 34))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(StatusLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(StatusSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ViewRequisitionButton)
                                .addGap(13, 13, 13))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutbuttonActionPerformed
        new LoginUI().setVisible(true);
        this.dispose();
        userState.setLoggedInAdmin(null);
    }//GEN-LAST:event_logoutbuttonActionPerformed

    private void backtodashboardbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backtodashboardbuttonActionPerformed
        new PurchaseManagerDashBoard(purchaseManager).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backtodashboardbuttonActionPerformed

    private void adminpageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminpageButtonActionPerformed
        new AdminHomeUI(admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_adminpageButtonActionPerformed

    private void ViewRequisitionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewRequisitionButtonActionPerformed
        new ViewPRequisition(purchaseManager).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ViewRequisitionButtonActionPerformed

    private void ItemCodeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemCodeComboBoxActionPerformed
        ItemCodeComboBox.addItem("Please choose an item");
        for (PRequisition.ItemData item : InventoryItems) {
            ItemCodeComboBox.addItem(item.getItemCode());
        }

        ItemCodeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedCode = (String) ItemCodeComboBox.getSelectedItem();
                displaySelectedItemDetails(selectedCode);
            }
        });
    }//GEN-LAST:event_ItemCodeComboBoxActionPerformed

    private void CreateRequisitionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateRequisitionButtonActionPerformed
        try {
        // Get data from UI components
        String selectedItemId = (String) ItemCodeComboBox.getSelectedItem();
        String quantity = QuantitySpinner.getValue().toString();
        String requiredDate = RequiredDateTextfield.getText();
        String purchaseManagerId = "P001"; // Replace with actual logged-in Purchase Manager ID
        String requisitionDate = String.valueOf(System.currentTimeMillis()); // Current time in milliseconds
        String status = (String) StatusSpinner.getValue(); // Get the selected status

        // Validate inputs
        if (selectedItemId == null || selectedItemId.equals("Please choose an item")) {
            JOptionPane.showMessageDialog(this, "Please select an item.");
            return;
        }

        if (quantity.isEmpty() || Integer.parseInt(quantity) <= 0) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity.");
            return;
        }

        if (requiredDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a required date.");
            return;
        }
        
        if (status == null || status.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a status.");
            return;
        }

        // Add the requisition using PurchaseManager
        PurchaseManager purchaseManager = new PurchaseManager("P001", "purchaseManager", "password");
        purchaseManager.addRequisition(selectedItemId, quantity, requiredDate, purchaseManagerId, requisitionDate, status);

        // Notify the user
        JOptionPane.showMessageDialog(this, "Requisition created successfully!");

    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error creating requisition: " + ex.getMessage());
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid quantity format.");
    }
    }//GEN-LAST:event_CreateRequisitionButtonActionPerformed

    private boolean isValidDateFormat(String date) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setLenient(false);
    try {
        sdf.parse(date);
        return true;
    } catch (ParseException e) {
        return false;
    }
    }
    
    private void clearInputFields() {
    ItemCodeComboBox.setSelectedIndex(0);
    QuantitySpinner.setValue(0);
    RequiredDateTextfield.setText(""); // Clear the date entry field
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Requisition.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Requisition.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Requisition.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Requisition.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CreateRequisitionButton;
    private javax.swing.JComboBox<String> ItemCodeComboBox;
    private javax.swing.JTextField ItemNameTextfield;
    private javax.swing.JLabel PRlabel;
    private javax.swing.JSpinner QuantitySpinner;
    private javax.swing.JTextField RequiredDateTextfield;
    private javax.swing.JLabel StatusLabel;
    private javax.swing.JSpinner StatusSpinner;
    private javax.swing.JButton ViewRequisitionButton;
    private javax.swing.JButton adminpageButton;
    private javax.swing.JButton backtodashboardbutton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton logoutbutton;
    // End of variables declaration//GEN-END:variables
}
