/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.InventoryManager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import models.InventoryManager;
import models.Item;
import models.User;
import utils.InputValidator;
import utils.LogHandler;

/**
 *
 * @author Aorus
 */
public class EditStockInfoUI extends javax.swing.JFrame {

    private InventoryManager inventoryManager;
    private LogHandler logHandler;

    public EditStockInfoUI(InventoryManager inventoryManager) {
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
                        if (col == 0) { // Check if the clicked column is the first column (User ID)
                            JOptionPane.showMessageDialog(stockTable,
                                    "You cannot change the Item ID",
                                    "Warning",
                                    JOptionPane.WARNING_MESSAGE);
                        } else if (col == 4) {
                            JOptionPane.showMessageDialog(stockTable,
                                    "You cannot change the Supplier ID",
                                    "Warning",
                                    JOptionPane.WARNING_MESSAGE);
                        } else {
                            // Call the method to show the dialog for editing other columns
                            showCellContentDialog(row, col);
                        }
                    }
                }
            }
        });

        filterTextField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
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

    private void loadStockData() {
        try {
            String[] columnNames = {"Item ID", "Stock Name", "Price", "Quantity", "SupplierId"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            List<Item> items = inventoryManager.getInventoryItems();

            for (Item item : items) {
                model.addRow(new Object[]{
                    item.getItemID(),
                    item.getName(),
                    item.getPrice(),
                    item.getQuantity(),
                    item.getSupplierID(),
                    "Actions" // Placeholder for the Actions column
                });
            }

            stockTable.setModel(model);

        } catch (IOException e) {
            System.err.println("Error loading item data: " + e.getMessage());
        }
    }

    private void filterStockTable() {
        String filterText = filterTextField.getText().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
        model.setRowCount(0);

        try {
            List<Item> items = inventoryManager.getInventoryItems();
            items.stream()
                    .filter(item -> item.getItemID().toLowerCase().contains(filterText)
                    || item.getName().toLowerCase().contains(filterText)
                    || item.getPrice().toLowerCase().contains(filterText)
                    || item.getQuantity().toLowerCase().contains(filterText)
                    || item.getSupplierID().toLowerCase().contains(filterText))
                    .forEach(item -> {
                        model.addRow(new Object[]{
                            item.getItemID(),
                            item.getName(),
                            item.getPrice(),
                            item.getQuantity(),
                            item.getSupplierID(),
                            "Actions" // Placeholder for the Actions column
                        });
                    });
        } catch (IOException e) {
            System.err.println("Error loading user data: " + e.getMessage());
        }

    }

    private void showCellContentDialog(int row, int col) {
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

            JButton deleteButton = new JButton("Delete Item");
            deleteButton.setBackground(Color.RED);
            deleteButton.setForeground(Color.WHITE);

            deleteButton.addActionListener(new ActionListener() {
                private boolean itemDeleted = false; // Track item deletion status

                @Override
                public void actionPerformed(ActionEvent e) {
                    int confirm = JOptionPane.showConfirmDialog(deleteButton.getParent(),
                            "Are you sure you want to delete this item?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            Item deletedItem = inventoryManager.deleteItem(itemId); 
                            ((DefaultTableModel) stockTable.getModel()).removeRow(row);
                            JOptionPane.showMessageDialog(deleteButton.getParent(),
                                    "Item deleted successfully: " + deletedItem.getItemID(),
                                    "Delete Successful",
                                    JOptionPane.INFORMATION_MESSAGE);
                            itemDeleted = true; // Set itemDeleted to true after successful deletion

                            if (itemDeleted) {
                                logHandler.addLogActionToFile(String.format(
                                        "user: %s has removed the item (%s - %s)",
                                        inventoryManager.getUsername(),
                                        deletedItem.getItemID(),
                                        deletedItem.getName()
                                ));
                            }

                        } catch (IOException e1) {
                            JOptionPane.showMessageDialog(deleteButton.getParent(),
                                    "Failed to delete item: " + e1.getMessage(),
                                    "Delete Failed",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });

            Object[] message = {
                "Before: " + (cellContent != null ? cellContent.toString() : "No content"),
                textField,
                deleteButton // Add delete button to the dialog
            };

            int result = JOptionPane.showConfirmDialog(this,
                    message,
                    "Edit " + columnName,
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String newContent = textField.getText();
                try {
                    InputValidator.validateNotEmpty(newContent, columnName); // Validate new input

                    // Additional validation based on column name
                    if ("Price".equals(columnName)) {
                        InputValidator.validateIsNumber(newContent, columnName); // Validate if it's a number
                    } else if ("Quantity".equals(columnName)) {
                        InputValidator.validateIsInteger(newContent, columnName); // Validate if it's an integer
                    }

                    boolean updateSuccessful = false;
                    switch (columnName) {
                        case "Stock Name":
                            updateSuccessful = inventoryManager.updateItem(itemId, newContent, selectedItem.getPrice(), selectedItem.getQuantity(), selectedItem.getSupplierID());
                            break;
                        case "Price":
                            updateSuccessful = inventoryManager.updateItem(itemId, selectedItem.getName(), newContent, selectedItem.getQuantity(), selectedItem.getSupplierID());
                            break;
                        case "Quantity":
                            updateSuccessful = inventoryManager.updateItem(itemId, selectedItem.getName(), selectedItem.getPrice(), newContent, selectedItem.getSupplierID());
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
                                "user: %s has modified the Item's %s (%s): from %s to %s",
                                inventoryManager.getUsername(),
                                columnName,
                                selectedItem.getItemID(),
                                cellContent,
                                newContent
                        ));
                        JOptionPane.showMessageDialog(this,
                                columnName + ": " + newContent,
                                "Update Successful!",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Failed to update item: " + itemId,
                                "Update Failed",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } catch (IllegalArgumentException ex) {
                    // Show a pop-up warning if validation fails
                    JOptionPane.showMessageDialog(this,
                            ex.getMessage(),
                            "Input Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }

        } catch (IOException e) {
            // Handle the exception and show a message
            JOptionPane.showMessageDialog(this,
                    "Error finding user: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return; // Exit the method on error
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
        newStockCountLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        userIDLabel = new javax.swing.JLabel();
        userIDLabel2 = new javax.swing.JLabel();
        newStockCountValue = new javax.swing.JLabel();
        backToHomeButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        stockTable = new javax.swing.JTable();
        filterLabel = new javax.swing.JLabel();
        filterTextField = new javax.swing.JTextField();
        actionDescriptionLabel2 = new javax.swing.JLabel();
        actionDescriptionLabel3 = new javax.swing.JLabel();

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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(userIDLabel2))
                            .addComponent(backToHomeButton))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(backToHomeButton)
                .addContainerGap())
        );

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
        jScrollPane1.setViewportView(stockTable);

        filterLabel.setText("Filter:");

        actionDescriptionLabel2.setText("Click any of the cell to modify it");

        actionDescriptionLabel3.setForeground(new java.awt.Color(254, 0, 0));
        actionDescriptionLabel3.setText("*");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filterLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(actionDescriptionLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(actionDescriptionLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterLabel)
                    .addComponent(filterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(actionDescriptionLabel2)
                    .addComponent(actionDescriptionLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backToHomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToHomeButtonActionPerformed
        new InventoryManagerHomeUI(inventoryManager).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backToHomeButtonActionPerformed

    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new EditStockInfoUI(inventoryManager).setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actionDescriptionLabel2;
    private javax.swing.JLabel actionDescriptionLabel3;
    private javax.swing.JButton backToHomeButton;
    private javax.swing.JLabel filterLabel;
    private javax.swing.JTextField filterTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel newStockCountLabel;
    private javax.swing.JLabel newStockCountValue;
    private javax.swing.JTable stockTable;
    private javax.swing.JLabel userIDLabel;
    private javax.swing.JLabel userIDLabel2;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
