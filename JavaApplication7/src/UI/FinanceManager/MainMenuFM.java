/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.FinanceManager;

/**
 *
 * @author Dev
 */
public class MainMenuFM extends javax.swing.JFrame {

    /**
     * Creates new form MainMenuFM
     */
    public MainMenuFM() {
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

        jSeparator1 = new javax.swing.JSeparator();
        panel1 = new java.awt.Panel();
        MainMenuFM_CSS = new javax.swing.JButton();
        MainMenuFM_PO = new javax.swing.JButton();
        MainMenuFM_MP = new javax.swing.JButton();
        MainMenuFM_SPS = new javax.swing.JButton();
        MainMenuFM_LO = new javax.swing.JButton();
        MainMenuFM_greet1 = new javax.swing.JLabel();
        MainMenuFM_username = new javax.swing.JLabel();
        MainMenuFM_greet2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 204, 255));

        MainMenuFM_CSS.setBackground(new java.awt.Color(169, 188, 208));
        MainMenuFM_CSS.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        MainMenuFM_CSS.setForeground(new java.awt.Color(27, 27, 30));
        MainMenuFM_CSS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/stock-warehouse-removebg.png"))); // NOI18N
        MainMenuFM_CSS.setText("Stock Status");
        MainMenuFM_CSS.setActionCommand("Stock Status");
        MainMenuFM_CSS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MainMenuFM_CSS.setIconTextGap(10);
        MainMenuFM_CSS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainMenuFM_CSSActionPerformed(evt);
            }
        });

        MainMenuFM_PO.setBackground(new java.awt.Color(216, 219, 226));
        MainMenuFM_PO.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        MainMenuFM_PO.setForeground(new java.awt.Color(55, 63, 81));
        MainMenuFM_PO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/po-removebg.png"))); // NOI18N
        MainMenuFM_PO.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        MainMenuFM_PO.setIconTextGap(10);
        MainMenuFM_PO.setLabel("Purchase Order");
        MainMenuFM_PO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainMenuFM_POActionPerformed(evt);
            }
        });

        MainMenuFM_MP.setBackground(new java.awt.Color(132, 194, 204));
        MainMenuFM_MP.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        MainMenuFM_MP.setForeground(new java.awt.Color(55, 63, 81));
        MainMenuFM_MP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/payment-removebg.png"))); // NOI18N
        MainMenuFM_MP.setText("Make Payment");
        MainMenuFM_MP.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        MainMenuFM_MP.setIconTextGap(10);
        MainMenuFM_MP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainMenuFM_MPActionPerformed(evt);
            }
        });

        MainMenuFM_SPS.setBackground(new java.awt.Color(173, 203, 204));
        MainMenuFM_SPS.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        MainMenuFM_SPS.setForeground(new java.awt.Color(76, 102, 99));
        MainMenuFM_SPS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/supplier-payment-removebg.png"))); // NOI18N
        MainMenuFM_SPS.setText("Supplier Payment Status");
        MainMenuFM_SPS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MainMenuFM_SPS.setIconTextGap(10);
        MainMenuFM_SPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainMenuFM_SPSActionPerformed(evt);
            }
        });

        MainMenuFM_LO.setBackground(new java.awt.Color(255, 75, 62));
        MainMenuFM_LO.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        MainMenuFM_LO.setForeground(new java.awt.Color(255, 229, 72));
        MainMenuFM_LO.setText("Log Out");

        MainMenuFM_greet1.setFont(new java.awt.Font("Segoe UI Historic", 0, 24)); // NOI18N
        MainMenuFM_greet1.setForeground(new java.awt.Color(55, 63, 81));
        MainMenuFM_greet1.setText("Good day,");

        MainMenuFM_username.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        MainMenuFM_username.setForeground(new java.awt.Color(88, 164, 176));
        MainMenuFM_username.setText("Username");

        MainMenuFM_greet2.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        MainMenuFM_greet2.setForeground(new java.awt.Color(59, 59, 59));
        MainMenuFM_greet2.setText("Welcome back to Finance Manager Dashboard");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(MainMenuFM_LO, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                .addComponent(MainMenuFM_greet1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MainMenuFM_username))
                            .addComponent(MainMenuFM_greet2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                        .addComponent(MainMenuFM_PO, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(MainMenuFM_MP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                        .addComponent(MainMenuFM_CSS)
                        .addGap(18, 18, 18)
                        .addComponent(MainMenuFM_SPS)))
                .addContainerGap(50, Short.MAX_VALUE))
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
                    .addComponent(MainMenuFM_PO)
                    .addComponent(MainMenuFM_MP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MainMenuFM_CSS)
                    .addComponent(MainMenuFM_SPS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(70, 70, 70)
                .addComponent(MainMenuFM_LO, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MainMenuFM_POActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MainMenuFM_POActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MainMenuFM_POActionPerformed

    private void MainMenuFM_CSSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MainMenuFM_CSSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MainMenuFM_CSSActionPerformed

    private void MainMenuFM_MPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MainMenuFM_MPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MainMenuFM_MPActionPerformed

    private void MainMenuFM_SPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MainMenuFM_SPSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MainMenuFM_SPSActionPerformed

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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenuFM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton MainMenuFM_CSS;
    private javax.swing.JButton MainMenuFM_LO;
    private javax.swing.JButton MainMenuFM_MP;
    private javax.swing.JButton MainMenuFM_PO;
    private javax.swing.JButton MainMenuFM_SPS;
    private javax.swing.JLabel MainMenuFM_greet1;
    private javax.swing.JLabel MainMenuFM_greet2;
    private javax.swing.JLabel MainMenuFM_username;
    private javax.swing.JSeparator jSeparator1;
    private java.awt.Panel panel1;
    // End of variables declaration//GEN-END:variables
}
