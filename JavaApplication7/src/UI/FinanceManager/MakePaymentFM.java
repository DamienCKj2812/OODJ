/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.FinanceManager;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.FinanceManager;
import models.Item;
import models.PurchaseOrder;
import utils.LogHandler;
import utils.StringFormatter;

/**
 *
 * @author Dev
 */
public class MakePaymentFM extends javax.swing.JFrame {

    private FinanceManager financeManager;
    private StringFormatter sf;
    private StringFormatter stringFormatter = new StringFormatter();
    private LogHandler logHandler;

    public MakePaymentFM(FinanceManager financeManager) {
        this.financeManager = financeManager;
        this.logHandler = new LogHandler(financeManager);
        initComponents();
        loadTableData();
        setupTableMouseListener();
    }

    private void setupTableMouseListener() {
        purchaseOrderTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = purchaseOrderTable.getSelectedRow();
                int column = purchaseOrderTable.getSelectedColumn();

                // Check if a valid row is selected
                if (row != -1 && column != -1) {
                    // Get the purchaseOrderId from the first column
                    Object purchaseOrderId = purchaseOrderTable.getValueAt(row, 0);
                    if (purchaseOrderId != null) {
                        selectedPurchaseOrderIdLabel.setText(purchaseOrderId.toString());
                    }

                    // Handle double-click event
                    if (e.getClickCount() == 2) { // Double-click detected
                        Object cellValue = purchaseOrderTable.getValueAt(row, column);
                        String columnName = purchaseOrderTable.getColumnName(column);

                        if (cellValue != null) {
                            // Copy the cell value to the clipboard
                            String value = cellValue.toString();
                            Toolkit.getDefaultToolkit().getSystemClipboard()
                                    .setContents(new StringSelection(value), null);

                            // Optionally, show a confirmation dialog
                            JOptionPane.showMessageDialog(null,
                                    value,
                                    columnName,
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        MakePaymentFM_ttl = new javax.swing.JLabel();
        backHomeButton = new javax.swing.JButton();
        MakePaymentFM_searchttl = new javax.swing.JLabel();
        paymentFilterTextField = new javax.swing.JTextField();
        refreshListButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        purchaseOrderTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        selectedPurchaseOrderIdLabel = new javax.swing.JLabel();
        makePaymentButton = new javax.swing.JButton();

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 157, 204));

        MakePaymentFM_ttl.setFont(new java.awt.Font("Arial Black", 2, 24)); // NOI18N
        MakePaymentFM_ttl.setForeground(new java.awt.Color(58, 68, 93));
        MakePaymentFM_ttl.setText("Make Payment");
        MakePaymentFM_ttl.setToolTipText("");

        backHomeButton.setBackground(new java.awt.Color(255, 75, 62));
        backHomeButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        backHomeButton.setForeground(new java.awt.Color(255, 229, 72));
        backHomeButton.setText("Back");
        backHomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backHomeButtonActionPerformed(evt);
            }
        });

        MakePaymentFM_searchttl.setBackground(new java.awt.Color(55, 63, 81));
        MakePaymentFM_searchttl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        MakePaymentFM_searchttl.setForeground(new java.awt.Color(58, 68, 93));
        MakePaymentFM_searchttl.setText("Filter Keyword:");

