/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.PurchaseManager;

import UI.Admin.AdminHomeUI;
import UI.Authentication.LoginUI;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import models.Admin;
import models.PurchaseManager;
import models.PurchaseOrder;
import models.PurchaseOrderAction;
import models.Requisition;
import models.RequisitionAction;
import models.User;
import state.UserSession;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.TableRowSorter;
import models.Supplier;
import models.SupplierAction;
/**
 *
 * @author zhuow
 */
public class ViewListofSupplier extends javax.swing.JFrame {

    private User user;
    private PurchaseManager purchaseManager;
    UserSession userState = UserSession.getInstance();
    Admin admin = userState.getLoggedInAdmin();
    /**
     * Creates new form PurchaseOrder
     */
    
    
    public ViewListofSupplier(User user) {
        this.user = user;
        initComponents();
        loadSupplierData();
        
        if (admin != null) {
            adminpageButton.setVisible(true);
        } else {
            adminpageButton.setVisible(false);
        }
    }
    
    private void loadSupplierData(){
        DefaultTableModel model = (DefaultTableModel) View_list_of_Supplier_Table.getModel();
        model.setRowCount(0);  // Clear any existing rows in the table

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        View_list_of_Supplier_Table.setRowSorter(sorter);

        try (BufferedReader reader = new BufferedReader(new FileReader("data/SupplierData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] SDetails = line.split("\\|");

                if (SDetails.length >= 6) {
                    String id = SDetails[0];
                    String name = SDetails[1];
                    String ContactPerson = SDetails[2];
                    String address = SDetails[3];
                    String phoneNumber = SDetails[4];
                    String productSupplied = SDetails[5];
                    // Add row to the table model
                    model.addRow(new Object[]{id, name, ContactPerson, address, phoneNumber, productSupplied});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading supplier data: " + e.getMessage());
        }
    }
    // Method to load supplier data into the table
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        View_list_of_Supplier_Table = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        POlabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        welcomelabel = new javax.swing.JLabel();
        adminpageButton = new javax.swing.JButton();
        backtodashboardButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        View_list_of_Supplier_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Supplier ID", "Supplier Name", "Contact Person", "Address", "Phone Number", "Product Supplied"
            }
        ));
        jScrollPane1.setViewportView(View_list_of_Supplier_Table);

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));

        POlabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        POlabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/view list supplier.png"))); // NOI18N
        POlabel.setText("List of Supplier");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(POlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(POlabel)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 255, 255));

        welcomelabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        welcomelabel.setText("WELCOME!");

        adminpageButton.setText("Admin Page");
        adminpageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminpageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(adminpageButton)
                    .addComponent(welcomelabel))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(welcomelabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
                .addComponent(adminpageButton)
                .addGap(42, 42, 42))
        );

        backtodashboardButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        backtodashboardButton.setText("Back to Dashboard");
        backtodashboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backtodashboardButtonActionPerformed(evt);
            }
        });

        logoutButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        logoutButton.setText("Log Out");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(backtodashboardButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101))))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(backtodashboardButton)
                            .addComponent(logoutButton)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adminpageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminpageButtonActionPerformed
        new AdminHomeUI(admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_adminpageButtonActionPerformed

    private void backtodashboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backtodashboardButtonActionPerformed
        new PurchaseManagerDashBoard(purchaseManager).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backtodashboardButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        new LoginUI().setVisible(true);
        this.dispose();
        userState.setLoggedInAdmin(null);
    }//GEN-LAST:event_logoutButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ViewListofSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewListofSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewListofSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewListofSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel POlabel;
    private javax.swing.JTable View_list_of_Supplier_Table;
    private javax.swing.JButton adminpageButton;
    private javax.swing.JButton backtodashboardButton;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JLabel welcomelabel;
    // End of variables declaration//GEN-END:variables
}
