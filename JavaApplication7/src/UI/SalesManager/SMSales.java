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
import models.SalesManager;
import models.Item;
import utils.StringFormatter;
import java.text.MessageFormat;
import java.awt.print.PrinterJob;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import javax.swing.table.TableColumnModel;
import state.UserSession;
import UI.Authentication.LoginUI;


/**
 *
 * @author USER
 */
public class SMSales extends javax.swing.JFrame {

     UserSession userState = UserSession.getInstance();
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

    try {
        // Provide valid arguments for SalesManager constructor
        SalesManager salesManager = new SalesManager("SM001", "salesManager1", "password123");

        // Fetch all sales entries
        List<Sales> salesEntries = salesManager.getAllSalesEntries();

        // Fetch inventory items once for efficiency
        List<Item> inventoryItems = salesManager.getInventoryItems();

        for (Sales sales : salesEntries) {
            String salesID = sales.getSalesID();       // Sales ID
            String itemCode = sales.getItemID();       // Item Code
            int quantitySold = sales.getQuantitySold(); // Quantity Sold
            String dateSold = sales.getDateSold();     // Date Sold
            String notes = sales.getNotes();           // Notes

            String formattedDate = StringFormatter.formatUnixTimestamp(dateSold);
            // Find item details in inventory
            String itemName = "Unknown Item";
            double unitPrice = 0.0;

            
    for (Item item : inventoryItems) {
        if (item.getItemID().equals(itemCode)) {
            itemName = item.getName();        // Item Name

            // Convert unit price from String to double
            try {
                unitPrice = Double.parseDouble(item.getPrice());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid price format for item: " + item.getName());
                unitPrice = 0.0; // Default value in case of error
            }
            break;
        }
    }

            // Calculate total amount
            double totalAmount = quantitySold * unitPrice;

            // Add row to the table model
            model.addRow(new Object[]{salesID, itemCode, itemName, quantitySold, formattedDate, notes, unitPrice, totalAmount});
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error loading sales data: " + e.getMessage());
        e.printStackTrace();
    }
    
    TableColumnModel columnModel = tbSalesReport.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(180); // Sales ID
    columnModel.getColumn(1).setPreferredWidth(180);  // Item Code
    columnModel.getColumn(2).setPreferredWidth(150); // Item Name
    columnModel.getColumn(3).setPreferredWidth(40);  // Quantity Sold
    columnModel.getColumn(4).setPreferredWidth(120); // Date Sold
    columnModel.getColumn(5).setPreferredWidth(100); // Notes
    columnModel.getColumn(6).setPreferredWidth(80);  // Unit Price
    columnModel.getColumn(7).setPreferredWidth(100); // Total Amount
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSalesReport = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lblSalesEntry = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        lblStockLevel = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        lblRequisition = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblRequisition1 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        lblLogOut = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        lblAdmin = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblListOfItems = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 153, 0));

        lblListOfItem.setFont(new java.awt.Font("Arial Black", 1, 30)); // NOI18N
        lblListOfItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSM/report-1321938.png"))); // NOI18N
        lblListOfItem.setText("Sales ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblListOfItem, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblListOfItem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbSalesReport.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        tbSalesReport.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbSalesReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Sales ID", "Item Code", "Item Name", "Quantity Sold", "Date Sold", "Notes", "Unit Price", "Total Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbSalesReport);

        btnAdd.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));

        jPanel7.setAutoscrolls(true);
        jPanel7.setInheritsPopupMenu(true);
        jPanel7.setPreferredSize(new java.awt.Dimension(139, 45));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });

        lblSalesEntry.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblSalesEntry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSM/bar-chart-5567326.png"))); // NOI18N
        lblSalesEntry.setText("Sales");
        lblSalesEntry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSalesEntryMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblSalesEntryMousePressed(evt);
            }
        });
        lblSalesEntry.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblSalesEntryKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblSalesEntry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSalesEntry, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
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
                .addGap(14, 14, 14)
                .addComponent(lblRequisition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRequisition, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setPreferredSize(new java.awt.Dimension(133, 45));

        lblRequisition1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblRequisition1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSM/shopping-list (1).png"))); // NOI18N
        lblRequisition1.setText("Purchase Order");
        lblRequisition1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRequisition1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRequisition1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lblRequisition1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(204, 204, 204));
        jPanel15.setAutoscrolls(true);
        jPanel15.setInheritsPopupMenu(true);
        jPanel15.setPreferredSize(new java.awt.Dimension(180, 45));

        lblLogOut.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSM/logout (1).png"))); // NOI18N
        lblLogOut.setText("Log Out");
        lblLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogOutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel16.setBackground(new java.awt.Color(204, 204, 204));
        jPanel16.setAutoscrolls(true);
        jPanel16.setInheritsPopupMenu(true);
        jPanel16.setPreferredSize(new java.awt.Dimension(180, 45));

        lblAdmin.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSM/user (1).png"))); // NOI18N
        lblAdmin.setText("Admin");
        lblAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAdminMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAdmin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(204, 204, 204));
        jPanel14.setAutoscrolls(true);
        jPanel14.setInheritsPopupMenu(true);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setPreferredSize(new java.awt.Dimension(147, 45));

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
        lblListOfItems.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblListOfItemsKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblListOfItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblListOfItems, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        SMSalesEntry newPage = new SMSalesEntry();   // Replace with the name of your target frame
        newPage.setVisible(true);

        // Optional: Hide or dispose of the current frame if you want
        SMSales.this.dispose();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        try {
        // Initialize SalesManager instance
        SalesManager salesManager = new SalesManager("SM001", "salesManager", "password"); // Replace with actual credentials if necessary

        int selectedRow = tbSalesReport.getSelectedRow();
        if (selectedRow != -1) {
            // Get the sales ID from the selected row
            String salesID = tbSalesReport.getValueAt(selectedRow, 0).toString();

            // Retrieve the sales entry using SalesManager
            Sales salesEntry = salesManager.getSalesEntry(salesID);

            // Ensure the sales entry exists
            if (salesEntry != null) {
                // Open SMSalesEntry for editing, passing the retrieved Sales object and the selected row index
                SMSalesEntry editEntry = new SMSalesEntry(salesEntry, selectedRow);
                editEntry.setVisible(true);

                // Close the current SMSales window
                SMSales.this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "The selected sales entry could not be found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "An error occurred while retrieving the sales entry. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnEditActionPerformed

    
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       try {
        // Initialize SalesManager instance
        SalesManager salesManager = new SalesManager("SM001", "salesManager", "password"); // Replace with actual credentials if necessary

        int selectedRow = tbSalesReport.getSelectedRow();
        if (selectedRow != -1) {
            // Get the sales ID from the selected row
            String salesID = tbSalesReport.getValueAt(selectedRow, 0).toString();

            // Confirm deletion with the user
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this entry?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                // Call the SalesManager function to delete the entry
                Sales deletedSales = salesManager.removeSalesEntry(salesID);

                // Check if the sales entry was successfully deleted
                if (deletedSales != null) {
                    // Remove the row from the table
                    ((DefaultTableModel) tbSalesReport.getModel()).removeRow(selectedRow);

                    JOptionPane.showMessageDialog(this, "Sales entry deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete the sales entry. Entry not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "An error occurred while deleting the sales entry. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
         // Create the header format
    MessageFormat header = new MessageFormat("Sales Report");

    // Set the page format to ensure columns fit
    PrinterJob printerJob = PrinterJob.getPrinterJob();
    PageFormat pageFormat = printerJob.defaultPage();
    pageFormat.setOrientation(PageFormat.LANDSCAPE);  // Optional: Landscape orientation

    printerJob.setPrintable(tbSalesReport.getPrintable(JTable.PrintMode.FIT_WIDTH, header, null), pageFormat);

    // Try to print the table
    try {
        printerJob.print();
    } catch (PrinterException e) {
        JOptionPane.showMessageDialog(rootPane, e);
    }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void lblSalesEntryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSalesEntryMouseClicked
        SMSales newPage = new SMSales();   // Replace with the name of your target frame
        newPage.setVisible(true);

        // Optional: Hide or dispose of the current frame if you want
        SMSales.this.dispose();
    }//GEN-LAST:event_lblSalesEntryMouseClicked

    private void lblSalesEntryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSalesEntryMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSalesEntryMousePressed

    private void lblSalesEntryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblSalesEntryKeyPressed
        // Check if the pressed key is Enter (or any other key you want)
    }//GEN-LAST:event_lblSalesEntryKeyPressed

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        // TODO add your handling code here:
        SMSalesEntry newPage = new SMSalesEntry();   // Replace with the name of your target frame
        newPage.setVisible(true);

        // Optional: Hide or dispose of the current frame if you want
       SMSales.this.dispose();
    }//GEN-LAST:event_jPanel7MouseClicked

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

    private void lblRequisition1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRequisition1MouseClicked
        SMPurchaseOrder newPage = new SMPurchaseOrder();   // Replace with the name of your target frame
        newPage.setVisible(true);

        // Optional: Hide or dispose of the current frame if you want
       SMSales.this.dispose();
    }//GEN-LAST:event_lblRequisition1MouseClicked

    private void lblLogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogOutMouseClicked
        new LoginUI().setVisible(true);
        userState.setLoggedInAdmin(null);
        this.dispose();
    }//GEN-LAST:event_lblLogOutMouseClicked

    private void lblAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAdminMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblAdminMouseClicked

    private void lblListOfItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblListOfItemsMouseClicked
        // TODO add your handling code here:
        SMListOfItem newPage = new SMListOfItem();   // Replace with the name of your target frame
        newPage.setVisible(true);

        // Optional: Hide or dispose of the current frame if you want
        SMSales.this.dispose();
    }//GEN-LAST:event_lblListOfItemsMouseClicked

    private void lblListOfItemsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblListOfItemsKeyPressed
        SMListOfItem newPage = new SMListOfItem();   // Replace with the name of your target frame
        newPage.setVisible(true);
        // Optional: Hide or dispose of the current frame if you want
       SMSales.this.dispose();
    }//GEN-LAST:event_lblListOfItemsKeyPressed

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
    private javax.swing.JButton btnPrint;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAdmin;
    private javax.swing.JLabel lblListOfItem;
    private javax.swing.JLabel lblListOfItems;
    private javax.swing.JLabel lblLogOut;
    private javax.swing.JLabel lblRequisition;
    private javax.swing.JLabel lblRequisition1;
    private javax.swing.JLabel lblSalesEntry;
    private javax.swing.JLabel lblStockLevel;
    private javax.swing.JTable tbSalesReport;
    // End of variables declaration//GEN-END:variables
}
