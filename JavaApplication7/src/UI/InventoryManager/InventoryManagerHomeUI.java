package UI.InventoryManager;

import UI.Admin.AdminHomeUI;
import models.Admin;
import models.InventoryManager;
import models.User;

public class InventoryManagerHomeUI extends javax.swing.JFrame {

    private User user;

    public InventoryManagerHomeUI(User user) {
        this.user = user;
        initComponents();

        usernameLabel.setText(user.getUsername());
        userIDLabel.setText(user.getUserID());

        if (user instanceof Admin) {
            AdminPageButton.setVisible(true); // Make the button visible
        } else {
            AdminPageButton.setVisible(false); // Hide the button for non-admin users
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        menuBar2 = new java.awt.MenuBar();
        menu3 = new java.awt.Menu();
        menu4 = new java.awt.Menu();
        itemEntryButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        newStockCountLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        userIDLabel = new javax.swing.JLabel();
        userIDLabel2 = new javax.swing.JLabel();
        newStockCountValue = new javax.swing.JLabel();
        AdminPageButton = new javax.swing.JButton();
        itemEntryButton = new javax.swing.JButton();
        itemEntryLabel = new javax.swing.JLabel();
        supplierEntryButton = new javax.swing.JButton();
        supplierEntryLabel = new javax.swing.JLabel();
        updateStockLevelButton = new javax.swing.JButton();
        updateStockLevelLabel = new javax.swing.JLabel();
        editStockInfoButton = new javax.swing.JButton();
        editStockInfoLabel = new javax.swing.JLabel();

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        menu3.setLabel("File");
        menuBar2.add(menu3);

        menu4.setLabel("Edit");
        menuBar2.add(menu4);

        itemEntryButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Inventory.png"))); // NOI18N
        itemEntryButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemEntryButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEntryButton1ActionPerformed(evt);
            }
        });

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

        AdminPageButton.setText("Admin Page");
        AdminPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminPageButtonActionPerformed(evt);
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
                        .addComponent(AdminPageButton)))
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
                .addGap(49, 49, 49)
                .addComponent(AdminPageButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        itemEntryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/inventory.png"))); // NOI18N
        itemEntryButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemEntryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEntryButtonActionPerformed(evt);
            }
        });

        itemEntryLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        itemEntryLabel.setText("Item Entry");

        supplierEntryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/supplier.png"))); // NOI18N
        supplierEntryButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        supplierEntryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierEntryButtonActionPerformed(evt);
            }
        });

        supplierEntryLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        supplierEntryLabel.setText("Supplier Entry");

        updateStockLevelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/update-stock.png"))); // NOI18N
        updateStockLevelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateStockLevelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStockLevelButtonActionPerformed(evt);
            }
        });

        updateStockLevelLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        updateStockLevelLabel.setText("Update Stock Level");

        editStockInfoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/ready-stock.png"))); // NOI18N
        editStockInfoButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editStockInfoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editStockInfoButtonActionPerformed(evt);
            }
        });

        editStockInfoLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        editStockInfoLabel.setText("Edit Stock Info");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateStockLevelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(itemEntryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(updateStockLevelLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(itemEntryLabel)))
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(supplierEntryButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(supplierEntryLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editStockInfoLabel)
                            .addComponent(editStockInfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(61, 61, 61))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(itemEntryButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(itemEntryLabel)
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateStockLevelButton)
                            .addComponent(editStockInfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editStockInfoLabel)
                            .addComponent(updateStockLevelLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(supplierEntryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(supplierEntryLabel)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemEntryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEntryButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemEntryButtonActionPerformed

    private void supplierEntryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierEntryButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supplierEntryButtonActionPerformed

    private void itemEntryButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEntryButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemEntryButton1ActionPerformed

    private void updateStockLevelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStockLevelButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateStockLevelButtonActionPerformed

    private void editStockInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editStockInfoButtonActionPerformed
        if (user instanceof Admin) {
            Admin admin = (Admin) user;
            new EditStockInfoUI(admin.getInventoryManager()).setVisible(true);
        } else {
            InventoryManager inventoryManager = (InventoryManager) user;
            new EditStockInfoUI(inventoryManager).setVisible(true);
        }
        this.dispose();

    }//GEN-LAST:event_editStockInfoButtonActionPerformed

    private void AdminPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminPageButtonActionPerformed
        if (user instanceof Admin) {
            Admin admin = (Admin) user;
            new AdminHomeUI(admin).setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_AdminPageButtonActionPerformed

    public static void main(String args[]) {

//        testing - purpose
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new InventoryManagerHomeUI(this).setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AdminPageButton;
    private javax.swing.JButton editStockInfoButton;
    private javax.swing.JLabel editStockInfoLabel;
    private javax.swing.JButton itemEntryButton;
    private javax.swing.JButton itemEntryButton1;
    private javax.swing.JLabel itemEntryLabel;
    private javax.swing.JPanel jPanel1;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.Menu menu3;
    private java.awt.Menu menu4;
    private java.awt.MenuBar menuBar1;
    private java.awt.MenuBar menuBar2;
    private javax.swing.JLabel newStockCountLabel;
    private javax.swing.JLabel newStockCountValue;
    private javax.swing.JButton supplierEntryButton;
    private javax.swing.JLabel supplierEntryLabel;
    private javax.swing.JButton updateStockLevelButton;
    private javax.swing.JLabel updateStockLevelLabel;
    private javax.swing.JLabel userIDLabel;
    private javax.swing.JLabel userIDLabel2;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
