/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.FinanceManager;

/**
 *
 * @author Dev
 */
public class StockStatusFM extends javax.swing.JFrame {

    /**
     * Creates new form StockStatusFM
     */
    public StockStatusFM() {
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

        jPanel1 = new javax.swing.JPanel();
        StockStatusFM_ttl = new javax.swing.JLabel();
        StockStatusFM_searchfld = new javax.swing.JTextField();
        StockStatusFM_searchttl = new javax.swing.JLabel();
        StockStatusFM_searchbtn = new javax.swing.JButton();
        StockStatusFM_posp = new javax.swing.JScrollPane();
        StockStatusFM_potb = new javax.swing.JTable();
        StockStatusFM_filterttl = new javax.swing.JLabel();
        StockStatusFM_filtercbx = new javax.swing.JComboBox<>();
        StockStatusFM_refreshbtn = new javax.swing.JButton();
        StockStatusFM_close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(163, 176, 204));

        StockStatusFM_ttl.setFont(new java.awt.Font("Arial Black", 2, 24)); // NOI18N
        StockStatusFM_ttl.setForeground(new java.awt.Color(3, 25, 38));
        StockStatusFM_ttl.setText("Stock Status");

        StockStatusFM_searchfld.setBackground(new java.awt.Color(61, 165, 217));
        StockStatusFM_searchfld.setForeground(new java.awt.Color(28, 35, 33));
        StockStatusFM_searchfld.setToolTipText("");
        StockStatusFM_searchfld.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        StockStatusFM_searchfld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StockStatusFM_searchfldActionPerformed(evt);
            }
        });

        StockStatusFM_searchttl.setBackground(new java.awt.Color(55, 63, 81));
        StockStatusFM_searchttl.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        StockStatusFM_searchttl.setForeground(new java.awt.Color(3, 25, 38));
        StockStatusFM_searchttl.setText("Enter your item name here");

        StockStatusFM_searchbtn.setBackground(new java.awt.Color(94, 101, 114));
        StockStatusFM_searchbtn.setForeground(new java.awt.Color(238, 241, 239));
        StockStatusFM_searchbtn.setText("Search");
        StockStatusFM_searchbtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        StockStatusFM_searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StockStatusFM_searchbtnActionPerformed(evt);
            }
        });

        StockStatusFM_potb.setModel(new javax.swing.table.DefaultTableModel(
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
                "Item Code", "Item Name", "Supplier Name", "Quantity", "Reorder Level", "Stock Status"
            }
        ));
        StockStatusFM_posp.setViewportView(StockStatusFM_potb);

        StockStatusFM_filterttl.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        StockStatusFM_filterttl.setForeground(new java.awt.Color(55, 63, 81));
        StockStatusFM_filterttl.setText("Filter by Stock Status");

        StockStatusFM_filtercbx.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        StockStatusFM_filtercbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "In Stock", "Low Stock", "No Stock" }));
        StockStatusFM_filtercbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StockStatusFM_filtercbxActionPerformed(evt);
            }
        });

        StockStatusFM_refreshbtn.setBackground(new java.awt.Color(35, 100, 170));
        StockStatusFM_refreshbtn.setForeground(new java.awt.Color(238, 241, 239));
        StockStatusFM_refreshbtn.setText("Refresh List");
        StockStatusFM_refreshbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        StockStatusFM_refreshbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StockStatusFM_refreshbtnActionPerformed(evt);
            }
        });

        StockStatusFM_close.setBackground(new java.awt.Color(255, 75, 62));
        StockStatusFM_close.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        StockStatusFM_close.setForeground(new java.awt.Color(255, 229, 72));
        StockStatusFM_close.setText("Close");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(StockStatusFM_posp, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(StockStatusFM_filterttl)
                            .addComponent(StockStatusFM_refreshbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StockStatusFM_filtercbx, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(StockStatusFM_ttl)
                    .addComponent(StockStatusFM_searchttl)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(StockStatusFM_searchfld, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(StockStatusFM_searchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(StockStatusFM_close, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(StockStatusFM_ttl)
                .addGap(18, 18, 18)
                .addComponent(StockStatusFM_searchttl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StockStatusFM_searchfld, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StockStatusFM_searchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StockStatusFM_posp, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(StockStatusFM_filterttl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StockStatusFM_filtercbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StockStatusFM_refreshbtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(StockStatusFM_close, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StockStatusFM_searchfldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StockStatusFM_searchfldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StockStatusFM_searchfldActionPerformed

    private void StockStatusFM_searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StockStatusFM_searchbtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StockStatusFM_searchbtnActionPerformed

    private void StockStatusFM_filtercbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StockStatusFM_filtercbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StockStatusFM_filtercbxActionPerformed

    private void StockStatusFM_refreshbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StockStatusFM_refreshbtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StockStatusFM_refreshbtnActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StockStatusFM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton StockStatusFM_close;
    private javax.swing.JComboBox<String> StockStatusFM_filtercbx;
    private javax.swing.JLabel StockStatusFM_filterttl;
    private javax.swing.JScrollPane StockStatusFM_posp;
    private javax.swing.JTable StockStatusFM_potb;
    private javax.swing.JButton StockStatusFM_refreshbtn;
    private javax.swing.JButton StockStatusFM_searchbtn;
    private javax.swing.JTextField StockStatusFM_searchfld;
    private javax.swing.JLabel StockStatusFM_searchttl;
    private javax.swing.JLabel StockStatusFM_ttl;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
