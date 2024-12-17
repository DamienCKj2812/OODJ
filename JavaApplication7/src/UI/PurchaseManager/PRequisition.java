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
import state.UserSession;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import javax.swing.SpinnerListModel;
import javax.swing.table.DefaultTableModel;
import models.Item;
import models.PurchaseOrder;
import utils.LogHandler;
import utils.StringFormatter;

/**
 *
 * @author zhuow
 */
public class PRequisition extends javax.swing.JFrame {

    private PurchaseManager purchaseManager;
    private UserSession userState = UserSession.getInstance();
    private Admin admin = userState.getLoggedInAdmin();
    private List<Item> items;
    private Item selectedItem;
    private LogHandler logHandler;

    /**
     * Creates new form PurchaseManagerDashBoard
     */
    public PRequisition(PurchaseManager purchaseManager) {
        this.purchaseManager = purchaseManager;
        this.logHandler = new LogHandler(purchaseManager);
        initComponents();
        loadData();
        loadItemTable();

        if (admin != null) {
            adminpageButton.setVisible(true);
        } else {
            adminpageButton.setVisible(false);
        }

        QuantitySpinner.addChangeListener(e -> {
            int value = (Integer) QuantitySpinner.getValue();
            if (value < 0) {
                QuantitySpinner.setValue(0); // Reset value to 0 if somehow it gets below 0
            }
        });

    }

    public void loadData() {
        try {
            ItemCodeComboBox.removeAllItems();
            items = purchaseManager.getInventoryItems();
            for (Item item : items) {
                ItemCodeComboBox.addItem(item.getItemID());
            }
            requiredDate.setDate(new Date());
            requiredDate.setDateFormatString("MMM dd, yyyy");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading inventory data: " + e.getMessage(),
                    "Load Data Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void setupQuantitySpinner() {
        // Set the spinner's initial value to 0, and restrict it from going below 0
        QuantitySpinner.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
    }

    public void loadItemTable() {
        String[] purchaseOrderColumnNames = {"Item ID", "Name", "Description", "Price", "Quantity", "Reorder Point", "Supplier ID"};
        DefaultTableModel itemTableModel = new DefaultTableModel(purchaseOrderColumnNames, 0);
        if (selectedItem != null) {
            itemTableModel.addRow(new Object[]{
                selectedItem.getItemID(),
                selectedItem.getName(),
                selectedItem.getDescription(),
                selectedItem.getPrice(),
                selectedItem.getQuantity(),
                selectedItem.getReorderPoint(),
                selectedItem.getSupplierID()});
        } else {
            itemTableModel.addRow(new Object[]{
                "Please Select an Item"
            });
        }

        itemTable.setModel(itemTableModel);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
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
        ItemCodeComboBox = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        QuantitySpinner = new javax.swing.JSpinner();
        CreateRequisitionButton = new javax.swing.JButton();
        ViewRequisitionButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        backtodashboardbutton = new javax.swing.JButton();
        logoutbutton = new javax.swing.JButton();
        requiredDate = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemTable = new javax.swing.JTable();

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(adminpageButton)
                .addGap(46, 46, 46))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Item Id");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Quantity");

        ItemCodeComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ItemCodeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemCodeComboBoxActionPerformed(evt);
            }
        });

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

        itemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(itemTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(QuantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ItemCodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(87, 87, 87)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(requiredDate, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(ViewRequisitionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logoutbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CreateRequisitionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(backtodashboardbutton))
                        .addGap(34, 34, 34))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addComponent(QuantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel9)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(requiredDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ItemCodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CreateRequisitionButton)
                                .addGap(61, 61, 61)
                                .addComponent(backtodashboardbutton)
                                .addGap(67, 67, 67)
                                .addComponent(logoutbutton)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ViewRequisitionButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addComponent(jLabel6)))
                        .addGap(38, 38, 38))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void convertDateToUnix() {

    }

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
        try {
            String selectedItemId = (String) ItemCodeComboBox.getSelectedItem();
            selectedItem = purchaseManager.getItem(selectedItemId);
            loadItemTable();
        } catch (Exception E) {
            System.out.println(E);
        }


    }//GEN-LAST:event_ItemCodeComboBoxActionPerformed

    private void CreateRequisitionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateRequisitionButtonActionPerformed
        // Validate item selection
        if (ItemCodeComboBox.getSelectedItem() == null || ItemCodeComboBox.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select an item code from the dropdown list.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
            return; // Stop execution
        }

        // Validate quantity
        int quantity = (Integer) QuantitySpinner.getValue();
        try {
            if (quantity <= Integer.parseInt(selectedItem.getReorderPoint())) {
                JOptionPane.showMessageDialog(this,
                        "Quantity must be greater than reorder point.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE);
                return; // Stop execution
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid reorder point value.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return; // Stop execution
        }

        // Validate the date selection and convert it to Unix timestamp
        Date selectedDate = requiredDate.getDate();
        if (selectedDate == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select a date.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return; // Stop execution
        }
        long unixTime = selectedDate.getTime() / 1000; // Convert milliseconds to seconds

        // Try to process the requisition creation
        try {
            purchaseManager.addRequisition(
                    selectedItem.getItemID(),
                    String.valueOf(quantity),
                    String.valueOf(unixTime),
                    purchaseManager.getUserID(),
                    String.valueOf(System.currentTimeMillis() / 1000)
            );

            JOptionPane.showMessageDialog(this,
                    "Requisition created successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            logHandler.addLogActionToFile(String.format(
                    "Added the Purchase requisition (item id: %s, item name: %s, item quantity: %s, required date: %s)",
                    selectedItem.getItemID(),
                    selectedItem.getName(),
                    String.valueOf(quantity),
                    new SimpleDateFormat("yyyy MM dd").format(selectedDate)
            ));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_CreateRequisitionButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CreateRequisitionButton;
    private javax.swing.JComboBox<String> ItemCodeComboBox;
    private javax.swing.JLabel PRlabel;
    private javax.swing.JSpinner QuantitySpinner;
    private javax.swing.JButton ViewRequisitionButton;
    private javax.swing.JButton adminpageButton;
    private javax.swing.JButton backtodashboardbutton;
    private javax.swing.JTable itemTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logoutbutton;
    private com.toedter.calendar.JDateChooser requiredDate;
    // End of variables declaration//GEN-END:variables
}
