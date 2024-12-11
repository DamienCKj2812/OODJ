/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.FinanceManager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import models.FinanceManager;
import models.Item;
import models.PurchaseOrder;
import utils.StringFormatter;

/**
 *
 * @author Dev
 */
public class ViewPurchaseOrderFM extends javax.swing.JFrame {

    private FinanceManager financeManager;
    private StringFormatter sf;
    private List<PurchaseOrder> purchaseOrders;

    public ViewPurchaseOrderFM(FinanceManager financeManager) {
        this.financeManager = financeManager;
        initComponents();
        loadPurchaseOrderData();
        setupTableMouseListener();

    }

    private void setupTableMouseListener() {
        // Add a MouseListener to detect double-clicks on the table, run only once
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
                        selectedPurchaseOrderLabel.setText(purchaseOrderId.toString());
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

    private void loadPurchaseOrderData() {
        try {
            String[] columnNames = {
                "Purchase Order ID", "Requisition ID", "Item ID", "Order Quantity",
                "Order Date", "Expected Delivery Date", "Status", "Purchase Manager ID"
            };

            DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            List<PurchaseOrder> purchaseOrders = financeManager.getAllPurchaseOrders();
            String selectedStatus = (String) filterStatusComboBox.getSelectedItem();
            String selectedFilterKeyword = filterTextField.getText().strip().toLowerCase();

            //Filter based on the JComboBox
            purchaseOrders = purchaseOrders.stream()
                    .filter(purchaseOrder
                            -> selectedStatus.equalsIgnoreCase("All")
                    || purchaseOrder.getStatus().equalsIgnoreCase(selectedStatus)
                    )
                    .collect(Collectors.toList());

            purchaseOrders = purchaseOrders.stream()
                    .filter(purchaseOrder
                            -> purchaseOrder.getPurchaseOrderId().toLowerCase().contains(selectedFilterKeyword)
                    || purchaseOrder.getPurchaseManagerID().toLowerCase().contains(selectedFilterKeyword)
                    || purchaseOrder.getItemId().toLowerCase().contains(selectedFilterKeyword))
                    .collect(Collectors.toList());

            // Populate the table with data
            for (PurchaseOrder purchaseOrder : purchaseOrders) {
                model.addRow(new Object[]{
                    purchaseOrder.getPurchaseOrderId(),
                    purchaseOrder.getRequisitionID(),
                    purchaseOrder.getItemId(),
                    purchaseOrder.getOrderQuantity(),
                    sf.formatUnixTimestamp(purchaseOrder.getOrderDate()),
                    sf.formatUnixTimestamp(purchaseOrder.getExpectedDeliveryDate()),
                    purchaseOrder.getStatus(),
                    purchaseOrder.getPurchaseManagerID(),});
            }

            purchaseOrderTable.setModel(model);

            // Custom renderer for the "Status" column
            purchaseOrderTable.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    if (value != null) {
                        String status = value.toString();
                        if (status.equalsIgnoreCase("Approved")) {
                            c.setBackground(Color.GREEN);
                        } else if (status.equalsIgnoreCase("Rejected")) {
                            c.setBackground(Color.RED);
                        } else if (status.equalsIgnoreCase("Pending")) {
                            c.setBackground(Color.ORANGE);
                        } else {
                            c.setBackground(Color.WHITE);
                        }
                    } else {
                        c.setBackground(Color.WHITE);
                    }
                    return c;
                }
            });

        } catch (IOException e) {
            System.err.println("Error loading purchaseOrder data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        PurchaseOrderFM_ttl = new javax.swing.JLabel();
        filterTextField = new javax.swing.JTextField();
        PurchaseOrderFM_searchttl = new javax.swing.JLabel();
        filterStatusComboBox = new javax.swing.JComboBox<>();
        PurchaseOrderFM_filterttl = new javax.swing.JLabel();
        refreshListButton = new javax.swing.JButton();
        approveButton = new javax.swing.JButton();
        rejectButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        PurchaseOrderFM_posp = new javax.swing.JScrollPane();
        purchaseOrderTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        selectedPurchaseOrderLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel.setBackground(new java.awt.Color(216, 219, 226));

        PurchaseOrderFM_ttl.setFont(new java.awt.Font("Arial Black", 2, 24)); // NOI18N
        PurchaseOrderFM_ttl.setForeground(new java.awt.Color(55, 63, 81));
        PurchaseOrderFM_ttl.setText("Purchase Order");
        PurchaseOrderFM_ttl.setToolTipText("");

        filterTextField.setBackground(new java.awt.Color(125, 152, 161));
        filterTextField.setForeground(new java.awt.Color(28, 35, 33));
        filterTextField.setToolTipText("");
        filterTextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        filterTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterTextFieldActionPerformed(evt);
            }
        });

        PurchaseOrderFM_searchttl.setBackground(new java.awt.Color(55, 63, 81));
        PurchaseOrderFM_searchttl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        PurchaseOrderFM_searchttl.setForeground(new java.awt.Color(55, 63, 81));
        PurchaseOrderFM_searchttl.setText("Enter your PO ID / Item ID / Purchase Manager here");

        filterStatusComboBox.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        filterStatusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Pending", "Approved", "Rejected" }));
        filterStatusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterStatusComboBoxActionPerformed(evt);
            }
        });

        PurchaseOrderFM_filterttl.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        PurchaseOrderFM_filterttl.setForeground(new java.awt.Color(55, 63, 81));
        PurchaseOrderFM_filterttl.setText("Filter by Approval Status");

        refreshListButton.setBackground(new java.awt.Color(94, 101, 114));
        refreshListButton.setForeground(new java.awt.Color(238, 241, 239));
        refreshListButton.setText("Refresh List");
        refreshListButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        refreshListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshListButtonActionPerformed(evt);
            }
        });

        approveButton.setBackground(new java.awt.Color(44, 218, 157));
        approveButton.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        approveButton.setForeground(new java.awt.Color(62, 137, 137));
        approveButton.setText("APPROVE");
        approveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveButtonActionPerformed(evt);
            }
        });

        rejectButton.setBackground(new java.awt.Color(177, 15, 46));
        rejectButton.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        rejectButton.setForeground(new java.awt.Color(87, 0, 0));
        rejectButton.setText("REJECT");
        rejectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectButtonActionPerformed(evt);
            }
        });

        backButton.setBackground(new java.awt.Color(255, 75, 62));
        backButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 229, 72));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        PurchaseOrderFM_posp.setViewportView(purchaseOrderTable);

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Selected Purchase Order :");

        selectedPurchaseOrderLabel.setForeground(new java.awt.Color(0, 0, 0));
        selectedPurchaseOrderLabel.setText("None");

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PurchaseOrderFM_posp, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(approveButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rejectButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                .addComponent(filterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PurchaseOrderFM_filterttl)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(filterStatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(refreshListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(181, 181, 181))
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectedPurchaseOrderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(PurchaseOrderFM_ttl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PurchaseOrderFM_searchttl, javax.swing.GroupLayout.Alignment.LEADING))
                    .addContainerGap(464, Short.MAX_VALUE)))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(PurchaseOrderFM_filterttl, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filterStatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshListButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectedPurchaseOrderLabel)
                    .addComponent(jLabel1))
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(approveButton)
                        .addGap(18, 18, 18)
                        .addComponent(rejectButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PurchaseOrderFM_posp, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PurchaseOrderFM_ttl)
                    .addGap(18, 18, 18)
                    .addComponent(PurchaseOrderFM_searchttl)
                    .addContainerGap(511, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filterTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterTextFieldActionPerformed

    private void filterStatusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterStatusComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterStatusComboBoxActionPerformed

    private void refreshListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshListButtonActionPerformed
        loadPurchaseOrderData();
        selectedPurchaseOrderLabel.setText("");
    }//GEN-LAST:event_refreshListButtonActionPerformed

    private void rejectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectButtonActionPerformed
        String selectedPurchaseOrderId = selectedPurchaseOrderLabel.getText();
        if (selectedPurchaseOrderId.toLowerCase().equals("none")) {
            // Show an error dialog if no purchase order is selected
            JOptionPane.showMessageDialog(this, "No purchase order selected!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        try {
            // Assuming selectedPurchaseOrderId is valid
            PurchaseOrder purchaseOrder = financeManager.getPurchaseOrder(selectedPurchaseOrderId);

            // Update the purchase order status to "Approved"
            financeManager.updatePurchaseOrder(
                    selectedPurchaseOrderId,
                    purchaseOrder.getRequisitionID(),
                    purchaseOrder.getItemId(),
                    purchaseOrder.getOrderQuantity(),
                    purchaseOrder.getOrderDate(),
                    purchaseOrder.getExpectedDeliveryDate(),
                    "Rejected",
                    purchaseOrder.getPurchaseManagerID()
            );

            // Reload the data to reflect the change in the table
            loadPurchaseOrderData();

            // Show success dialog
            JOptionPane.showMessageDialog(this, "Purchase Order approved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            // Print the error message to the console for debugging
            System.err.println("Error updating purchase order: " + e.getMessage());

            // Optionally, show an error dialog to the user
            JOptionPane.showMessageDialog(this, "An error occurred while approving the purchase order.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_rejectButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        new MainMenuFM(financeManager).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void approveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveButtonActionPerformed
        String selectedPurchaseOrderId = selectedPurchaseOrderLabel.getText();
        if (selectedPurchaseOrderId.toLowerCase().equals("none")) {
            // Show an error dialog if no purchase order is selected
            JOptionPane.showMessageDialog(this, "No purchase order selected!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        try {
            // Assuming selectedPurchaseOrderId is valid
            PurchaseOrder purchaseOrder = financeManager.getPurchaseOrder(selectedPurchaseOrderId);

            // Update the purchase order status to "Approved"
            financeManager.updatePurchaseOrder(
                    selectedPurchaseOrderId,
                    purchaseOrder.getRequisitionID(),
                    purchaseOrder.getItemId(),
                    purchaseOrder.getOrderQuantity(),
                    purchaseOrder.getOrderDate(),
                    purchaseOrder.getExpectedDeliveryDate(),
                    "Approved",
                    purchaseOrder.getPurchaseManagerID()
            );

            // Reload the data to reflect the change in the table
            loadPurchaseOrderData();

            // Show success dialog
            JOptionPane.showMessageDialog(this, "Purchase Order approved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            // Print the error message to the console for debugging
            System.err.println("Error updating purchase order: " + e.getMessage());

            // Optionally, show an error dialog to the user
            JOptionPane.showMessageDialog(this, "An error occurred while approving the purchase order.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_approveButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ViewPurchaseOrderFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPurchaseOrderFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPurchaseOrderFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPurchaseOrderFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PurchaseOrderFM_filterttl;
    private javax.swing.JScrollPane PurchaseOrderFM_posp;
    private javax.swing.JLabel PurchaseOrderFM_searchttl;
    private javax.swing.JLabel PurchaseOrderFM_ttl;
    private javax.swing.JButton approveButton;
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> filterStatusComboBox;
    private javax.swing.JTextField filterTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel;
    private javax.swing.JTable purchaseOrderTable;
    private javax.swing.JButton refreshListButton;
    private javax.swing.JButton rejectButton;
    private javax.swing.JLabel selectedPurchaseOrderLabel;
    // End of variables declaration//GEN-END:variables
}
