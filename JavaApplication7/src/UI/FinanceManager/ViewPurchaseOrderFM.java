/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.FinanceManager;

/**
 *
 * @author Dev
 */
public class ViewPurchaseOrderFM extends javax.swing.JFrame {

    /**
     * Creates new form ViewPurchaseOrderFM
     */
    public ViewPurchaseOrderFM() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        PurchaseOrderFM_ttl = new javax.swing.JLabel();
        PurchaseOrderFM_searchfld = new javax.swing.JTextField();
        PurchaseOrderFM_searchbtn = new javax.swing.JButton();
        PurchaseOrderFM_searchttl = new javax.swing.JLabel();
        PurchaseOrderFM_filtercbx = new javax.swing.JComboBox<>();
        PurchaseOrderFM_filterttl = new javax.swing.JLabel();
        PurchaseOrderFM_refreshbtn = new javax.swing.JButton();
        PurchaseOrderFM_approvebtn = new javax.swing.JButton();
        PurchaseOrderFM_rejectbtn = new javax.swing.JButton();
        PurchaseOrderFM_close = new javax.swing.JButton();
        PurchaseOrderFM_status = new javax.swing.JLabel();
        PurchaseOrderFM_posp = new javax.swing.JScrollPane();
        PurchaseOrderFM_potb = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel.setBackground(new java.awt.Color(216, 219, 226));

        PurchaseOrderFM_ttl.setFont(new java.awt.Font("Arial Black", 2, 24)); // NOI18N
        PurchaseOrderFM_ttl.setForeground(new java.awt.Color(55, 63, 81));
        PurchaseOrderFM_ttl.setText("Purchase Order");
        PurchaseOrderFM_ttl.setToolTipText("");

