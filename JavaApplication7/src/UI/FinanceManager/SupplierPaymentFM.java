/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.FinanceManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.FinanceManager;
import models.Item;
import models.Payment;
import models.PurchaseOrder;
import models.Supplier;
import utils.StringFormatter;

/**
 *
 * @author Dev
 */
public class SupplierPaymentFM extends javax.swing.JFrame {

    private FinanceManager financeManager;
    private StringFormatter stringFormatter = new StringFormatter();
    private String selectedSupplierId;

    public SupplierPaymentFM(FinanceManager financeManager) {
        this.financeManager = financeManager;
        initComponents();
        loadSupplierData();
        addTableMouseListener();
    }

    private void loadSupplierData() {
        try {
            String[] columnNames = {"Supplier ID", "Supplier Name", "Contact Person", "Address", "Phone Number", "Products Supplied", "Remaining Payment Amount"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            List<Supplier> suppliers = financeManager.getAllSupplier();

            String filterText = searchTextField.getText().toString().strip().toLowerCase();

            if (filterText != null && !filterText.isEmpty()) {
                suppliers = suppliers.stream().filter(s -> s.getId().toLowerCase().contains(filterText) || s.getName().toLowerCase().contains(filterText)).toList();
            }

            for (Supplier supplier : suppliers) {
                List<PurchaseOrder> purchaseOrders = financeManager.getAllPurchaseOrders().stream()
                        .filter(p -> {

                            try {
                                Item item = financeManager.getItems(p.getItemId());
                                // Check if the item is valid and if the supplier ID matches                            

                                return item != null && item.getSupplierID().equals(supplier.getId()) && p.getStatus().equals("Approved");
                            } catch (IOException e) {
                                // Handle the IOException, for example by logging the error or returning false
                                System.err.println("Error fetching item: " + e.getMessage());
                                return false; // Skip this purchaseOrder if the exception is thrown
                            }
                        }).toList();

                double totalAmount = purchaseOrders.stream().reduce(0.0, (acc, purchaseOrder) -> {
                    try {
                        // Retrieve the item using the item ID
                        Item item = financeManager.getItems(purchaseOrder.getItemId());

                        // Convert order quantity and item price to double (if necessary)
                        double orderQuantity = Double.parseDouble(purchaseOrder.getOrderQuantity());
                        double itemPrice = Double.parseDouble(item.getPrice());

                        // Multiply order quantity with item price and accumulate the result
                        return acc + (orderQuantity * itemPrice);
                    } catch (Exception e) {
                        // Handle any exceptions (like parsing errors or null values)
                        System.err.println("Error processing purchase order: " + e.getMessage());
                        return acc; // Skip this purchase order
                    }
                }, Double::sum);
                // Use sum() to get the total amount directly

                model.addRow(new Object[]{
                    supplier.getId(),
                    supplier.getName(),
                    supplier.getContactPerson(),
                    supplier.getAddress(),
                    supplier.getPhoneNumber(),
                    supplier.getProductsSupplied(),
                    totalAmount
                }
                );
            }

            supplierTable.setModel(model);

        } catch (IOException e) {
            System.err.println("Error loading supplier data: " + e.getMessage());
        }
    }

    private void addTableMouseListener() {
        supplierTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = supplierTable.rowAtPoint(e.getPoint());
                int col = supplierTable.columnAtPoint(e.getPoint());
                String supplierId = supplierTable.getValueAt(row, 0).toString();
                selectedSupplierId = supplierId;

                // Check if the clicked cell is valid
                if (row != -1 && col != -1) {
                    // Get the value of the clicked cell
                    String cellValue = supplierTable.getValueAt(row, col).toString();

                    // Optionally, show the content of the clicked cell
                    JOptionPane.showMessageDialog(null, "Clicked on: " + cellValue, "Cell Content", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private JTable loadPaymentTableData() {
        try {
            // Define column names for the payment history table
            String[] columnNames = {"Payment ID", "Purchase Order ID", "Supplier Id", "Payment Date", "Payment Amount"};

            // Create a DefaultTableModel for the table (non-editable)
            DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Make all cells non-editable
                }
            };

            // Get all payments
            List<Payment> payments = financeManager.getAllPayment();

            // Filter payments based on the selectedSupplierId
            payments = payments.stream().filter(p -> p.getSupplierId().equals(this.selectedSupplierId)).toList();

            // Add rows to the model for each payment
            for (Payment payment : payments) {
                model.addRow(new Object[]{
                    payment.getPaymentId(),
                    payment.getPoId(),
                    payment.getSupplierId(),
                    stringFormatter.formatUnixTimestamp(payment.getPaymentDate()), // Assuming you have a stringFormatter to format dates
                    payment.getPaymentAmount()
                });
            }

            // Create the JTable with the model
            JTable paymentTable = new JTable(model);

            // Return the table to be used in the JOptionPane
            return paymentTable;

        } catch (IOException e) {
            System.err.println("Error loading payment data: " + e.getMessage());
            return null; // Return null in case of error
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SupplierDetailedPaymentFM_Dlg = new javax.swing.JDialog();
        SupplierDetailedPaymentFM_ttl = new javax.swing.JLabel();
        SupplierDetailedPaymentFM_sp = new javax.swing.JScrollPane();
        SupplierDetailedPaymentFM_tbl = new javax.swing.JTable();
        SupplierDetailedPaymentFM_close = new javax.swing.JButton();
        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        SupplierPaymentFM_ttl = new javax.swing.JLabel();
        SupplierPaymentFM_searchttl = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        SupplierPaymentFM_posp = new javax.swing.JScrollPane();
        supplierTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
        paymentHistory = new javax.swing.JButton();

        SupplierDetailedPaymentFM_Dlg.setResizable(false);

        SupplierDetailedPaymentFM_ttl.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        SupplierDetailedPaymentFM_ttl.setText("Payment History");
        SupplierDetailedPaymentFM_ttl.setToolTipText("");

        SupplierDetailedPaymentFM_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Transaction ID", "Payment Date", "Amount Paid", "Payment Method", "Transaction Reference"
            }
        ));
        SupplierDetailedPaymentFM_sp.setViewportView(SupplierDetailedPaymentFM_tbl);

        SupplierDetailedPaymentFM_close.setBackground(new java.awt.Color(255, 75, 62));
        SupplierDetailedPaymentFM_close.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        SupplierDetailedPaymentFM_close.setForeground(new java.awt.Color(255, 229, 72));
        SupplierDetailedPaymentFM_close.setText("Close");

        javax.swing.GroupLayout SupplierDetailedPaymentFM_DlgLayout = new javax.swing.GroupLayout(SupplierDetailedPaymentFM_Dlg.getContentPane());
        SupplierDetailedPaymentFM_Dlg.getContentPane().setLayout(SupplierDetailedPaymentFM_DlgLayout);
        SupplierDetailedPaymentFM_DlgLayout.setHorizontalGroup(
            SupplierDetailedPaymentFM_DlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SupplierDetailedPaymentFM_DlgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SupplierDetailedPaymentFM_DlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SupplierDetailedPaymentFM_sp, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                    .addGroup(SupplierDetailedPaymentFM_DlgLayout.createSequentialGroup()
                        .addComponent(SupplierDetailedPaymentFM_ttl)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SupplierDetailedPaymentFM_DlgLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(SupplierDetailedPaymentFM_close)))
                .addContainerGap())
        );
        SupplierDetailedPaymentFM_DlgLayout.setVerticalGroup(
            SupplierDetailedPaymentFM_DlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SupplierDetailedPaymentFM_DlgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SupplierDetailedPaymentFM_ttl)
                .addGap(18, 18, 18)
                .addComponent(SupplierDetailedPaymentFM_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(SupplierDetailedPaymentFM_close)
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(173, 203, 204));

        SupplierPaymentFM_ttl.setFont(new java.awt.Font("Arial Black", 2, 24)); // NOI18N
        SupplierPaymentFM_ttl.setForeground(new java.awt.Color(65, 76, 65));
        SupplierPaymentFM_ttl.setText("Supplier Payment Status");
        SupplierPaymentFM_ttl.setToolTipText("");

        SupplierPaymentFM_searchttl.setBackground(new java.awt.Color(65, 76, 65));
        SupplierPaymentFM_searchttl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        SupplierPaymentFM_searchttl.setForeground(new java.awt.Color(65, 76, 65));
        SupplierPaymentFM_searchttl.setText("Supplier ID / Supplier Name");

        searchTextField.setBackground(new java.awt.Color(161, 205, 168));
        searchTextField.setForeground(new java.awt.Color(28, 35, 33));
        searchTextField.setToolTipText("");
        searchTextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        searchButton.setBackground(new java.awt.Color(94, 101, 114));
        searchButton.setForeground(new java.awt.Color(238, 241, 239));
        searchButton.setText("Search");
        searchButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        supplierTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        supplierTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        SupplierPaymentFM_posp.setViewportView(supplierTable);

        backButton.setBackground(new java.awt.Color(255, 75, 62));
        backButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 229, 72));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        paymentHistory.setBackground(new java.awt.Color(94, 101, 114));
        paymentHistory.setForeground(new java.awt.Color(238, 241, 239));
        paymentHistory.setText("Payment History");
        paymentHistory.setToolTipText("");
        paymentHistory.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        paymentHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentHistoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SupplierPaymentFM_ttl, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(402, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(SupplierPaymentFM_searchttl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(paymentHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SupplierPaymentFM_posp)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SupplierPaymentFM_ttl)
                .addGap(18, 18, 18)
                .addComponent(SupplierPaymentFM_searchttl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paymentHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SupplierPaymentFM_posp, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        loadSupplierData();
    }//GEN-LAST:event_searchButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        new MainMenuFM(financeManager).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void paymentHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentHistoryActionPerformed
        // Call the method to load payment data and get the JTable
        JTable paymentTable = loadPaymentTableData();
        loadPaymentTableData();
        JScrollPane scrollPane = new JScrollPane(paymentTable);

        // Show the table inside a JOptionPane dialog
        JOptionPane.showMessageDialog(null, scrollPane, "Payment History", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_paymentHistoryActionPerformed

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
            java.util.logging.Logger.getLogger(SupplierPaymentFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SupplierPaymentFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SupplierPaymentFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SupplierPaymentFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog SupplierDetailedPaymentFM_Dlg;
    private javax.swing.JButton SupplierDetailedPaymentFM_close;
    private javax.swing.JScrollPane SupplierDetailedPaymentFM_sp;
    private javax.swing.JTable SupplierDetailedPaymentFM_tbl;
    private javax.swing.JLabel SupplierDetailedPaymentFM_ttl;
    private javax.swing.JScrollPane SupplierPaymentFM_posp;
    private javax.swing.JLabel SupplierPaymentFM_searchttl;
    private javax.swing.JLabel SupplierPaymentFM_ttl;
    private javax.swing.JButton backButton;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton paymentHistory;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JTable supplierTable;
    // End of variables declaration//GEN-END:variables
}
