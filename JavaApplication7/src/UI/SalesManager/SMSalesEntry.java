/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.SalesManager;

import UI.Admin.AdminHomeUI;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import models.Item;
import models.Sales;
import models.SalesManager;
import models.Item;
import UI.Authentication.LoginUI;
import models.Admin;
import models.InventoryManager;
import state.UserSession;
import utils.LogHandler;

public class SMSalesEntry extends javax.swing.JFrame {

    UserSession userState = UserSession.getInstance();
    Admin admin = userState.getLoggedInAdmin();
    private List<ItemData> inventoryItems = new ArrayList<>();
    private int editingRowIndex = -1;
    private Sales salesEntry;
    private SalesManager salesManager;
    private LogHandler logHandler;

    public SMSalesEntry(Sales salesEntry, int rowIndex, SalesManager salesManager) {
        initComponents();
        this.salesEntry = salesEntry;  // Store the salesEntry object here
        loadInventoryData();
        this.salesManager = salesManager;
        this.logHandler = new LogHandler(salesManager);
        populateItemCodeComboBox();
        setupQuantitySpinner();
        cmbItemCode.setSelectedItem(salesEntry.getItemID());
        spnQuantitySold.setValue(salesEntry.getQuantitySold());
        txtaNotes.setText(salesEntry.getNotes());
        editingRowIndex = rowIndex;
        btnEdit.setEnabled(true);
        btnAddSales.setEnabled(false);

        if (admin != null) {
            lblAdmin.setVisible(true); // Make the button visible
        } else {
            lblAdmin.setVisible(false); // Hide the button for non-admin users
        }
    }

    public SMSalesEntry(SalesManager salesManager) {
        initComponents();
        this.salesManager = salesManager;
        this.logHandler = new LogHandler(salesManager);
        System.out.println("Here is SM sales entry page");
        System.out.println(salesManager.getUserID());
        System.out.println(salesManager.getUsername());
        loadInventoryData();
        populateItemCodeComboBox();
        setupQuantitySpinner();

    }