        paymentFilterTextField.setBackground(new java.awt.Color(94, 87, 104));
        paymentFilterTextField.setForeground(new java.awt.Color(28, 35, 33));
        paymentFilterTextField.setToolTipText("");
        paymentFilterTextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        paymentFilterTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentFilterTextFieldActionPerformed(evt);
            }
        });

        refreshListButton.setBackground(new java.awt.Color(94, 101, 114));
        refreshListButton.setForeground(new java.awt.Color(238, 241, 239));
        refreshListButton.setText("Refresh List");
        refreshListButton.setToolTipText("");
        refreshListButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        refreshListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshListButtonActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

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
        jScrollPane2.setViewportView(purchaseOrderTable);

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Selected Purchase Order ID:");

        selectedPurchaseOrderIdLabel.setForeground(new java.awt.Color(0, 0, 0));
        selectedPurchaseOrderIdLabel.setText("None");

        makePaymentButton.setText("Make Payment");
        makePaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makePaymentButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(makePaymentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(backHomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MakePaymentFM_ttl, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(MakePaymentFM_searchttl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(paymentFilterTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(refreshListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectedPurchaseOrderIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 117, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MakePaymentFM_ttl)
                .addGap(18, 18, 18)
                .addComponent(MakePaymentFM_searchttl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(paymentFilterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(refreshListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(selectedPurchaseOrderIdLabel)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(makePaymentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backHomeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshListButtonActionPerformed
        loadTableData();
        selectedPurchaseOrderIdLabel.setText("None");

    }//GEN-LAST:event_refreshListButtonActionPerformed

    private void paymentFilterTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentFilterTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentFilterTextFieldActionPerformed

    private void backHomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backHomeButtonActionPerformed
        new MainMenuFM(financeManager).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backHomeButtonActionPerformed

    private void makePaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makePaymentButtonActionPerformed
        String selectedPurchaseOrder = selectedPurchaseOrderIdLabel.getText().toString().strip().toLowerCase();
        try {
            // Check if no purchase order is selected
            if (selectedPurchaseOrder == null || selectedPurchaseOrder.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "No Purchase Order Selected",
                        "Make Payment",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Show a confirmation dialog
            int confirmation = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to make the payment for Purchase Order: " + selectedPurchaseOrder + "?",
                    "Confirm Payment",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Check user's response
            if (confirmation == JOptionPane.YES_OPTION) {
                // Retrieve the purchase order and update it
                PurchaseOrder purchaseOrder = financeManager.getPurchaseOrder(selectedPurchaseOrder);
                financeManager.updatePurchaseOrder(
                        purchaseOrder.getPurchaseOrderId(),
                        purchaseOrder.getRequisitionID(),
                        purchaseOrder.getItemId(),
                        purchaseOrder.getOrderQuantity(),
                        purchaseOrder.getOrderDate(),
                        purchaseOrder.getExpectedDeliveryDate(),
                        "Done Payment",
                        purchaseOrder.getPurchaseManagerID()
                );

                Item item = financeManager.getItems(purchaseOrder.getItemId());
                int orderQuantity = Integer.parseInt(purchaseOrder.getOrderQuantity());
                double price = Double.parseDouble(item.getPrice());

                // Perform the multiplication
                double totalAmount = orderQuantity * price;

                // Call addPayment method
                financeManager.addPayment(purchaseOrder.getPurchaseOrderId(),
                        item.getSupplierID(),
                        Long.toString(logHandler.getCurrentUnixTimestamp()),
                        Double.toString(totalAmount)
                );

                // Reload data and reset the selection
                loadTableData();
                selectedPurchaseOrderIdLabel.setText("None");

                // Show success message
                JOptionPane.showMessageDialog(null,
                        "Payment successfully made for Purchase Order: " + selectedPurchaseOrder,
                        "Make Payment",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error: Purchase order is not found",
                    "Make Payment",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_makePaymentButtonActionPerformed

    private void loadTableData() {
        try {
            String[] columnNames = {"Purchase Order ID", "Requisition ID", "Item Id", "Order Quantity", "Order Date", "Expected Delivery Date", "Purchase Manager ID"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Make all cells non-editable
                }
            };

            List<PurchaseOrder> purchaseOrders = financeManager.getAllApprovedPurchaseOrders();

            String filterText = paymentFilterTextField.getText().toString().strip().toLowerCase();

            if (filterText != null && !filterText.isEmpty()) {
                purchaseOrders = purchaseOrders.stream()
                        .filter(purchaseOrder -> purchaseOrder.getPurchaseOrderId().toLowerCase().contains(filterText.toLowerCase()) || purchaseOrder.getRequisitionID().toLowerCase().contains(filterText.toLowerCase()) || purchaseOrder.getItemId().toLowerCase().contains(filterText.toLowerCase()) || purchaseOrder.getOrderQuantity().toLowerCase().contains(filterText.toLowerCase()) || purchaseOrder.getPurchaseManagerID().toLowerCase().contains(filterText.toLowerCase()))
                        .toList();
            }

            for (PurchaseOrder purchaseOrder : purchaseOrders) {
                model.addRow(new Object[]{
                    purchaseOrder.getPurchaseOrderId(),
                    purchaseOrder.getRequisitionID(),
                    purchaseOrder.getItemId(),
                    purchaseOrder.getOrderQuantity(),
                    stringFormatter.formatUnixTimestamp(purchaseOrder.getOrderDate()),
                    stringFormatter.formatUnixTimestamp(purchaseOrder.getExpectedDeliveryDate()),
                    purchaseOrder.getPurchaseManagerID()
                });
            }

            purchaseOrderTable.setModel(model);

        } catch (IOException e) {
            System.err.println("Error loading purchaseOrder data: " + e.getMessage());
        }
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
            java.util.logging.Logger.getLogger(MakePaymentFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MakePaymentFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MakePaymentFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MakePaymentFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MakePaymentFM_searchttl;
    private javax.swing.JLabel MakePaymentFM_ttl;
    private javax.swing.JButton backHomeButton;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton makePaymentButton;
    private javax.swing.JTextField paymentFilterTextField;
    private javax.swing.JTable purchaseOrderTable;
    private javax.swing.JButton refreshListButton;
    private javax.swing.JLabel selectedPurchaseOrderIdLabel;
    // End of variables declaration//GEN-END:variables
}
