/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.PurchaseManager;

import UI.Admin.AdminHomeUI;
import UI.Authentication.LoginUI;
import models.Admin;
import models.PurchaseManager;
import models.PurchaseOrder;
import models.PurchaseOrderAction;
import models.Requisition;
import models.RequisitionAction;
import models.User;
import state.UserSession;

/**
 *
 * @author zhuow
 */
public class PurchaseManagerDashBoard extends javax.swing.JFrame {
    private User user;
    private PurchaseManager purchaseManager;
    UserSession userState = UserSession.getInstance();
    Admin admin = userState.getLoggedInAdmin();
    
    public PurchaseManagerDashBoard(User user) {
        this.user = user;
        initComponents();

        if (admin != null) {
            AdminMenuButton.setVisible(true);
        } else {
            AdminMenuButton.setVisible(false);
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

        jPanel1 = new javax.swing.JPanel();
        PMDashboard = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        viewlistofitemsbutton = new javax.swing.JButton();
        viewlistofsupplierbutton = new javax.swing.JButton();
        requisitionbutton = new javax.swing.JButton();
        POButton = new javax.swing.JButton();
        viewlistPOButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        welcomelabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        AdminMenuButton = new javax.swing.JButton();
        logoutbutton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        PMDashboard.setBackground(new java.awt.Color(0, 153, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Purchase Manager Dashboard");

        javax.swing.GroupLayout PMDashboardLayout = new javax.swing.GroupLayout(PMDashboard);
        PMDashboard.setLayout(PMDashboardLayout);
        PMDashboardLayout.setHorizontalGroup(
            PMDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PMDashboardLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PMDashboardLayout.setVerticalGroup(
            PMDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PMDashboardLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        viewlistofitemsbutton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        viewlistofitemsbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/view list item.png"))); // NOI18N
        viewlistofitemsbutton.setText("View List of Items");
        viewlistofitemsbutton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        viewlistofitemsbutton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        viewlistofitemsbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewlistofitemsbuttonActionPerformed(evt);
            }
        });

        viewlistofsupplierbutton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        viewlistofsupplierbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/view list supplier.png"))); // NOI18N
        viewlistofsupplierbutton.setText("View List of Supplier");
        viewlistofsupplierbutton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        viewlistofsupplierbutton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        viewlistofsupplierbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewlistofsupplierbuttonActionPerformed(evt);
            }
        });

        requisitionbutton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        requisitionbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/purchase requisition.png"))); // NOI18N
        requisitionbutton.setText("Purchase Requisition");
        requisitionbutton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        requisitionbutton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        requisitionbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requisitionbuttonActionPerformed(evt);
            }
        });

        POButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        POButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/purchase order.png"))); // NOI18N
        POButton.setText("Purchase Order");
        POButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        POButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        POButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                POButtonActionPerformed(evt);
            }
        });

        viewlistPOButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        viewlistPOButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/view list purchase order.png"))); // NOI18N
        viewlistPOButton.setText("View List of Purchase Order");
        viewlistPOButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        viewlistPOButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        viewlistPOButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewlistPOButtonActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 255, 255));

        welcomelabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        welcomelabel.setText("WELCOME!");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        AdminMenuButton.setText("Admin Menu");
        AdminMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(AdminMenuButton)
                    .addComponent(welcomelabel)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(welcomelabel)
                .addGap(44, 44, 44)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                .addComponent(AdminMenuButton)
                .addGap(46, 46, 46))
        );

        logoutbutton.setText("Log Out");
        logoutbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(viewlistofitemsbutton, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(POButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(viewlistPOButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewlistofsupplierbutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(requisitionbutton, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(logoutbutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
            .addComponent(PMDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(PMDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(viewlistofsupplierbutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewlistofitemsbutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(requisitionbutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(POButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(viewlistPOButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(logoutbutton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(82, 82, 82))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutbuttonActionPerformed
        new LoginUI().setVisible(true);
        this.dispose();
        userState.setLoggedInAdmin(null);
    }//GEN-LAST:event_logoutbuttonActionPerformed

    private void viewlistofitemsbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewlistofitemsbuttonActionPerformed
        if (user instanceof Admin) {
            Admin admin = (Admin) user;
            new ViewListofItems(admin.getPurchaseManager()).setVisible(true);
        } else {
            PurchaseManager purchaseManager = (PurchaseManager) user;
            new ViewListofItems(purchaseManager).setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_viewlistofitemsbuttonActionPerformed

    private void viewlistofsupplierbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewlistofsupplierbuttonActionPerformed
        if (user instanceof Admin) {
            Admin admin = (Admin) user;
            new ViewListofSupplier(admin.getPurchaseManager()).setVisible(true);
        } else {
            PurchaseManager purchaseManager = (PurchaseManager) user;
            new ViewListofSupplier(purchaseManager).setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_viewlistofsupplierbuttonActionPerformed

    private void requisitionbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requisitionbuttonActionPerformed
        if (user instanceof Admin) {
            Admin admin = (Admin) user;
            new PRequisition(admin.getPurchaseManager()).setVisible(true);
        } else {
            PurchaseManager purchaseManager = (PurchaseManager) user;
            new PRequisition(purchaseManager).setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_requisitionbuttonActionPerformed

    private void POButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_POButtonActionPerformed
        if (user instanceof Admin) {
            Admin admin = (Admin) user;
            new PurchaseOrderPO(admin.getPurchaseManager()).setVisible(true);
        } else {
            PurchaseManager purchaseManager = (PurchaseManager) user;
            new PurchaseOrderPO(purchaseManager).setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_POButtonActionPerformed

    private void viewlistPOButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewlistPOButtonActionPerformed
        if (user instanceof Admin) {
            Admin admin = (Admin) user;
            new ViewListofPurchaseOrder(admin.getPurchaseManager()).setVisible(true);
        } else {
            PurchaseManager purchaseManager = (PurchaseManager) user;
            new ViewListofPurchaseOrder(purchaseManager).setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_viewlistPOButtonActionPerformed

    private void AdminMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminMenuButtonActionPerformed
        new AdminHomeUI(admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AdminMenuButtonActionPerformed

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
            java.util.logging.Logger.getLogger(PurchaseManagerDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PurchaseManagerDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PurchaseManagerDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PurchaseManagerDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
    }
//These are my friends code, he is doing sales manager for his sales entry, which is similar to my purchase manager's purchase order( Generate purchase order). I want you do it like him.
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AdminMenuButton;
    private javax.swing.JPanel PMDashboard;
    private javax.swing.JButton POButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton logoutbutton;
    private javax.swing.JButton requisitionbutton;
    private javax.swing.JButton viewlistPOButton;
    private javax.swing.JButton viewlistofitemsbutton;
    private javax.swing.JButton viewlistofsupplierbutton;
    private javax.swing.JLabel welcomelabel;
    // End of variables declaration//GEN-END:variables
}