        PurchaseOrderFM_searchfld.setBackground(new java.awt.Color(125, 152, 161));
        PurchaseOrderFM_searchfld.setForeground(new java.awt.Color(28, 35, 33));
        PurchaseOrderFM_searchfld.setToolTipText("");
        PurchaseOrderFM_searchfld.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PurchaseOrderFM_searchfld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PurchaseOrderFM_searchfldActionPerformed(evt);
            }
        });

        PurchaseOrderFM_searchbtn.setBackground(new java.awt.Color(94, 101, 114));
        PurchaseOrderFM_searchbtn.setForeground(new java.awt.Color(238, 241, 239));
        PurchaseOrderFM_searchbtn.setText("Search");
        PurchaseOrderFM_searchbtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PurchaseOrderFM_searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PurchaseOrderFM_searchbtnActionPerformed(evt);
            }
        });

        PurchaseOrderFM_searchttl.setBackground(new java.awt.Color(55, 63, 81));
        PurchaseOrderFM_searchttl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        PurchaseOrderFM_searchttl.setForeground(new java.awt.Color(55, 63, 81));
        PurchaseOrderFM_searchttl.setText("Enter your PO ID / Supplier Name / Item Code here");

        PurchaseOrderFM_filtercbx.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        PurchaseOrderFM_filtercbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Pending", "Approved", "Rejected" }));
        PurchaseOrderFM_filtercbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PurchaseOrderFM_filtercbxActionPerformed(evt);
            }
        });

        PurchaseOrderFM_filterttl.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        PurchaseOrderFM_filterttl.setForeground(new java.awt.Color(55, 63, 81));
        PurchaseOrderFM_filterttl.setText("Filter by Approval Status");

        PurchaseOrderFM_refreshbtn.setBackground(new java.awt.Color(94, 101, 114));
        PurchaseOrderFM_refreshbtn.setForeground(new java.awt.Color(238, 241, 239));
        PurchaseOrderFM_refreshbtn.setText("Refresh List");
        PurchaseOrderFM_refreshbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PurchaseOrderFM_refreshbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PurchaseOrderFM_refreshbtnActionPerformed(evt);
            }
        });

        PurchaseOrderFM_approvebtn.setBackground(new java.awt.Color(44, 218, 157));
        PurchaseOrderFM_approvebtn.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        PurchaseOrderFM_approvebtn.setForeground(new java.awt.Color(62, 137, 137));
        PurchaseOrderFM_approvebtn.setText("APPROVE");

        PurchaseOrderFM_rejectbtn.setBackground(new java.awt.Color(177, 15, 46));
        PurchaseOrderFM_rejectbtn.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        PurchaseOrderFM_rejectbtn.setForeground(new java.awt.Color(87, 0, 0));
        PurchaseOrderFM_rejectbtn.setText("REJECT");
        PurchaseOrderFM_rejectbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PurchaseOrderFM_rejectbtnActionPerformed(evt);
            }
        });

        PurchaseOrderFM_close.setBackground(new java.awt.Color(255, 75, 62));
        PurchaseOrderFM_close.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        PurchaseOrderFM_close.setForeground(new java.awt.Color(255, 229, 72));
        PurchaseOrderFM_close.setText("Close");

        PurchaseOrderFM_status.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        PurchaseOrderFM_status.setText("'Status'");

        PurchaseOrderFM_potb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "PO ID", "Supplier Name", "Item Code", "Quantity", "Order Date", "Approval Status"
            }
        ));
        PurchaseOrderFM_posp.setViewportView(PurchaseOrderFM_potb);

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PurchaseOrderFM_close, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PurchaseOrderFM_rejectbtn)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(PurchaseOrderFM_posp, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(PurchaseOrderFM_approvebtn))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PurchaseOrderFM_filterttl))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(PurchaseOrderFM_filtercbx, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(PurchaseOrderFM_refreshbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(PurchaseOrderFM_status)
                        .addGroup(jPanelLayout.createSequentialGroup()
                            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(PurchaseOrderFM_ttl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(PurchaseOrderFM_searchttl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PurchaseOrderFM_searchfld, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(PurchaseOrderFM_searchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(408, Short.MAX_VALUE)))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addContainerGap(125, Short.MAX_VALUE)
                        .addComponent(PurchaseOrderFM_posp, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(PurchaseOrderFM_filterttl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PurchaseOrderFM_filtercbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PurchaseOrderFM_refreshbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PurchaseOrderFM_approvebtn)
                        .addGap(18, 18, 18)
                        .addComponent(PurchaseOrderFM_rejectbtn)
                        .addGap(84, 84, 84)))
                .addComponent(PurchaseOrderFM_close, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PurchaseOrderFM_ttl)
                    .addGap(18, 18, 18)
                    .addComponent(PurchaseOrderFM_searchttl)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(PurchaseOrderFM_searchfld, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PurchaseOrderFM_searchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PurchaseOrderFM_status)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PurchaseOrderFM_searchfldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PurchaseOrderFM_searchfldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PurchaseOrderFM_searchfldActionPerformed

    private void PurchaseOrderFM_searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PurchaseOrderFM_searchbtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PurchaseOrderFM_searchbtnActionPerformed

    private void PurchaseOrderFM_filtercbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PurchaseOrderFM_filtercbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PurchaseOrderFM_filtercbxActionPerformed

    private void PurchaseOrderFM_refreshbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PurchaseOrderFM_refreshbtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PurchaseOrderFM_refreshbtnActionPerformed

    private void PurchaseOrderFM_rejectbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PurchaseOrderFM_rejectbtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PurchaseOrderFM_rejectbtnActionPerformed

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
            java.util.logging.Logger.getLogger(ViewPurchaseOrderFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPurchaseOrderFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPurchaseOrderFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPurchaseOrderFM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ViewPurchaseOrderFM().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton PurchaseOrderFM_approvebtn;
    private javax.swing.JButton PurchaseOrderFM_close;
    private javax.swing.JComboBox<String> PurchaseOrderFM_filtercbx;
    private javax.swing.JLabel PurchaseOrderFM_filterttl;
    private javax.swing.JScrollPane PurchaseOrderFM_posp;
    private javax.swing.JTable PurchaseOrderFM_potb;
    private javax.swing.JButton PurchaseOrderFM_refreshbtn;
    private javax.swing.JButton PurchaseOrderFM_rejectbtn;
    private javax.swing.JButton PurchaseOrderFM_searchbtn;
    private javax.swing.JTextField PurchaseOrderFM_searchfld;
    private javax.swing.JLabel PurchaseOrderFM_searchttl;
    private javax.swing.JLabel PurchaseOrderFM_status;
    private javax.swing.JLabel PurchaseOrderFM_ttl;
    private javax.swing.JPanel jPanel;
    // End of variables declaration//GEN-END:variables
}
