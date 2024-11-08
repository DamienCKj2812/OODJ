/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.Admin;

import UI.InventoryManager.InventoryManagerHomeUI;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import models.Admin;
import models.User;
import utils.InputValidator;
import utils.LogHandler;

/**
 *
 * @author Aorus
 */
public class UserManagementUI extends javax.swing.JFrame {

    private Admin admin;
    private InputValidator inputValidator;
    private LogHandler logHandler;

    public UserManagementUI(Admin admin) {
        this.admin = admin;
        this.logHandler = new LogHandler(admin);
        initComponents();
        loadUserData();
        userTable.setRowHeight(30);

        userTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Detect single click
                    int row = userTable.rowAtPoint(e.getPoint());
                    int col = userTable.columnAtPoint(e.getPoint());
                    if (row >= 0 && col >= 0) {
                        if (col == 0) { // Check if the clicked column is the first column (User ID)
                            JOptionPane.showMessageDialog(userTable,
                                    "You cannot change the User ID",
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
                filterUserTable();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filterUserTable();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filterUserTable();
            }
        });
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
        userTable = new javax.swing.JTable();
        filterLabel = new javax.swing.JLabel();
        filterTextField = new javax.swing.JTextField();
        actionDescriptionLabel1 = new javax.swing.JLabel();
        actionDescriptionLabel = new javax.swing.JLabel();

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                .addComponent(backToHomeButton)
                .addContainerGap())
        );

        userTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(userTable);

        filterLabel.setText("Filter:");

        actionDescriptionLabel1.setText("Click any of the cell to modify it");

        actionDescriptionLabel.setForeground(new java.awt.Color(254, 0, 0));
        actionDescriptionLabel.setText("*");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filterLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(actionDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(actionDescriptionLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterLabel)
                    .addComponent(filterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(actionDescriptionLabel1)
                    .addComponent(actionDescriptionLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backToHomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToHomeButtonActionPerformed
        new AdminHomeUI(admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backToHomeButtonActionPerformed

    // Function to display cell content in a dialog
    private void showCellContentDialog(int row, int col) {
        Object cellContent = userTable.getValueAt(row, col);
        String columnName = userTable.getColumnName(col);
        String userID = (String) userTable.getValueAt(row, 0); // Get userID from the first column
        User selectedUser = null; // Initialize selectedUser

        try {
            selectedUser = admin.findUser(userID);
            if (selectedUser == null) {
                JOptionPane.showMessageDialog(this,
                        "User not found: " + userID,
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return; // Exit if the user is not found
            }

            // Create a component based on the column name
            JComponent inputComponent;
            if ("Role".equals(columnName)) {
                // Create a JComboBox for the "Role" column with predefined roles
                JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"admin", "salesManager", "purchaseManager", "financeManagaer", "inventoryManager"});
                roleComboBox.setSelectedItem(cellContent != null ? cellContent.toString() : "");
                inputComponent = roleComboBox;
            } else {
                // Use JTextField for other columns
                JTextField textField = new JTextField(cellContent != null ? cellContent.toString() : "");
                inputComponent = textField;
            }

            Object[] message = {
                "Before: " + (cellContent != null ? cellContent.toString() : "No content"),
                inputComponent
            };

            int result = JOptionPane.showConfirmDialog(this,
                    message,
                    "Edit " + columnName,
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String newContent;
                if ("Role".equals(columnName)) {
                    // Get the selected value from JComboBox for "Role"
                    newContent = (String) ((JComboBox<?>) inputComponent).getSelectedItem();
                } else {
                    // Get text from JTextField for other columns
                    newContent = ((JTextField) inputComponent).getText();
                }

                try {
                    inputValidator.validateNotEmpty(newContent, columnName); // Validate new input

                    // Call updateUser based on the column name
                    boolean updateSuccessful = false;
                    switch (columnName) {
                        case "Username":
                            updateSuccessful = admin.updateUser(userID, newContent, selectedUser.getPassword(), selectedUser.getRole(), selectedUser.getStatus());
                            break;
                        case "Password":
                            updateSuccessful = admin.updateUser(userID, selectedUser.getUsername(), newContent, selectedUser.getRole(), selectedUser.getStatus());
                            break;
                        case "Role":
                            updateSuccessful = admin.updateUser(userID, selectedUser.getUsername(), selectedUser.getPassword(), newContent, selectedUser.getStatus());
                            break;
                        case "Account Status":
                            updateSuccessful = admin.updateUser(userID, selectedUser.getUsername(), selectedUser.getPassword(), selectedUser.getRole(), newContent);
                            break;
                        default:
                            JOptionPane.showMessageDialog(this,
                                    "Column not recognized: " + columnName,
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                    }

                    if (updateSuccessful) {
                        userTable.setValueAt(newContent, row, col); // Update the table if the update was successful
                        logHandler.addLogActionToFile(MessageFormat.format("admin: {0} changed the {1} of the user: {2} from {3} to {4}", admin.getUsername(), columnName, selectedUser.getUsername(), cellContent, newContent));
                        JOptionPane.showMessageDialog(this,
                                columnName + ": " + newContent,
                                "Update Successful!",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Failed to update user: " + userID,
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

    private void loadUserData() {
        try {
            String[] columnNames = {"User ID", "Username", "Password", "Role", "Account Status"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            List<User> users = admin.getAllUsers();

            for (User user : users) {
                model.addRow(new Object[]{
                    user.getUserID(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getRole(),
                    user.getStatus(),
                    "Actions" // Placeholder for the Actions column
                });
            }

            userTable.setModel(model);

        } catch (IOException e) {
            System.err.println("Error loading user data: " + e.getMessage());
        }
    }

    private void filterUserTable() {
        String filterText = filterTextField.getText().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setRowCount(0); // Clear the current table rows

        try {
            List<User> users = admin.getAllUsers(); // Get all users from the admin
            users.stream()
                    .filter(user -> user.getUsername().toLowerCase().contains(filterText)
                    || user.getRole().toLowerCase().contains(filterText)
                    || user.getUserID().toLowerCase().contains(filterText)
                    || user.getStatus().toLowerCase().contains(filterText))
                    .forEach(user -> {
                        model.addRow(new Object[]{
                            user.getUserID(),
                            user.getUsername(),
                            user.getPassword(),
                            user.getRole(),
                            user.getStatus(),
                            "Actions" // Placeholder for the Actions column
                        });
                    });
        } catch (IOException e) {
            System.err.println("Error loading user data: " + e.getMessage());
        }

    }

    public static void main(String args[]) {
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actionDescriptionLabel;
    private javax.swing.JLabel actionDescriptionLabel1;
    private javax.swing.JButton backToHomeButton;
    private javax.swing.JLabel filterLabel;
    private javax.swing.JTextField filterTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel newStockCountLabel;
    private javax.swing.JLabel newStockCountValue;
    private javax.swing.JLabel userIDLabel;
    private javax.swing.JLabel userIDLabel2;
    private javax.swing.JTable userTable;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
