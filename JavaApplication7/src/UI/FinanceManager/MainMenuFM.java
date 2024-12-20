/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.FinanceManager;

import UI.Admin.AdminHomeUI;
import UI.Authentication.LoginUI;
import models.Admin;
import models.FinanceManager;
import models.User;
import state.UserSession;

/**
 *
 * @author Dev
 */
public class MainMenuFM extends javax.swing.JFrame {

    private User user;
    UserSession userState = UserSession.getInstance();
    Admin admin = userState.getLoggedInAdmin();

    public MainMenuFM(User user) {
        this.user = user;
        initComponents();

        if (admin != null) {
            backToAdminButton.setVisible(true); // Make the button visible
        } else {
            backToAdminButton.setVisible(false); // Hide the button for non-admin users
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        panel1 = new java.awt.Panel();
        stockStatusButton = new javax.swing.JButton();
        purchaseOrderButton = new javax.swing.JButton();
        makePaymentButton = new javax.swing.JButton();
        supplierPaymentButton = new javax.swing.JButton();
        backToAdminButton = new javax.swing.JButton();
        MainMenuFM_greet1 = new javax.swing.JLabel();
        MainMenuFM_username = new javax.swing.JLabel();
        MainMenuFM_greet2 = new javax.swing.JLabel();
        MainMenuFM_LO1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 204, 255));
        setResizable(false);

        panel1.setPreferredSize(new java.awt.Dimension(800, 600));

