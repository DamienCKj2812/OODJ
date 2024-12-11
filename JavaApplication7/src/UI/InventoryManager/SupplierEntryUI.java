package UI.InventoryManager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import models.InventoryManager;
import models.Item;
import models.Supplier;
import utils.InputValidator;
import utils.LogHandler;

public class SupplierEntryUI extends javax.swing.JFrame {

    private InventoryManager inventoryManager;
    private LogHandler logHandler;

    public SupplierEntryUI(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
        this.logHandler = new LogHandler(inventoryManager);
        initComponents();
        loadSupplierData();
        supplierTable.setRowHeight(30);

        supplierTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Detect single click
                    int row = supplierTable.rowAtPoint(e.getPoint());
                    int col = supplierTable.columnAtPoint(e.getPoint());
                    if (row >= 0 && col >= 0) {
                        if (col == 0) { // Check if the clicked column is the first column (User ID)
                            JOptionPane.showMessageDialog(supplierTable,
                                    "You cannot change the Item ID",
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
                filterSupplierTable();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filterSupplierTable();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filterSupplierTable();
            }
        });
    }

    private void showCellContentDialog(int row, int col) {
        Object cellContent = supplierTable.getValueAt(row, col);
        String columnName = supplierTable.getColumnName(col);
        String supplierId = (String) supplierTable.getValueAt(row, 0);
        Supplier selectedSupplier = null;

        try {
            selectedSupplier = inventoryManager.getSupplier(supplierId);
            if (selectedSupplier == null) {
                JOptionPane.showMessageDialog(this,
                        "Supplier not found: " + supplierId,
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            JTextField textField = new JTextField(cellContent != null ? cellContent.toString() : "");

            JButton deleteButton = new JButton("Delete Supplier");
            deleteButton.setBackground(Color.RED);
            deleteButton.setForeground(Color.WHITE);

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int confirm = JOptionPane.showConfirmDialog(deleteButton.getParent(),
                            "Are you sure you want to delete this supplier?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            Supplier deletedSupplier = inventoryManager.removeSupplier(supplierId);
                            ((DefaultTableModel) supplierTable.getModel()).removeRow(row);
                            JOptionPane.showMessageDialog(deleteButton.getParent(),
                                    "Supplier deleted successfully: " + deletedSupplier.getId(),
                                    "Delete Successful",
                                    JOptionPane.INFORMATION_MESSAGE);

                            logHandler.addLogActionToFile(String.format(
                                    "Removed the Supplier (%s - %s)",
                                    deletedSupplier.getId(),
                                    deletedSupplier.getName()
                            ));
                        } catch (IOException e1) {
                            JOptionPane.showMessageDialog(deleteButton.getParent(),
                                    "Failed to delete Supplier: " + e1.getMessage(),
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
                InputValidator.validateNotEmpty(newContent, columnName); // Validate new input

                Supplier updatedSupplier = null;
                switch (columnName) {
                    case "Supplier Name":
                        updatedSupplier = inventoryManager.updateSupplier(supplierId, newContent, selectedSupplier.getContactPerson(), selectedSupplier.getAddress(), selectedSupplier.getPhoneNumber(), selectedSupplier.getProductsSupplied());
                        break;
                    case "Contact Person":
                        updatedSupplier = inventoryManager.updateSupplier(supplierId, selectedSupplier.getName(), newContent, selectedSupplier.getAddress(), selectedSupplier.getPhoneNumber(), selectedSupplier.getProductsSupplied());
                        break;
                    case "Address":
                        updatedSupplier = inventoryManager.updateSupplier(supplierId, selectedSupplier.getName(), selectedSupplier.getContactPerson(), newContent, selectedSupplier.getPhoneNumber(), selectedSupplier.getProductsSupplied());
                        break;
                    case "Phone Number":
                        updatedSupplier = inventoryManager.updateSupplier(supplierId, selectedSupplier.getName(), selectedSupplier.getContactPerson(), selectedSupplier.getAddress(), newContent, selectedSupplier.getProductsSupplied());
                        break;
                    case "Products Supplied":
                        updatedSupplier = inventoryManager.updateSupplier(supplierId, selectedSupplier.getName(), selectedSupplier.getContactPerson(), selectedSupplier.getAddress(), selectedSupplier.getPhoneNumber(), newContent);
                        break;
                    default:
                        // If column is not recognized, handle accordingly
                        JOptionPane.showMessageDialog(this,
                                "Column not recognized: " + columnName,
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                }

                if (updatedSupplier != null) {
                    supplierTable.setValueAt(newContent, row, col);
                    logHandler.addLogActionToFile(String.format(
                            "Modified the Item's %s (%s): from %s to %s",
                            columnName,
                            updatedSupplier.getId(),
                            cellContent,
                            newContent
                    ));
                    JOptionPane.showMessageDialog(this,
                            columnName + ": " + newContent,
                            "Update Successful!",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Failed to update supplier: " + supplierId,
                            "Update Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (IOException e) {
            // Handle the exception and show a message
            JOptionPane.showMessageDialog(this,
                    "Error finding supplier: " + e.getMessage(),
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

    private void filterSupplierTable() {
        String filterText = filterTextField.getText().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) supplierTable.getModel();
        model.setRowCount(0);

        try {
            List<Supplier> suppliers = inventoryManager.getAllSupplier();
            suppliers.stream()
                    .filter(supplier -> supplier.getId().toLowerCase().contains(filterText)
                    || supplier.getName().toLowerCase().contains(filterText)
                    || supplier.getName().toLowerCase().contains(filterText)
                    || supplier.getContactPerson().toLowerCase().contains(filterText)
                    || supplier.getAddress().toLowerCase().contains(filterText)
                    || supplier.getPhoneNumber().toLowerCase().contains(filterText)
                    || supplier.getProductsSupplied().toLowerCase().contains(filterText))
                    .forEach(supplier -> {
                        model.addRow(new Object[]{
                            supplier.getId(),
                            supplier.getName(),
                            supplier.getContactPerson(),
                            supplier.getAddress(),
                            supplier.getPhoneNumber(),
                            supplier.getProductsSupplied(),
                            "Actions" // Placeholder for the Actions column
                        });
                    });
        } catch (IOException e) {
            System.err.println("Error loading user data: " + e.getMessage());
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
        supplierTable = new javax.swing.JTable();
        filterTextField = new javax.swing.JTextField();
        actionDescriptionLabel2 = new javax.swing.JLabel();
        actionDescriptionLabel3 = new javax.swing.JLabel();
        addNewSupplierButton = new java.awt.Button();
        filterLabel = new javax.swing.JLabel();

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 260, Short.MAX_VALUE)
                .addComponent(backToHomeButton)
                .addContainerGap())
        );

        supplierTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(supplierTable);

        filterTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterTextFieldActionPerformed(evt);
            }
        });

        actionDescriptionLabel2.setText("Click any of the cell to modify it");

        actionDescriptionLabel3.setForeground(new java.awt.Color(254, 0, 0));
        actionDescriptionLabel3.setText("*");

        addNewSupplierButton.setLabel("Add new supplier");
        addNewSupplierButton.setName(""); // NOI18N
        addNewSupplierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewSupplierButtonActionPerformed(evt);
            }
        });

        filterLabel.setText("Filter:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filterLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addNewSupplierButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(actionDescriptionLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(actionDescriptionLabel2)
                        .addGap(17, 17, 17))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(actionDescriptionLabel3)
                            .addComponent(actionDescriptionLabel2))
                        .addGap(8, 8, 8))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(addNewSupplierButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(filterLabel)
                            .addComponent(filterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        addNewSupplierButton.getAccessibleContext().setAccessibleName("Add new supplier");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backToHomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToHomeButtonActionPerformed
        new InventoryManagerHomeUI(inventoryManager).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backToHomeButtonActionPerformed

    private void filterTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterTextFieldActionPerformed

    private void addNewSupplierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewSupplierButtonActionPerformed
        JDialog addSupplierDialog = new JDialog((JFrame) null, "Add New Item", true);
        addSupplierDialog.setLayout(new BoxLayout(addSupplierDialog.getContentPane(), BoxLayout.Y_AXIS));
        addSupplierDialog.setSize(300, 300);
        addSupplierDialog.setLocationRelativeTo(null);

        // Create input fields
        JTextField supplierNameField = new JTextField(20);
        JTextField contactPerson = new JTextField(20);
        JTextField address = new JTextField(20);
        JTextField phoneNumber = new JTextField(20);
        JTextField productSupplied = new JTextField(20);

        // Add components to the dialog
        addSupplierDialog.add(new JLabel("Supplier Name:"));
        addSupplierDialog.add(supplierNameField);

        addSupplierDialog.add(new JLabel("Contact Person:"));
        addSupplierDialog.add(contactPerson);

        addSupplierDialog.add(new JLabel("Address:"));
        addSupplierDialog.add(address);

        addSupplierDialog.add(new JLabel("Phone Number:"));
        addSupplierDialog.add(phoneNumber);

        addSupplierDialog.add(new JLabel("Products Supplied:"));
        addSupplierDialog.add(productSupplied);

        JButton okButton = new JButton("Add");
        okButton.addActionListener(e -> {
            try {
                InputValidator.validateNotEmpty(supplierNameField.getText(), "Supplier Name");
                InputValidator.validateNotEmpty(contactPerson.getText(), "Contact Person");
                InputValidator.validateNotEmpty(address.getText(), "Address");
                InputValidator.validateNotEmpty(phoneNumber.getText(), "Phone Number");
                InputValidator.validateNotEmpty(productSupplied.getText(), "Product Supplied");

                Supplier addedSuppier = inventoryManager.addSupplier(supplierNameField.getText(), contactPerson.getText(), address.getText(), phoneNumber.getText(), productSupplied.getText());

                // Log the action
                logHandler.addLogActionToFile(String.format(
                        "Add an supplier (id: %s, name: %s, contact person: %s, address: %s, phone number: %s, products supplied: %s)",
                        addedSuppier.getId(),
                        addedSuppier.getName(),
                        addedSuppier.getContactPerson(),
                        addedSuppier.getAddress(),
                        addedSuppier.getPhoneNumber(),
                        addedSuppier.getProductsSupplied()
                ));

                loadSupplierData();
                addSupplierDialog.dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(addSupplierDialog,
                        "Failed to add supplier.",
                        "Update Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        addSupplierDialog.add(okButton);

        addSupplierDialog.setVisible(true);
    }//GEN-LAST:event_addNewSupplierButtonActionPerformed

    private void loadSupplierData() {
        try {
            String[] columnNames = {"Supplier ID", "Supplier Name", "Contact Person", "Address", "Phone Number", "Products Supplied"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            List<Supplier> suppliers = inventoryManager.getAllSupplier();

            for (Supplier supplier : suppliers) {
                model.addRow(new Object[]{
                    supplier.getId(),
                    supplier.getName(),
                    supplier.getContactPerson(),
                    supplier.getAddress(),
                    supplier.getPhoneNumber(),
                    supplier.getProductsSupplied(),
                    "Actions" // Placeholder for the Actions column
                });
            }

            supplierTable.setModel(model);

        } catch (IOException e) {
            System.err.println("Error loading supplier data: " + e.getMessage());
        }
    }

    public static void main(String args[]) {

//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SupplierEntryUI().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actionDescriptionLabel2;
    private javax.swing.JLabel actionDescriptionLabel3;
    private java.awt.Button addNewSupplierButton;
    private javax.swing.JButton backToHomeButton;
    private javax.swing.JLabel filterLabel;
    private javax.swing.JTextField filterTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel newStockCountLabel;
    private javax.swing.JLabel newStockCountValue;
    private javax.swing.JTable supplierTable;
    private javax.swing.JLabel userIDLabel;
    private javax.swing.JLabel userIDLabel2;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