    private void loadInventoryData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/InventoryData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] itemDetails = line.split("\\|");
                if (itemDetails.length >= 7) { // Ensure all fields are present
                    String itemCode = itemDetails[0];
                    String itemName = itemDetails[1];
                    String description = itemDetails[2];
                    double unitPrice = Double.parseDouble(itemDetails[3]);
                    int quantityInStock = Integer.parseInt(itemDetails[4]);
                    int reorderLevel = Integer.parseInt(itemDetails[5]);
                    String supplierCode = itemDetails[6];

                    // Create and add the ItemData object to the inventoryItems list
                    inventoryItems.add(new ItemData(itemCode, itemName, description, unitPrice, quantityInStock, reorderLevel, supplierCode));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading inventory data: " + e.getMessage());
        }
    }

    private void populateItemCodeComboBox() {
        cmbItemCode.addItem("Please choose an item");
        for (ItemData item : inventoryItems) {
            cmbItemCode.addItem(item.getItemCode());
        }

        cmbItemCode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedCode = (String) cmbItemCode.getSelectedItem();
                displaySelectedItemDetails(selectedCode);
            }
        });
    }

    private void displaySelectedItemDetails(String itemCode) {
        for (ItemData item : inventoryItems) {
            if (item.getItemCode().equals(itemCode)) {
                // Set the item details in the text fields
                txtItemName.setText(item.getItemName());
                txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));

                // Reset quantity spinner to 0 when switching items
                spnQuantitySold.setValue(0);

                // Set the max quantity of the spinner based on the selected item
                setMaxQuantityInSpinner(item.getQuantityInStock());

                // Recalculate total amount with the updated quantity (which is 0)
                updateTotalAmount();
                break;
            }
        }
    }

    private void setupQuantitySpinner() {
        spnQuantitySold.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));

        // Add ChangeListener to update total amount whenever quantity is changed
        spnQuantitySold.addChangeListener(e -> updateTotalAmount());
    }

    private void setMaxQuantityInSpinner(int maxQuantity) {
        // Update the spinner model to have a max value of the available stock
        spnQuantitySold.setModel(new SpinnerNumberModel(0, 0, maxQuantity, 1));
    }

    private void updateTotalAmount() {
        try {
            // Get the quantity and unit price
            int quantitySold = (int) spnQuantitySold.getValue();
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());

            // Calculate total amount
            double totalAmount = quantitySold * unitPrice;

            // Display the total amount
            txtTotalAmount.setText(String.format("%.2f", totalAmount));
        } catch (NumberFormatException e) {
            // Handle potential errors, for example, if unit price is not a valid number
            txtTotalAmount.setText("0.00");
        }
    }

    private class ItemData {

        private String itemCode;
        private String itemName;
        private String description;
        private double unitPrice;
        private int quantityInStock;
        private int reorderLevel;
        private String supplierCode;

        public ItemData(String itemCode, String itemName, String description, double unitPrice, int quantityInStock, int reorderLevel, String supplierCode) {
            this.itemCode = itemCode;
            this.itemName = itemName;
            this.description = description;
            this.unitPrice = unitPrice;
            this.quantityInStock = quantityInStock;
            this.reorderLevel = reorderLevel;
            this.supplierCode = supplierCode;
        }

        public String getItemCode() {
            return itemCode;
        }

        public String getItemName() {
            return itemName;
        }

        public String getDescription() {
            return description;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public int getQuantityInStock() {
            return quantityInStock;
        }

        public int getReorderLevel() {
            return reorderLevel;
        }

        public String getSupplierCode() {
            return supplierCode;
        }
    }

    private void updateInventoryFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/InventoryData.txt"))) {
            for (ItemData item : inventoryItems) {
                // Write each item to the file in the original format
                writer.write(item.getItemCode() + "|"
                        + item.getItemName() + "|"
                        + item.getDescription() + "|"
                        + item.getUnitPrice() + "|"
                        + item.getQuantityInStock() + "|"
                        + item.getReorderLevel() + "|"
                        + item.getSupplierCode());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error updating inventory data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        jPanel3 = new javax.swing.JPanel();
        lblListOfItem = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblItemCode = new javax.swing.JLabel();
        lblItemCode1 = new javax.swing.JLabel();
        lblItemCode2 = new javax.swing.JLabel();
        lblItemCode4 = new javax.swing.JLabel();
        lblItemCode6 = new javax.swing.JLabel();
        lblItemCode5 = new javax.swing.JLabel();
        cmbItemCode = new javax.swing.JComboBox<>();
        txtItemName = new javax.swing.JTextField();
        spnQuantitySold = new javax.swing.JSpinner();
        txtUnitPrice = new javax.swing.JTextField();
        txtTotalAmount = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaNotes = new javax.swing.JTextArea();
        jPanel10 = new javax.swing.JPanel();
        btnAddSales = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
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
        lblListOfItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgSM/bar-chart-5567326.png"))); // NOI18N
        lblListOfItem.setText("Sales");

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
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(lblListOfItem))
        );

        lblItemCode.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblItemCode.setText("Item Code");

        lblItemCode1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblItemCode1.setText("Item Name");

        lblItemCode2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblItemCode2.setText("Quantity Sold");

        lblItemCode4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblItemCode4.setText("Unit Price(RM)");

        lblItemCode6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblItemCode6.setText("Total Amount");

        lblItemCode5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblItemCode5.setText("Notes");

        cmbItemCode.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cmbItemCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbItemCodeActionPerformed(evt);
            }
        });

        txtItemName.setEditable(false);
        txtItemName.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        spnQuantitySold.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtUnitPrice.setEditable(false);
        txtUnitPrice.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtTotalAmount.setEditable(false);
        txtTotalAmount.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtaNotes.setColumns(20);
        txtaNotes.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtaNotes.setRows(5);
        jScrollPane1.setViewportView(txtaNotes);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblItemCode5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblItemCode2)
                            .addComponent(spnQuantitySold, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblItemCode4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblItemCode6)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(57, 57, 57))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblItemCode)
                            .addComponent(cmbItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblItemCode1)
                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(77, 77, 77))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblItemCode)
                    .addComponent(lblItemCode1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblItemCode4)
                            .addComponent(lblItemCode6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblItemCode2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spnQuantitySold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(lblItemCode5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        btnAddSales.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnAddSales.setText("Add Sales");
        btnAddSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSalesActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.setEnabled(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddSales, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnAddSales)
                .addGap(18, 18, 18)
                .addComponent(btnEdit)
                .addGap(18, 18, 18)
                .addComponent(btnClear)
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private double getUnitPriceForItem(String itemCode) {
        for (ItemData item : inventoryItems) {
            if (item.getItemCode().equals(itemCode)) {
                return item.getUnitPrice();
            }
        }
        return 0.0;  // Default value if itemCode is not found
    }

    private void btnAddSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSalesActionPerformed
        try {
            // Get the selected item code
            String itemCode = (String) cmbItemCode.getSelectedItem();

            // Validate that the item code is not "Please choose an item"
            if ("Please choose an item".equals(itemCode)) {
                JOptionPane.showMessageDialog(this, "Please select a valid item code.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate that quantity sold is greater than 0
            int quantitySold = (Integer) spnQuantitySold.getValue();
            if (quantitySold <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity sold must be greater than 0.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Get notes
            String notes = txtaNotes.getText().trim();
            if (notes.isEmpty()) {
                notes = "-";
            }

            // Get the current date (or use a custom date)
            String dateSold = String.valueOf(System.currentTimeMillis() / 1000); // Unix timestamp in seconds

            Sales newSalesEntry = salesManager.addSalesEntry(itemCode, quantitySold, dateSold, notes);

            // Show success message
            JOptionPane.showMessageDialog(this, "Sale added successfully! Sales ID: " + newSalesEntry.getSalesID(), "Success", JOptionPane.INFORMATION_MESSAGE);

            logHandler.addLogActionToFile(String.format("Sales added successfully! (Sales ID:%S)", newSalesEntry.getSalesID()));
            // Clear fields
            cmbItemCode.setSelectedIndex(0);
            spnQuantitySold.setValue(0);
            txtaNotes.setText("");

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "An error occurred while saving the sales entry: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddSalesActionPerformed

    private void cmbItemCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbItemCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbItemCodeActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        cmbItemCode.setSelectedIndex(0);  // 0 index is the empty value
        spnQuantitySold.setValue(0);  // Optional: Reset quantity sold to 0
        txtaNotes.setText("");
        spnQuantitySold.setValue(0);  // Set the value to 0
        txtUnitPrice.setText("0");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        try {
            // Get the edited values from form fields
            String itemID = (String) cmbItemCode.getSelectedItem();
            int quantitySold = (Integer) spnQuantitySold.getValue();
            String notes = txtaNotes.getText().trim();
            if (notes.isEmpty()) {
                notes = "-";  // Set to "-" if notes are empty
            }
            Item item = salesManager.getInventoryItem(itemID);

            // Handle unit price properly
            String unitPriceText = txtUnitPrice.getText().trim();
            double unitPrice = 0;
            if (!unitPriceText.isEmpty()) {
                try {
                    unitPrice = Double.parseDouble(unitPriceText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid unit price.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Unit price cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double totalAmount = quantitySold * unitPrice;
            long unixTimestamp = System.currentTimeMillis() / 1000L;  // Get current timestamp

            // Ensure salesEntry is not null
            if (salesEntry == null) {
                JOptionPane.showMessageDialog(this, "Invalid sales entry.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update the sales entry
          
            Sales updatedSales = salesManager.updateSalesEntry(salesEntry.getSalesID(), itemID, quantitySold, String.valueOf(unixTimestamp), notes);

            if (updatedSales != null) {
                JOptionPane.showMessageDialog(this, "Sale updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                logHandler.addLogActionToFile(String.format("Sales EDITED (Sales ID:%S)", item.getItemID()));
                // Close this editing window and return to SMSales
                SMSales newPage = new SMSales(salesManager);  // Replace with the name of your target frame
                newPage.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update the sale. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating the sale: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        SMSales newPage = new SMSales(salesManager);
        newPage.setVisible(true);

        SMSalesEntry.this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void lblSalesEntryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSalesEntryMouseClicked
        SMSales newPage = new SMSales(salesManager);   // Replace with the name of your target frame
        newPage.setVisible(true);

        // Optional: Hide or dispose of the current frame if you want
        SMSalesEntry.this.dispose();
    }//GEN-LAST:event_lblSalesEntryMouseClicked

    private void lblSalesEntryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSalesEntryMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSalesEntryMousePressed

    private void lblSalesEntryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblSalesEntryKeyPressed
        // Check if the pressed key is Enter (or any other key you want)
    }//GEN-LAST:event_lblSalesEntryKeyPressed

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        // TODO add your handling code here:
        SMSalesEntry newPage = new SMSalesEntry(salesManager);   // Replace with the name of your target frame
        newPage.setVisible(true);

        // Optional: Hide or dispose of the current frame if you want
        SMSalesEntry.this.dispose();
    }//GEN-LAST:event_jPanel7MouseClicked

    private void lblStockLevelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStockLevelMouseClicked
        SMStockLevel newPage = new SMStockLevel(salesManager);   // Replace with the name of your target frame
        newPage.setVisible(true);

        // Optional: Hide or dispose of the current frame if you want
        SMSalesEntry.this.dispose();
    }//GEN-LAST:event_lblStockLevelMouseClicked

    private void lblRequisitionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRequisitionMouseClicked
        SMRequisition newPage = new SMRequisition(salesManager);   // Replace with the name of your target frame
        newPage.setVisible(true);

        // Optional: Hide or dispose of the current frame if you want
        SMSalesEntry.this.dispose();
    }//GEN-LAST:event_lblRequisitionMouseClicked

    private void lblRequisition1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRequisition1MouseClicked
        SMPurchaseOrder newPage = new SMPurchaseOrder(salesManager);   // Replace with the name of your target frame
        newPage.setVisible(true);

        // Optional: Hide or dispose of the current frame if you want
        SMSalesEntry.this.dispose();
    }//GEN-LAST:event_lblRequisition1MouseClicked

    private void lblLogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogOutMouseClicked
        new LoginUI().setVisible(true);
        userState.setLoggedInAdmin(null);
        this.dispose();
    }//GEN-LAST:event_lblLogOutMouseClicked

    private void lblAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAdminMouseClicked
        new AdminHomeUI(admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblAdminMouseClicked

    private void lblListOfItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblListOfItemsMouseClicked
        // TODO add your handling code here:
        SMListOfItem newPage = new SMListOfItem(salesManager);   // Replace with the name of your target frame
        newPage.setVisible(true);

        // Optional: Hide or dispose of the current frame if you want
        SMSalesEntry.this.dispose();
    }//GEN-LAST:event_lblListOfItemsMouseClicked

    private void lblListOfItemsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblListOfItemsKeyPressed
        SMListOfItem newPage = new SMListOfItem(salesManager);   // Replace with the name of your target frame
        newPage.setVisible(true);
        // Optional: Hide or dispose of the current frame if you want
        SMSalesEntry.this.dispose();
    }//GEN-LAST:event_lblListOfItemsKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(SMSalesEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SMSalesEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SMSalesEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SMSalesEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SMSalesEntry().setVisible(true);
//            }
//        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddSales;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnEdit;
    private javax.swing.JComboBox<String> cmbItemCode;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
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
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAdmin;
    private javax.swing.JLabel lblItemCode;
    private javax.swing.JLabel lblItemCode1;
    private javax.swing.JLabel lblItemCode2;
    private javax.swing.JLabel lblItemCode4;
    private javax.swing.JLabel lblItemCode5;
    private javax.swing.JLabel lblItemCode6;
    private javax.swing.JLabel lblListOfItem;
    private javax.swing.JLabel lblListOfItems;
    private javax.swing.JLabel lblLogOut;
    private javax.swing.JLabel lblRequisition;
    private javax.swing.JLabel lblRequisition1;
    private javax.swing.JLabel lblSalesEntry;
    private javax.swing.JLabel lblStockLevel;
    private javax.swing.JSpinner spnQuantitySold;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtTotalAmount;
    private javax.swing.JTextField txtUnitPrice;
    private javax.swing.JTextArea txtaNotes;
    // End of variables declaration//GEN-END:variables
}
