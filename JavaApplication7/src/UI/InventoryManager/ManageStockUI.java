/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.InventoryManager;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import models.InventoryManager;
import models.Item;
import utils.InputValidator;
import utils.LogHandler;

/**
 *
 * @author Aorus
 */
public class ManageStockUI extends javax.swing.JFrame {

    private InventoryManager inventoryManager;
    private LogHandler logHandler;

    public ManageStockUI(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
        this.logHandler = new LogHandler(inventoryManager);
        initComponents();
        loadStockData();

        stockTable.setRowHeight(30);

        stockTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Detect single click
                    int row = stockTable.rowAtPoint(e.getPoint());
                    int col = stockTable.columnAtPoint(e.getPoint());
                    if (row >= 0 && col >= 0) {
                        if (col == 0) {
                            JOptionPane.showMessageDialog(stockTable,
                                    "You cannot change the Item ID",
                                    "Warning",
                                    JOptionPane.WARNING_MESSAGE);
                        } else if (col == 1) {
                            JOptionPane.showMessageDialog(stockTable,
                                    "Change the item name in 'Item Entry",
                                    "Warning",
                                    JOptionPane.WARNING_MESSAGE);
                        } else if (col == 4) {
                            JOptionPane.showMessageDialog(stockTable,
                                    "You cannot change the Supplier ID",
                                    "Warning",
                                    JOptionPane.WARNING_MESSAGE);
                        } else if (col == 5) {
                            JOptionPane.showMessageDialog(stockTable,
                                    "You cannot change the Stock status",
                                    "Warning",
                                    JOptionPane.WARNING_MESSAGE);
                        } else {
                            // Call the method to show the dialog for editing other columns
                            showStockTableCellContentDialog(row, col);
                        }
                    }
                }
            }
        });

        filterItemTextField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filterStockTable();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filterStockTable();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filterStockTable();
            }
        });
    }

    private void showStockTableCellContentDialog(int row, int col) {
        Object cellContent = stockTable.getValueAt(row, col);
        String columnName = stockTable.getColumnName(col);
        String itemId = (String) stockTable.getValueAt(row, 0);
        Item selectedItem = null;

        try {
            selectedItem = inventoryManager.getItem(itemId);
            if (selectedItem == null) {
                JOptionPane.showMessageDialog(this,
                        "Item not found: " + itemId,
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            JTextField textField = new JTextField(cellContent != null ? cellContent.toString() : "");

            Object[] message = {
                "Before: " + (cellContent != null ? cellContent.toString() : "No content"),
                textField,};

            int result = JOptionPane.showConfirmDialog(this,
                    message,
                    "Edit " + columnName,
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String newContent = textField.getText();
                InputValidator.validateNotEmpty(newContent, columnName);
                InputValidator.validateIsInteger(newContent, columnName);

                boolean updateSuccessful = false;
                switch (columnName) {
                    case "Quantity":
                        updateSuccessful = inventoryManager.updateItem(itemId, selectedItem.getName(), selectedItem.getDescription(), selectedItem.getPrice(), newContent, selectedItem.getReorderPoint(), selectedItem.getSupplierID());
                        break;
                    case "Reorder Point":
                        updateSuccessful = inventoryManager.updateItem(itemId, selectedItem.getName(), selectedItem.getDescription(), selectedItem.getPrice(), selectedItem.getQuantity(), newContent, selectedItem.getSupplierID());
                        break;
                    default:
                        // If column is not recognized, handle accordingly
                        JOptionPane.showMessageDialog(this,
                                "Column not recognized: " + columnName,
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                }

                if (updateSuccessful) {
                    stockTable.setValueAt(newContent, row, col);
                    logHandler.addLogActionToFile(String.format(
                            "Updated the Item's %s (%s): from %s to %s",
                            columnName,
                            selectedItem.getItemID(),
                            cellContent,
                            newContent
                    ));
                    JOptionPane.showMessageDialog(this,
                            columnName + ": " + newContent,
                            "Update Successful!",
                            JOptionPane.INFORMATION_MESSAGE);
                    loadStockData();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Failed to update item: " + itemId,
                            "Update Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (IOException e) {
            // Handle the exception and show a message
            JOptionPane.showMessageDialog(this,
                    "Error finding user: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            // Show a pop-up warning if validation fails
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        newStockCountLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        userIDLabel = new javax.swing.JLabel();
        userIDLabel2 = new javax.swing.JLabel();
        newStockCountValue = new javax.swing.JLabel();
        backToHomeButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        supplierStockInformationTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        stockTable = new javax.swing.JTable();
        actionDescriptionLabel2 = new javax.swing.JLabel();
        actionDescriptionLabel3 = new javax.swing.JLabel();
        actionDescriptionLabel4 = new javax.swing.JLabel();
        actionDescriptionLabel5 = new javax.swing.JLabel();
        filterInformationLabel = new javax.swing.JLabel();
        filterInformationTextField = new javax.swing.JTextField();
        filterItemLabel = new javax.swing.JLabel();
        filterItemTextField = new javax.swing.JTextField();
        stockTableLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        filterItemLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(50, 54, 57));

        newStockCountLabel.setForeground(new java.awt.Color(225, 225, 225));
        newStockCountLabel.setText("New stock :");
        newStockCountLabel.setToolTipText("");

        usernameLabel.setBackground(new java.awt.Color(0, 0, 0));
        usernameLabel.setForeground(new java.awt.Color(225, 225, 225));
        usernameLabel.setText("username");

        userIDLabel.setForeground(new java.awt.Color(225, 225, 225));
        userIDLabel.setText("userid");
        userIDLabel.setToolTipText("");

        userIDLabel2.setToolTipText("");

        newStockCountValue.setText("value");
        newStockCountValue.setToolTipText("");

        backToHomeButton.setText("Back to Home");
        backToHomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToHomeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(newStockCountLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newStockCountValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(userIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(userIDLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(backToHomeButton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newStockCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newStockCountValue, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userIDLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backToHomeButton)
                .addContainerGap())
        );

        supplierStockInformationTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(supplierStockInformationTable);

        stockTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(stockTable);

        actionDescriptionLabel2.setText("Click any of the cell to modify it");

        actionDescriptionLabel3.setForeground(new java.awt.Color(254, 0, 0));
        actionDescriptionLabel3.setText("*");

        actionDescriptionLabel4.setText("Click any of the cell to modify it");

        actionDescriptionLabel5.setForeground(new java.awt.Color(254, 0, 0));
        actionDescriptionLabel5.setText("*");

        filterInformationLabel.setText("Filter information: ");

        filterInformationTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterInformationTextFieldActionPerformed(evt);
            }
        });

        filterItemLabel.setText("Filter item:");

        stockTableLabel.setText("Stock table");

        jLabel2.setText("Supplier stock information table");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(filterItemLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(filterItemTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(83, 83, 83)
                                        .addComponent(stockTableLabel)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(filterInformationLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(filterInformationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2)
                                        .addGap(34, 34, 34)
                                        .addComponent(actionDescriptionLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(actionDescriptionLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(filterItemLabel1)
                                .addGap(201, 201, 201)
                                .addComponent(actionDescriptionLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(actionDescriptionLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(actionDescriptionLabel2)
                        .addComponent(actionDescriptionLabel3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(filterInformationLabel)
                        .addComponent(filterInformationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterItemLabel)
                    .addComponent(filterItemTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(actionDescriptionLabel4)
                    .addComponent(actionDescriptionLabel5)
                    .addComponent(stockTableLabel)
                    .addComponent(filterItemLabel1))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filterInformationTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterInformationTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterInformationTextFieldActionPerformed

    private void backToHomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToHomeButtonActionPerformed
        new InventoryManagerHomeUI(inventoryManager).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backToHomeButtonActionPerformed

    private void loadStockData() {
        try {
            String[] columnNames = {"Item ID", "Item Name", "Quantity", "Reorder Point", "SupplierId", "Stock Status"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            List<Item> items = inventoryManager.getInventoryItems();

            for (Item item : items) {
                model.addRow(new Object[]{
                    item.getItemID(),
                    item.getName(),
                    item.getQuantity(),
                    item.getReorderPoint(),
                    item.getSupplierID(),
                    item.getStockStatus(),});
            }

            stockTable.setModel(model);

            // Set the custom renderer for the stock status column (index 5)
            stockTable.getColumnModel().getColumn(5).setCellRenderer(new StockStatusRenderer());

        } catch (IOException e) {
            System.err.println("Error loading item data: " + e.getMessage());
        }
    }

    private void filterStockTable() {
        String filterText = filterItemTextField.getText().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
        model.setRowCount(0);

        try {
            List<Item> items = inventoryManager.getInventoryItems();
            items.stream()
                    .filter(item -> item.getItemID().toLowerCase().contains(filterText)
                    || item.getName().toLowerCase().contains(filterText)
                    || item.getQuantity().toLowerCase().contains(filterText)
                    || item.getReorderPoint().toLowerCase().contains(filterText)
                    || item.getSupplierID().toLowerCase().contains(filterText)
                    || item.getStockStatus().toLowerCase().contains(filterText)
                    )
                    .forEach(item -> {
                        model.addRow(new Object[]{
                            item.getItemID(),
                            item.getName(),
                            item.getQuantity(),
                            item.getReorderPoint(),
                            item.getSupplierID(),
                            item.getStockStatus(),
                            "Actions" // Placeholder for the Actions column
                        });
                    });
        } catch (IOException e) {
            System.err.println("Error loading user data: " + e.getMessage());
        }

    }

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

    public static void main(String args[]) {


        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ManageStockUI().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actionDescriptionLabel2;
    private javax.swing.JLabel actionDescriptionLabel3;
    private javax.swing.JLabel actionDescriptionLabel4;
    private javax.swing.JLabel actionDescriptionLabel5;
    private javax.swing.JButton backToHomeButton;
    private javax.swing.JLabel filterInformationLabel;
    private javax.swing.JTextField filterInformationTextField;
    private javax.swing.JLabel filterItemLabel;
    private javax.swing.JLabel filterItemLabel1;
    private javax.swing.JTextField filterItemTextField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel newStockCountLabel;
    private javax.swing.JLabel newStockCountValue;
    private javax.swing.JTable stockTable;
    private javax.swing.JLabel stockTableLabel;
    private javax.swing.JTable supplierStockInformationTable;
    private javax.swing.JLabel userIDLabel;
    private javax.swing.JLabel userIDLabel2;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
