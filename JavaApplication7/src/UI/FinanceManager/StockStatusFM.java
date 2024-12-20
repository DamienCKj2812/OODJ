/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.FinanceManager;

import UI.InventoryManager.ManageStockUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
public class StockStatusFM extends javax.swing.JFrame {

    private FinanceManager financeManager;
    private StringFormatter sf;
    private List<PurchaseOrder> purchaseOrders;

    public StockStatusFM(FinanceManager financeManager) {
        this.financeManager = financeManager;
        initComponents();
        loadStockData();
        setupTableMouseListener();
    }

    private void setupTableMouseListener() {
        // Add a MouseListener to detect double-clicks on the table, run only once
        stockStatusTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = stockStatusTable.getSelectedRow();
                int column = stockStatusTable.getSelectedColumn();

                // Check if a valid row is selected
                if (row != -1 && column != -1) {

                    // Handle double-click event
                    if (e.getClickCount() == 1) {
                        Object cellValue = stockStatusTable.getValueAt(row, column);
                        String columnName = stockStatusTable.getColumnName(column);

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

    private void loadStockData() {
        try {
            String[] columnNames = {"Item ID", "Item Name", "Quantity", "Reorder Point", "SupplierId", "Stock Status"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            List<Item> items = financeManager.getInventoryItems();

            String filterText = stockStatusTextField.getText().toString().strip().toLowerCase();
            String selectedStatus = stockStatusComboBox.getSelectedItem().toString().toLowerCase();

            System.out.println(selectedStatus);

            if (filterText != null && !filterText.isEmpty()) {
                items = items.stream()
                        .filter(item -> item.getItemID().toLowerCase().contains(filterText.toLowerCase())
                        || item.getName().toLowerCase().contains(filterText.toLowerCase()) || item.getSupplierID().toLowerCase().contains(filterText.toLowerCase()))
                        .toList();
            }

            if (selectedStatus != null && !selectedStatus.isEmpty()) {
                items = items.stream()
                        .filter(item -> {
                            if (selectedStatus.equals("sufficient")) {
                                return item.getStockStatus().toLowerCase().equals("sufficient");
                            } else if (selectedStatus.equals("warning")) {
                                return item.getStockStatus().toLowerCase().equals("warning");
                            } else if (selectedStatus.equals("danger")) {
                                return item.getStockStatus().toLowerCase().equals("danger");
                            }
                            return true; // Default case: no filtering
                        })
                        .toList();
            }

            for (Item item : items) {
                model.addRow(new Object[]{
                    item.getItemID(),
                    item.getName(),
                    item.getQuantity(),
                    item.getReorderPoint(),
                    item.getSupplierID(),
                    item.getStockStatus(),});
            }

            stockStatusTable.setModel(model);

            // Set the custom renderer for the stock status column (index 5)
            stockStatusTable.getColumnModel().getColumn(5).setCellRenderer(new StockStatusFM.StockStatusRenderer());

        } catch (IOException e) {
            System.err.println("Error loading item data: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jPanel1 = new javax.swing.JPanel();
        StockStatusFM_ttl = new javax.swing.JLabel();
        stockStatusTextField = new javax.swing.JTextField();
        StockStatusFM_searchttl = new javax.swing.JLabel();
        StockStatusFM_posp = new javax.swing.JScrollPane();
        stockStatusTable = new javax.swing.JTable();
        StockStatusFM_filterttl = new javax.swing.JLabel();
        stockStatusComboBox = new javax.swing.JComboBox<>();
        refreshListButton = new javax.swing.JButton();
        backPageButton = new javax.swing.JButton();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(163, 176, 204));

        StockStatusFM_ttl.setFont(new java.awt.Font("Arial Black", 2, 24)); // NOI18N
        StockStatusFM_ttl.setForeground(new java.awt.Color(3, 25, 38));
        StockStatusFM_ttl.setText("Stock Status");

        stockStatusTextField.setBackground(new java.awt.Color(61, 165, 217));
        stockStatusTextField.setForeground(new java.awt.Color(28, 35, 33));
        stockStatusTextField.setToolTipText("");
        stockStatusTextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        stockStatusTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockStatusTextFieldActionPerformed(evt);
            }
        });

        StockStatusFM_searchttl.setBackground(new java.awt.Color(55, 63, 81));
        StockStatusFM_searchttl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        StockStatusFM_searchttl.setForeground(new java.awt.Color(3, 25, 38));
        StockStatusFM_searchttl.setText("Enter your item name here");

        stockStatusTable.setModel(new javax.swing.table.DefaultTableModel(
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
        StockStatusFM_posp.setViewportView(stockStatusTable);

        StockStatusFM_filterttl.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        StockStatusFM_filterttl.setForeground(new java.awt.Color(55, 63, 81));
        StockStatusFM_filterttl.setText("Filter by Stock Status");

        stockStatusComboBox.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        stockStatusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Sufficient", "Warning", "Danger" }));
        stockStatusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockStatusComboBoxActionPerformed(evt);
            }
        });

        refreshListButton.setBackground(new java.awt.Color(35, 100, 170));
        refreshListButton.setForeground(new java.awt.Color(238, 241, 239));
        refreshListButton.setText("Refresh List");
        refreshListButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        refreshListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshListButtonActionPerformed(evt);
            }
        });

        backPageButton.setBackground(new java.awt.Color(255, 75, 62));
        backPageButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        backPageButton.setForeground(new java.awt.Color(255, 229, 72));
        backPageButton.setText("Back");
        backPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backPageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StockStatusFM_ttl)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stockStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StockStatusFM_searchttl))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(StockStatusFM_filterttl)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(stockStatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(refreshListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(StockStatusFM_posp, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(backPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(backPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(StockStatusFM_ttl)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(StockStatusFM_searchttl)
                            .addComponent(StockStatusFM_filterttl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stockStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stockStatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(refreshListButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StockStatusFM_posp, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stockStatusTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockStatusTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stockStatusTextFieldActionPerformed

    private void stockStatusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockStatusComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stockStatusComboBoxActionPerformed

    private void refreshListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshListButtonActionPerformed
        loadStockData();
    }//GEN-LAST:event_refreshListButtonActionPerformed

    private void backPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backPageButtonActionPerformed
        new MainMenuFM(financeManager).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backPageButtonActionPerformed

    class StockStatusRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (value != null) {
                String status = value.toString();
                switch (status) {
                    case "Sufficient":
                        cell.setForeground(Color.GREEN);
                        break;
                    case "Warning":
                        cell.setForeground(Color.ORANGE);
                        break;
                    case "Danger":
                        cell.setForeground(Color.RED);
                        break;
                    default:
                        cell.setForeground(Color.BLACK);
                }
            }

            return cell;
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
            java.util.logging.Logger.getLogger(StockStatusFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StockStatusFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StockStatusFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StockStatusFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel StockStatusFM_filterttl;
    private javax.swing.JScrollPane StockStatusFM_posp;
    private javax.swing.JLabel StockStatusFM_searchttl;
    private javax.swing.JLabel StockStatusFM_ttl;
    private javax.swing.JButton backPageButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JButton refreshListButton;
    private javax.swing.JComboBox<String> stockStatusComboBox;
    private javax.swing.JTable stockStatusTable;
    private javax.swing.JTextField stockStatusTextField;
    // End of variables declaration//GEN-END:variables
}
