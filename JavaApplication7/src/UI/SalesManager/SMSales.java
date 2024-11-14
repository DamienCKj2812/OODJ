/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.SalesManager;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import models.Sales;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class SMSales extends javax.swing.JFrame {

    /**
     * Creates new form SMSales
     */
    public SMSales() {
        initComponents();
        loadSalesReport();
    }

    
   private void loadSalesReport() {
    DefaultTableModel model = (DefaultTableModel) tbSalesReport.getModel();
    model.setRowCount(0); // Clear existing rows
    
    // Enable sorting
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    tbSalesReport.setRowSorter(sorter);

    try (BufferedReader br = new BufferedReader(new FileReader("data/SalesEntryData.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] saleData = line.split("\\|");
            if (saleData.length >= 6) {
                String itemCode = saleData[0];
                int quantitySold = Integer.parseInt(saleData[1]);
                String dateSold = saleData[2];
                String notes = saleData[3];
                double unitPrice = Double.parseDouble(saleData[4]);
                double totalAmount = Double.parseDouble(saleData[5]);

                // Retrieve item name from InventoryData.txt based on itemCode
                String itemName = "";
                try (BufferedReader inventoryReader = new BufferedReader(new FileReader("data/InventoryData.txt"))) {
                    String inventoryLine;
                    while ((inventoryLine = inventoryReader.readLine()) != null) {
                        String[] inventoryData = inventoryLine.split("\\|");
                        if (inventoryData.length >= 2 && inventoryData[0].equals(itemCode)) {
                            itemName = inventoryData[1]; // Get the item name
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Add row to the table model
                model.addRow(new Object[]{itemCode, itemName, quantitySold, dateSold,notes, unitPrice, totalAmount});
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

   private void deleteSalesEntryFromFile(String itemCode) {
    List<String> updatedLines = new ArrayList<>();
    
    try (BufferedReader reader = new BufferedReader(new FileReader("data/SalesEntryData.txt"))) {
        String line;
        
        // Read all lines and skip the line that matches the itemCode
        while ((line = reader.readLine()) != null) {
            String[] salesDetail = line.split("\\|");
            if (salesDetail.length > 0 && !salesDetail[0].equals(itemCode)) {
                updatedLines.add(line);
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error reading sales entry file: " + e.getMessage());
        return;
    }
    
    // Write the updated list back to the file
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/SalesEntryData.txt"))) {
        for (String updatedLine : updatedLines) {
            writer.write(updatedLine);
            writer.newLine();
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error updating sales entry file: " + e.getMessage());
    }
}

   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        lblListOfItem = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblDashboard = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblListOfItems = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblSalesEntry = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblSalesReport = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        lblStockLevel = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        lblRequisition = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        lblRequisition3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSalesReport = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 153, 0));

        lblListOfItem.setFont(new java.awt.Font("Arial Black", 1, 30)); // NOI18N
        lblListOfItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSM/report-1321938.png"))); // NOI18N
        lblListOfItem.setText("Sales Report");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblListOfItem, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(665, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblListOfItem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setPreferredSize(new java.awt.Dimension(133, 45));

        lblDashboard.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSM/layout-1.png"))); // NOI18N
        lblDashboard.setText("Dashboard");
        lblDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDashboardMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setPreferredSize(new java.awt.Dimension(147, 45));

        lblListOfItems.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblListOfItems.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSM/to-do-list (1).png"))); // NOI18N
        lblListOfItems.setText("List of Items");
        lblListOfItems.setAutoscrolls(true);
        lblListOfItems.setFocusCycleRoot(true);
        lblListOfItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblListOfItemsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblListOfItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblListOfItems, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        jPanel8.setAutoscrolls(true);
        jPanel8.setInheritsPopupMenu(true);
        jPanel8.setPreferredSize(new java.awt.Dimension(139, 45));

        lblSalesEntry.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblSalesEntry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSM/bar-chart-5567326.png"))); // NOI18N
        lblSalesEntry.setText("Sales");
        lblSalesEntry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSalesEntryMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblSalesEntry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSalesEntry, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setAutoscrolls(true);
        jPanel9.setInheritsPopupMenu(true);
        jPanel9.setPreferredSize(new java.awt.Dimension(151, 45));

        lblSalesReport.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblSalesReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSM/report-1321938.png"))); // NOI18N
        lblSalesReport.setText("Sales Report");
        lblSalesReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSalesReportMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblSalesReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSalesReport, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        jPanel12.setBackground(new java.awt.Color(204, 204, 204));
        jPanel12.setAutoscrolls(true);
        jPanel12.setInheritsPopupMenu(true);
        jPanel12.setPreferredSize(new java.awt.Dimension(142, 45));

        lblStockLevel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblStockLevel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSM/in-stock (1).png"))); // NOI18N
        lblStockLevel.setText("Stock Level");
        lblStockLevel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStockLevelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblStockLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblStockLevel, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));
        jPanel13.setAutoscrolls(true);
        jPanel13.setInheritsPopupMenu(true);
        jPanel13.setPreferredSize(new java.awt.Dimension(180, 45));

        lblRequisition.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblRequisition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSM/management (1).png"))); // NOI18N
        lblRequisition.setText("Requisition");
        lblRequisition.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRequisitionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblRequisition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRequisition, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel14.setBackground(new java.awt.Color(204, 204, 204));
        jPanel14.setAutoscrolls(true);
        jPanel14.setInheritsPopupMenu(true);
        jPanel14.setPreferredSize(new java.awt.Dimension(191, 45));

        lblRequisition3.setBackground(new java.awt.Color(204, 204, 204));
        lblRequisition3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblRequisition3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSM/shopping-list (1).png"))); // NOI18N
        lblRequisition3.setText("Purchase Order");
        lblRequisition3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRequisition3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblRequisition3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRequisition3, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        tbSalesReport.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        tbSalesReport.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbSalesReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Item Code", "Item Name", "Quantity Sold", "Date Sold", "Notes", "Unit Price", "Total Amount"
            }
        ));
        jScrollPane1.setViewportView(tbSalesReport);

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(114, 114, 114)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(120, 120, 120)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDashboardMouseClicked
        SMDashboard newPage = new SMDashboard();   // Replace with the name of your target frame
        newPage.setVisible(true);

        // Optional: Hide or dispose of the current frame if you want
        SMSales.this.dispose();
    }//GEN-LAST:event_lblDashboardMouseClicked

    private void lblListOfItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblListOfItemsMouseClicked
        SMListOfItem newPage = new SMListOfItem();   // Replace with the name of your target frame
        newPage.setVisible(true);

        // Optional: Hide or dispose of the current frame if you want
        SMSales.this.dispose();
    }//GEN-LAST:event_lblListOfItemsMouseClicked

    private void lblSalesEntryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSalesEntryMouseClicked
        SMSales newPage = new SMSales();   // Replace with the name of your target frame
        newPage.setVisible(true);

        // Optional: Hide or dispose of the current frame if you want
        SMSales.this.dispose();
    }//GEN-LAST:event_lblSalesEntryMouseClicked

    private void lblStockLevelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStockLevelMouseClicked
        SMStockLevel newPage = new SMStockLevel();   // Replace with the name of your target frame
        newPage.setVisible(true);

        // Optional: Hide or dispose of the current frame if you want
        SMSales.this.dispose();
    }//GEN-LAST:event_lblStockLevelMouseClicked

    private void lblRequisitionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRequisitionMouseClicked
        SMRequisition newPage = new SMRequisition();   // Replace with the name of your target frame
        newPage.setVisible(true);

        // Optional: Hide or dispose of the current frame if you want
        SMSales.this.dispose();
    }//GEN-LAST:event_lblRequisitionMouseClicked

    private void lblRequisition3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRequisition3MouseClicked
        SMPurchaseOrder newPage = new SMPurchaseOrder();   // Replace with the name of your target frame
        newPage.setVisible(true);

        // Optional: Hide or dispose of the current frame if you want
        SMSales.this.dispose();
    }//GEN-LAST:event_lblRequisition3MouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        SMSalesEntry newPage = new SMSalesEntry();   // Replace with the name of your target frame
        newPage.setVisible(true);

        // Optional: Hide or dispose of the current frame if you want
        SMSales.this.dispose();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
       int selectedRow = tbSalesReport.getSelectedRow();
    if (selectedRow != -1) {
        try {
            // Extract row data
            String itemCode = tbSalesReport.getValueAt(selectedRow, 0).toString();  // itemCode
            String notes = tbSalesReport.getValueAt(selectedRow, 4).toString();  // notes (formerly itemName)
            int quantitySold = Integer.parseInt(tbSalesReport.getValueAt(selectedRow, 2).toString());  // quantitySold
            String dateSold = tbSalesReport.getValueAt(selectedRow, 3).toString();  // dateSold
            
            // Attempt to parse unitPrice and totalAmount with error handling
            double unitPrice = Double.parseDouble(tbSalesReport.getValueAt(selectedRow, 5).toString());
            double totalAmount = Double.parseDouble(tbSalesReport.getValueAt(selectedRow, 6).toString());

            // Create a Sales object with the extracted data, including the notes instead of itemName
//            Sales salesEntry = new Sales(itemCode, quantitySold, dateSold, notes, unitPrice);

            // Open SMSalesEntry with the Sales object and selected row index
//            SMSalesEntry editEntry = new SMSalesEntry(salesEntry, selectedRow);
//            editEntry.setVisible(true);

            // Close the current SMSales window
            SMSales.this.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: One of the numeric fields contains invalid data. Please check your table data.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select a row to edit.");
    }
    }//GEN-LAST:event_btnEditActionPerformed

    
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
         int selectedRow = tbSalesReport.getSelectedRow();
    if (selectedRow != -1) {
        // Get the item code from the selected row to identify the entry to delete
        String itemCode = tbSalesReport.getValueAt(selectedRow, 0).toString();

        // Confirm deletion with the user
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this entry?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            // Call method to delete the entry from file
            deleteSalesEntryFromFile(itemCode);

            // Remove the row from the table
            ((DefaultTableModel) tbSalesReport.getModel()).removeRow(selectedRow);

            JOptionPane.showMessageDialog(this, "Sales entry deleted successfully.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select a row to delete.");
    }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void lblSalesReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSalesReportMouseClicked
      SMSalesReport newPage= new SMSalesReport();
      newPage.setVisible(true);

        SMSales.this.dispose();
      
    }//GEN-LAST:event_lblSalesReportMouseClicked

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
            java.util.logging.Logger.getLogger(SMSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SMSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SMSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SMSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SMSales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblListOfItem;
    private javax.swing.JLabel lblListOfItems;
    private javax.swing.JLabel lblRequisition;
    private javax.swing.JLabel lblRequisition3;
    private javax.swing.JLabel lblSalesEntry;
    private javax.swing.JLabel lblSalesReport;
    private javax.swing.JLabel lblStockLevel;
    private javax.swing.JTable tbSalesReport;
    // End of variables declaration//GEN-END:variables
}