        stockStatusButton.setBackground(new java.awt.Color(163, 176, 204));
        stockStatusButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        stockStatusButton.setForeground(new java.awt.Color(27, 27, 30));
        stockStatusButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/stock-warehouse-removebg.png"))); // NOI18N
        stockStatusButton.setText("Stock Status");
        stockStatusButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        stockStatusButton.setIconTextGap(10);
        stockStatusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockStatusButtonActionPerformed(evt);
            }
        });

        purchaseOrderButton.setBackground(new java.awt.Color(216, 219, 226));
        purchaseOrderButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        purchaseOrderButton.setForeground(new java.awt.Color(55, 63, 81));
        purchaseOrderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/po-removebg.png"))); // NOI18N
        purchaseOrderButton.setText("Purchase Order");
        purchaseOrderButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        purchaseOrderButton.setIconTextGap(10);
        purchaseOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseOrderButtonActionPerformed(evt);
            }
        });

        makePaymentButton.setBackground(new java.awt.Color(153, 157, 204));
        makePaymentButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        makePaymentButton.setForeground(new java.awt.Color(54, 49, 76));
        makePaymentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/payment-removebg.png"))); // NOI18N
        makePaymentButton.setText("Make Payment");
        makePaymentButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        makePaymentButton.setIconTextGap(10);
        makePaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makePaymentButtonActionPerformed(evt);
            }
        });

        supplierPaymentButton.setBackground(new java.awt.Color(173, 203, 204));
        supplierPaymentButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        supplierPaymentButton.setForeground(new java.awt.Color(47, 63, 61));
        supplierPaymentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/supplier-payment-removebg.png"))); // NOI18N
        supplierPaymentButton.setText("Supplier Payment Status");
        supplierPaymentButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        supplierPaymentButton.setIconTextGap(10);
        supplierPaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierPaymentButtonActionPerformed(evt);
            }
        });

        backToAdminButton.setBackground(new java.awt.Color(216, 219, 226));
        backToAdminButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        backToAdminButton.setForeground(new java.awt.Color(0, 0, 0));
        backToAdminButton.setText("Back to Admin Page");
        backToAdminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToAdminButtonActionPerformed(evt);
            }
        });

        MainMenuFM_greet1.setFont(new java.awt.Font("Segoe UI Historic", 0, 24)); // NOI18N
        MainMenuFM_greet1.setForeground(new java.awt.Color(55, 63, 81));
        MainMenuFM_greet1.setText("Good day,");

        MainMenuFM_username.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        MainMenuFM_username.setForeground(new java.awt.Color(88, 164, 176));
        MainMenuFM_username.setText("'Username'");

        MainMenuFM_greet2.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        MainMenuFM_greet2.setForeground(new java.awt.Color(59, 59, 59));
        MainMenuFM_greet2.setText("Welcome back to Finance Manager Dashboard");

        MainMenuFM_LO1.setBackground(new java.awt.Color(255, 75, 62));
        MainMenuFM_LO1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        MainMenuFM_LO1.setForeground(new java.awt.Color(255, 229, 72));
        MainMenuFM_LO1.setText("Log Out");
        MainMenuFM_LO1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainMenuFM_LO1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                .addComponent(MainMenuFM_greet1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MainMenuFM_username))
                            .addComponent(MainMenuFM_greet2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                .addComponent(purchaseOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(makePaymentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                .addComponent(stockStatusButton)
                                .addGap(18, 18, 18)
                                .addComponent(supplierPaymentButton))))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(backToAdminButton)))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                    .addContainerGap(684, Short.MAX_VALUE)
                    .addComponent(MainMenuFM_LO1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(16, 16, 16)))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MainMenuFM_greet1)
                    .addComponent(MainMenuFM_username))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MainMenuFM_greet2)
                .addGap(65, 65, 65)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(purchaseOrderButton)
                    .addComponent(makePaymentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stockStatusButton)
                    .addComponent(supplierPaymentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(59, 59, 59)
                .addComponent(backToAdminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                    .addContainerGap(541, Short.MAX_VALUE)
                    .addComponent(MainMenuFM_LO1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(16, 16, 16)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void purchaseOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseOrderButtonActionPerformed
        if (user instanceof Admin) {
            Admin admin = (Admin) user;
            new ViewPurchaseOrderFM(admin.getFinanceManager()).setVisible(true);
        } else {
            FinanceManager financeManager = (FinanceManager) user;
            new ViewPurchaseOrderFM(financeManager).setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_purchaseOrderButtonActionPerformed

    private void stockStatusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockStatusButtonActionPerformed
        if (user instanceof Admin) {
            Admin admin = (Admin) user;
            new StockStatusFM(admin.getFinanceManager()).setVisible(true);
        } else {
            FinanceManager financeManager = (FinanceManager) user;
            new StockStatusFM(financeManager).setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_stockStatusButtonActionPerformed

    private void makePaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makePaymentButtonActionPerformed
        if (user instanceof Admin) {
            Admin admin = (Admin) user;
            new MakePaymentFM(admin.getFinanceManager()).setVisible(true);
        } else {
            FinanceManager financeManager = (FinanceManager) user;
            new MakePaymentFM(financeManager).setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_makePaymentButtonActionPerformed

    private void supplierPaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierPaymentButtonActionPerformed
        if (user instanceof Admin) {
            Admin admin = (Admin) user;
            new SupplierPaymentFM(admin.getFinanceManager()).setVisible(true);
        } else {
            FinanceManager financeManager = (FinanceManager) user;
            new SupplierPaymentFM(financeManager).setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_supplierPaymentButtonActionPerformed

    private void backToAdminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToAdminButtonActionPerformed
        new AdminHomeUI(admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backToAdminButtonActionPerformed

    private void MainMenuFM_LO1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MainMenuFM_LO1ActionPerformed
        new LoginUI().setVisible(true);
        this.dispose();
        userState.setLoggedInAdmin(null);
    }//GEN-LAST:event_MainMenuFM_LO1ActionPerformed

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
            java.util.logging.Logger.getLogger(MainMenuFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenuFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenuFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenuFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton MainMenuFM_LO1;
    private javax.swing.JLabel MainMenuFM_greet1;
    private javax.swing.JLabel MainMenuFM_greet2;
    private javax.swing.JLabel MainMenuFM_username;
    private javax.swing.JButton backToAdminButton;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton makePaymentButton;
    private java.awt.Panel panel1;
    private javax.swing.JButton purchaseOrderButton;
    private javax.swing.JButton stockStatusButton;
    private javax.swing.JButton supplierPaymentButton;
    // End of variables declaration//GEN-END:variables
}
