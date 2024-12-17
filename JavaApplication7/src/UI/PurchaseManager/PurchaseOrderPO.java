/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.PurchaseManager;

import UI.Admin.AdminHomeUI;
import UI.Authentication.LoginUI;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import models.Admin;
import models.PurchaseManager;
import models.PurchaseOrder;
import models.Requisition;
import models.Item;
import state.UserSession;
import javax.swing.SpinnerNumberModel;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;
import utils.LogHandler;
import utils.StringFormatter;

/**
 *
 * @author zhuow
 */
public class PurchaseOrderPO extends javax.swing.JFrame {

    /**
     * Creates new form PurchaseManagerDashBoard
     */
    private PurchaseManager purchaseManager;
    private List<PurchaseOrder> purchaseOrders;
    private List<Requisition> requisitions;
    private LogHandler logHandler;
    private String selectedPurchaseOrderId; // Attribute to keep the first column value

    UserSession userState = UserSession.getInstance();
    Admin admin = userState.getLoggedInAdmin();

    public PurchaseOrderPO(PurchaseManager purchaseManager) {
        this.purchaseManager = purchaseManager;
        this.logHandler = new LogHandler(purchaseManager);
        initComponents();
        try {
            loadData();
            loadDataToUIComponents();
            loadTableData();
        } catch (IOException e) {
            System.out.println(e);
        }

        if (admin != null) {
            adminpageButton.setVisible(true);
        } else {
            adminpageButton.setVisible(false);
        }

        quantitySpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        quantitySpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                updateTotalCost();
            }
        });

        purchaseOrderTable.getSelectionModel().addListSelectionListener(event -> {
            updateSelectedPurchaseOrderId();
        });
    }

    private void updateSelectedPurchaseOrderId() {
        int selectedRow = purchaseOrderTable.getSelectedRow(); // Get selected row index

        if (selectedRow != -1) { // Check if a row is selected
            Object firstColumnValue = purchaseOrderTable.getValueAt(selectedRow, 0); // Get the value in the first column
            selectedPurchaseOrderId = firstColumnValue.toString(); // Update the attribute
            System.out.println("Selected Purchase Order ID: " + selectedPurchaseOrderId);
        } else {
            selectedPurchaseOrderId = null; // Reset if no row is selected
            System.out.println("No row selected.");
        }
    }

    private void loadData() throws IOException {
        purchaseOrders = purchaseManager.getAllPurchaseOrders();
        requisitions = purchaseManager.getAllRequisitions();
    }

    private void loadDataToUIComponents() throws IOException {
        loadData();
        RequisitionidComboBox.removeAllItems();
        for (Requisition po : requisitions) {
            RequisitionidComboBox.addItem(po.getRequisitionId());
        }
    }

    private void loadTableData() {
        try {
            loadData();
            // Column Names and Model for Purchase Order Table
            String[] purchaseOrderColumnNames = {"Purchase Order ID", "Requisition Id", "Item Id", "Order Quantity", "Order Date", "Expected Delivery Date", "Status", "Purchase Manager"};
            DefaultTableModel purchaseOrderModel = new DefaultTableModel(purchaseOrderColumnNames, 0);

            for (PurchaseOrder po : purchaseOrders) {
                purchaseOrderModel.addRow(new Object[]{
                    po.getPurchaseOrderId(),
                    po.getRequisitionID(),
                    po.getItemId(),
                    po.getOrderQuantity(),
                    StringFormatter.formatUnixTimestamp(po.getOrderDate()),
                    StringFormatter.formatUnixTimestamp(po.getExpectedDeliveryDate()),
                    po.getStatus(),
                    po.getPurchaseManagerID()
                });
            }

// Column Names and Model for Requisition Table
            String[] requisitionColumnNames = {"Requisition ID", "Item Id", "Quantity", "Required Date", "Sales Manager Id", "Requisition Date"};
            DefaultTableModel requisitionModel = new DefaultTableModel(requisitionColumnNames, 0);

            for (Requisition r : requisitions) {
                requisitionModel.addRow(new Object[]{
                    r.getRequisitionId(),
                    r.getItemId(),
                    r.getQuantity(),
                    StringFormatter.formatUnixTimestamp(r.getRequiredDate()),
                    r.getSalesManagerId(),
                    StringFormatter.formatUnixTimestamp(r.getRequisitionDate()),});
            }

// Set Models to Respective Tables
            purchaseOrderTable.setModel(purchaseOrderModel);
            requisitionTable.setModel(requisitionModel);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    private void updateTotalCost() {
        try {
            // Get the selected Requisition ID
            String requisitionId = (String) RequisitionidComboBox.getSelectedItem();

            // Get the quantity from the spinner
            int quantity = (int) quantitySpinner.getValue();

            // Retrieve the Requisition object using its ID
            Requisition requisition = purchaseManager.getRequisition(requisitionId);

            // Retrieve the associated Item object and its price
            Item item = purchaseManager.getItem(requisition.getItemId());
            double price = Double.parseDouble(item.getPrice());

            // Calculate total cost
            double totalCost = price * quantity;

            // Set the calculated total cost to the text field
            TotalCostTextfield.setText(String.format("%.2f", totalCost)); // Format to 2 decimal places
        } catch (Exception e) {
            System.out.println("Error updating total cost: " + e.getMessage());
        }
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
        POlabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        welcomelabel = new javax.swing.JLabel();
        adminpageButton = new javax.swing.JButton();
        RequisitionIDlabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        quantitylabel = new javax.swing.JLabel();
        TotalCostlabel = new javax.swing.JLabel();
        RequisitionidComboBox = new javax.swing.JComboBox<>();
        quantitySpinner = new javax.swing.JSpinner();
        TotalCostTextfield = new javax.swing.JTextField();
        addpo = new javax.swing.JButton();
        editpoButton = new javax.swing.JButton();
        deletepoButton = new javax.swing.JButton();
        backtodashboardButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        statuslabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        purchaseOrderTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        requisitionTableLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        requisitionTable = new javax.swing.JTable();
        expectedDeliveryDate = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));

        POlabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        POlabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/purchase order.png"))); // NOI18N
        POlabel.setText("Purchase Order");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(POlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(POlabel)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 255, 255));

        welcomelabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        welcomelabel.setText("WELCOME!");

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
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(adminpageButton)
                    .addComponent(welcomelabel))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(welcomelabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(adminpageButton)
                .addGap(42, 42, 42))
        );

        RequisitionIDlabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        RequisitionIDlabel.setText("Requisition ID");

        quantitylabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        quantitylabel.setText("Quantity");

        TotalCostlabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TotalCostlabel.setText("Total Cost");

        RequisitionidComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RequisitionidComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RequisitionidComboBoxMouseClicked(evt);
            }
        });
        RequisitionidComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RequisitionidComboBoxActionPerformed(evt);
            }
        });

        quantitySpinner.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        TotalCostTextfield.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TotalCostTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalCostTextfieldActionPerformed(evt);
            }
        });

        addpo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addpo.setText("Add Purchase Order");
        addpo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addpoActionPerformed(evt);
            }
        });

        editpoButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        editpoButton.setText("Edit");
        editpoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editpoButtonActionPerformed(evt);
            }
        });

        deletepoButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deletepoButton.setText("Delete");
        deletepoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletepoButtonActionPerformed(evt);
            }
        });

        backtodashboardButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        backtodashboardButton.setText("Back to Dashboard");
        backtodashboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backtodashboardButtonActionPerformed(evt);
            }
        });

        logoutButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        logoutButton.setText("Log Out");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        statuslabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Expected Delivery Date");

        purchaseOrderTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(purchaseOrderTable);

        jLabel1.setText("Purchase Order Table");

        requisitionTableLabel.setText("Requisition Table");

        requisitionTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(requisitionTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(expectedDeliveryDate, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(645, 645, 645)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(requisitionTableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(RequisitionIDlabel)
                                    .addComponent(RequisitionidComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(quantitylabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TotalCostTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TotalCostlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(addpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deletepoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(backtodashboardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(editpoButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statuslabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(statuslabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(requisitionTableLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(addpo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(editpoButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deletepoButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(backtodashboardButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(logoutButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(RequisitionIDlabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(RequisitionidComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(TotalCostlabel)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(TotalCostTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(quantitylabel)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(expectedDeliveryDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void TotalCostTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalCostTextfieldActionPerformed

    }//GEN-LAST:event_TotalCostTextfieldActionPerformed

    private void adminpageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminpageButtonActionPerformed
        new AdminHomeUI(admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_adminpageButtonActionPerformed

    private void addpoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addpoActionPerformed
        try {
            String requisitionId = (String) RequisitionidComboBox.getSelectedItem();
            int quantity = (int) quantitySpinner.getValue();
            Requisition requisition = purchaseManager.getRequisition(requisitionId);
            Item item = purchaseManager.getItem(requisition.getItemId());

            Date expectedDelivery = expectedDeliveryDate.getDate();
            long unixTimestampExpectedDeliveryDate;

            if (expectedDelivery != null) {
                unixTimestampExpectedDeliveryDate = expectedDelivery.getTime() / 1000; // Convert milliseconds to seconds
            } else {
                JOptionPane.showMessageDialog(this, "Please select a required date.");
                return; // Exit early if no date is selected
            }

            Date currentDate = new Date();
            long unixTimestampRequisitionDate = currentDate.getTime() / 1000; // Convert current date to Unix timestamp

            // Call the method to add the purchase order
            PurchaseOrder newPurchaseOrder = purchaseManager.addPurchaseOrder(
                    requisition.getRequisitionId(),
                    item.getItemID(),
                    String.valueOf(quantity),
                    String.valueOf(unixTimestampRequisitionDate),
                    String.valueOf(unixTimestampExpectedDeliveryDate),
                    "Pending",
                    purchaseManager.getUserID()
            );

            // Show a success dialog
            JOptionPane.showMessageDialog(this, "Purchase order added successfully!");

            try {
                logHandler.addLogActionToFile(String.format(
                        "Added the Purchase order ( id: %s, item id: %s, quantity: %s, expected delivery time: %s)",
                        newPurchaseOrder.getPurchaseOrderId(),
                        newPurchaseOrder.getItemId(),
                        newPurchaseOrder.getOrderQuantity(),
                        new SimpleDateFormat("yyyy MM dd").format(expectedDelivery)
                ));
            } catch (Exception logError) {
                JOptionPane.showMessageDialog(this,
                        "Logging error: " + logError.getMessage(),
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                logError.printStackTrace(); // Optional: Print the stack trace for debugging
            }
            loadDataToUIComponents();
            purchaseManager.removeRequisition(newPurchaseOrder.getRequisitionID());
            loadTableData();
            RequisitionidComboBox.setSelectedIndex(-1);
            quantitySpinner.setValue(1);
            TotalCostTextfield.setText("0.00");
            expectedDeliveryDate.setDate(null);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error adding purchase order: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }//GEN-LAST:event_addpoActionPerformed

    private void editpoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editpoButtonActionPerformed
        int selectedRow = purchaseOrderTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a purchase order to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Get current values from the selected row
        String purchaseOrderId = purchaseOrderTable.getValueAt(selectedRow, 0).toString();
        String itemId = purchaseOrderTable.getValueAt(selectedRow, 2).toString(); // Assuming Item ID is in column 2
        int orderQuantity = Integer.parseInt(purchaseOrderTable.getValueAt(selectedRow, 3).toString());
        Date expectedDeliveryDate = new Date();
        try {
            expectedDeliveryDate = new SimpleDateFormat("yyyy-MM-dd").parse(purchaseOrderTable.getValueAt(selectedRow, 5).toString());
        } catch (ParseException e) {
            // Handle invalid date format gracefully
        }

        // Create input fields
        JTextField newItemIDField = new JTextField(itemId);
        JSpinner newQuantitySpinner = new JSpinner(new SpinnerNumberModel(orderQuantity, 1, Integer.MAX_VALUE, 1));
        com.toedter.calendar.JDateChooser dateChooser = new com.toedter.calendar.JDateChooser();
        dateChooser.setDate(expectedDeliveryDate);

        // Create the panel for the dialog
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.add(new JLabel("New Item ID:"));
        panel.add(newItemIDField);
        panel.add(new JLabel("New Quantity:"));
        panel.add(newQuantitySpinner);
        panel.add(new JLabel("Expected Delivery Date:"));
        panel.add(dateChooser);

        // Show the dialog
        int result = JOptionPane.showConfirmDialog(this, panel, "Edit Purchase Order", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Get updated values
            String updatedItemID = newItemIDField.getText();
            int updatedQuantity = (int) newQuantitySpinner.getValue();
            Date updatedDate = dateChooser.getDate();

            if (updatedDate == null) {
                JOptionPane.showMessageDialog(this, "Please select a valid date.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                // Update the purchase order in the backend
                purchaseManager.updatePurchaseOrder(
                        purchaseOrderId,
                        purchaseOrderTable.getValueAt(selectedRow, 1).toString(),
                        updatedItemID,
                        String.valueOf(updatedQuantity),
                        String.valueOf(System.currentTimeMillis() / 1000),
                        String.valueOf(updatedDate.getTime() / 1000),
                        "Pending",
                        purchaseManager.getUserID()
                );
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error updating purchase order: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Update the table
            DefaultTableModel model = (DefaultTableModel) purchaseOrderTable.getModel();
            model.setValueAt(updatedItemID, selectedRow, 2); // Update Item ID
            model.setValueAt(updatedQuantity, selectedRow, 3); // Update Quantity
            model.setValueAt(new SimpleDateFormat("yyyy-MM-dd").format(updatedDate), selectedRow, 5); // Update Expected Delivery Date

            // Show success message
            JOptionPane.showMessageDialog(this, "Purchase order updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_editpoButtonActionPerformed

    private void deletepoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletepoButtonActionPerformed
        int selectedRow = purchaseOrderTable.getSelectedRow();

        if (selectedRow == -1) {
            // No row is selected
            JOptionPane.showMessageDialog(this, "Please select a purchase order to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Get the Purchase Order ID from the first column of the selected row
        String purchaseOrderId = purchaseOrderTable.getValueAt(selectedRow, 0).toString();

        // Show confirmation dialog
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to remove the purchase order with ID: " + purchaseOrderId + "?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            // Perform deletion logic here
            try {
                // Call a method from your manager class to delete the purchase order
                purchaseManager.removePurchaseOrder(purchaseOrderId);

                // Remove the row from the table model
                DefaultTableModel model = (DefaultTableModel) purchaseOrderTable.getModel();
                model.removeRow(selectedRow);

                // Show success message
                JOptionPane.showMessageDialog(this, "Purchase order deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                // Handle any errors during deletion
                JOptionPane.showMessageDialog(this, "Error deleting purchase order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_deletepoButtonActionPerformed

    private void backtodashboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backtodashboardButtonActionPerformed
        new PurchaseManagerDashBoard(purchaseManager).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backtodashboardButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        new LoginUI().setVisible(true);
        this.dispose();
        userState.setLoggedInAdmin(null);
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void RequisitionidComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RequisitionidComboBoxActionPerformed

        updateTotalCost();
    }//GEN-LAST:event_RequisitionidComboBoxActionPerformed

    private void RequisitionidComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RequisitionidComboBoxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_RequisitionidComboBoxMouseClicked

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
            java.util.logging.Logger.getLogger(PurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel POlabel;
    private javax.swing.JLabel RequisitionIDlabel;
    private javax.swing.JComboBox<String> RequisitionidComboBox;
    private javax.swing.JTextField TotalCostTextfield;
    private javax.swing.JLabel TotalCostlabel;
    private javax.swing.JButton addpo;
    private javax.swing.JButton adminpageButton;
    private javax.swing.JButton backtodashboardButton;
    private javax.swing.JButton deletepoButton;
    private javax.swing.JButton editpoButton;
    private com.toedter.calendar.JDateChooser expectedDeliveryDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTable purchaseOrderTable;
    private javax.swing.JSpinner quantitySpinner;
    private javax.swing.JLabel quantitylabel;
    private javax.swing.JTable requisitionTable;
    private javax.swing.JLabel requisitionTableLabel;
    private javax.swing.JLabel statuslabel1;
    private javax.swing.JLabel welcomelabel;
    // End of variables declaration//GEN-END:variables
}
