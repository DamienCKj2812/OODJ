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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import models.Admin;
import models.PurchaseManager;
import models.PurchaseOrder;
import models.PurchaseOrderAction;
import models.Requisition;
import models.RequisitionAction;
import models.User;
import models.Item;
import models.Supplier;
import state.UserSession;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date; 
import java.text.SimpleDateFormat;
import java.text.ParseException;


/**
 *
 * @author zhuow
 */
public class PurchaseOrderPO extends javax.swing.JFrame {

    /**
     * Creates new form PurchaseManagerDashBoard
     */
    private PurchaseManager purchaseManager;
    private User user;
    private List<PurchaseOrder> purchaseOrders;
    private List<PurchaseManagerData> PurchaseManagerInfo = new ArrayList<>();
    private List<RequisitionData> RequisitionInfo = new ArrayList<>();
    private List<SupplierData> SupplierInfo = new ArrayList<>();
    private List<PurchaseOrderData> PurchaseOrderInfo = new ArrayList<>();
    private List<ItemData> inventoryItems = new ArrayList<>();
    UserSession userState = UserSession.getInstance();
    Admin admin = userState.getLoggedInAdmin();
    
    public PurchaseOrderPO(User user) {
        this.purchaseOrders = new ArrayList<>();
        this.user = user;
        initComponents();
        loadRequisitionData();
        loadSupplierData();
        loadPurchaseManagerData();
        loadPurchaseOrderData();
        loadItemData();
        populatePurchaseOrderStatusComboBox();
        setupQuantitySpinner();
        populateComboBoxes();
        processOrderDates();

        if (admin != null) {
            adminpageButton.setVisible(true);
        } else {
            adminpageButton.setVisible(false);
        }
    }
    
    private void loadPurchaseManagerData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/UserData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split("\\|");
                if (userDetails.length >= 1) {
                    String purchaseManagerId = userDetails[0];
                    PurchaseManagerInfo.add(new PurchaseManagerData(purchaseManagerId));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading purchase manager data: " + e.getMessage());
        }
    }

    public class PurchaseManagerData {
        private String purchaseManagerId;
        public PurchaseManagerData(String purchaseManagerId) {
            this.purchaseManagerId = purchaseManagerId;
        }

        public String getPurchaseManagerId() {
            return purchaseManagerId;
        }

        public void setPurchaseManagerId(String purchaseManagerId) {
            this.purchaseManagerId = purchaseManagerId;
        }
    }
    
    private void loadRequisitionData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/RequisitionData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] RDetails = line.split("\\|");
                if (RDetails.length >= 4) {
                    String requisitionId = RDetails[0];
                    String itemId = RDetails[1];
                    String quantity = RDetails[2];
                    String status = RDetails[3];
                    RequisitionInfo.add(new RequisitionData(requisitionId, itemId, quantity, status));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading requisition data: " + e.getMessage());
        }
    }
    
    private class RequisitionData {
        private String requisitionId;
        private String itemId;
        private String quantity;
        private String status;
        private String unitPrice;

        public RequisitionData(String requisitionId, String itemId, String quantity, String status) {
            this.requisitionId = requisitionId;
            this.itemId = itemId;
            this.quantity = quantity;
            this.status = status;
        }

        public String getRequisitionId() {
            return requisitionId;
        }

        public void setRequisitionId(String requisitionId) {
            this.requisitionId = requisitionId;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        
    }
    
    private void loadSupplierData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/SupplierData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] SDetails = line.split("\\|");
                if (SDetails.length >= 1) {
                    String supplierId = SDetails[0];
                    SupplierInfo.add(new SupplierData(supplierId));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading supplier data: " + e.getMessage());
        }
    }
    
    public class SupplierData {
        private String supplierId;

        public SupplierData(String supplierId) {
            this.supplierId = supplierId;
        }

        public String getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }
    }
    
    private void loadPurchaseOrderData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/PurchaseOrderData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] orderDetails = line.split("\\|");
                if (orderDetails.length >= 5) { 
                    String purchaseOrderId = orderDetails[0];
                    String requisitionId = orderDetails[1];
                    String orderDate = orderDetails[2];
                    String expectedDeliveryDate = orderDetails[3];
                    String Status = orderDetails[4];
                    PurchaseOrderInfo.add(new PurchaseOrderData(purchaseOrderId, requisitionId, orderDate, expectedDeliveryDate, Status));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading purchase order data: " + e.getMessage());
        }
    }

    // Inner class for PurchaseOrderData
    public class PurchaseOrderData {
        private String purchaseOrderId;
        private String requisitionId;
        private ItemData itemData;  // Reference to ItemData
        private SupplierData supplierData;
        private PurchaseManagerData purchaseManagerData;
        private String orderDate;
        private String expectedDeliveryDate;       
        private String Status;

        public PurchaseOrderData(String purchaseOrderId, String requisitionId, String orderDate, String expectedDeliveryDate,  String Status) {
            this.purchaseOrderId = purchaseOrderId;
            this.requisitionId = requisitionId;
            this.orderDate = orderDate;
            this.expectedDeliveryDate = expectedDeliveryDate;
            this.Status = Status;
        }

        public String getPurchaseOrderId() {
            return purchaseOrderId;
        }

        public void setPurchaseOrderId(String purchaseOrderId) {
            this.purchaseOrderId = purchaseOrderId;
        }

        public String getRequisitionId() {
            return requisitionId;
        }

        public void setRequisitionId(String requisitionId) {
            this.requisitionId = requisitionId;
        }
        
        public ItemData getItemId() {
            return itemData;
        }

        public SupplierData getSupplierId() {
            return supplierData;
        }

        public PurchaseManagerData getPurchaseManagerId() {
            return purchaseManagerData;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }
        
        public String getExpectedDeliveryDate() {
        return expectedDeliveryDate;
        }

        public void setExpectedDeliveryDate(String expectedDeliveryDate) {
            this.expectedDeliveryDate = expectedDeliveryDate;
        }
        
        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }
        
    }
    
    private void loadItemData() {
    try (BufferedReader reader = new BufferedReader(new FileReader("data/ItemData.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] itemDetails = line.split("\\|");
            if (itemDetails.length >= 2) {
                String itemId = itemDetails[0];
                double unitPrice = Double.parseDouble(itemDetails[1]);
                inventoryItems.add(new ItemData(itemId, unitPrice));
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error loading item data: " + e.getMessage());
    }
    }
    
    // ItemData.java
    public class ItemData {
        private String itemId;
        private double unitPrice;

        public ItemData(String itemId, double unitPrice) {
            this.itemId = itemId;
            this.unitPrice = unitPrice;
        }

        public String getItemId() {
            return itemId;
        }

        public double getUnitPrice() {
            return unitPrice;
        }
    }

    private void populatePurchaseOrderStatusComboBox() {
    StatusComboBox.addItem("Please choose a status");
    StatusComboBox.addItem("Pending");
    StatusComboBox.addItem("Approved");
    StatusComboBox.addItem("Rejected");

    StatusComboBox.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String selectedStatus = (String) StatusComboBox.getSelectedItem();
            displaySelectedPurchaseOrderDetails(selectedStatus);
        }
    });
    }
    
    private void displaySelectedPurchaseOrderDetails(String orderStatus) {
    for (PurchaseOrderData order : PurchaseOrderInfo) {
        if (order.getStatus().equals(orderStatus)) {
            for (RequisitionData requisition : RequisitionInfo) {
                if (requisition.getRequisitionId().equals(order.getRequisitionId())) {
                    RequisitionidComboBox.setSelectedItem(requisition.getRequisitionId());
                    StatusComboBox.setSelectedItem(order.getStatus());
                    quantitySpinner.setValue(0);
                    setMaxQuantityInSpinner(Integer.parseInt(requisition.getQuantity()));
                    updateTotalCost();
                    break;
                }
            }
            break;
        }
    }
    }
    
    private void setupQuantitySpinner() {
        quantitySpinner.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));

        quantitySpinner.addChangeListener(e -> updateTotalCost());
    }

    private void setMaxQuantityInSpinner(int maxQuantity) {
        quantitySpinner.setModel(new SpinnerNumberModel(0, 0, maxQuantity, 1));
    }
    
    private void updateTotalCost() {
    try {
        int quantityOrdered = (int) quantitySpinner.getValue();

        String selectedItemCode = (String) itemcodeComboBox.getSelectedItem();

        ItemData selectedItem = null;
        for (ItemData item : inventoryItems) {
            if (item.getItemId().equals(selectedItemCode)) {
                selectedItem = item;
                break;
            }
        }

        double unitPrice = (selectedItem != null) ? selectedItem.getUnitPrice() : 0.0;

        double totalAmount = quantityOrdered * unitPrice;

        TotalCostTextfield.setText(String.format("%.2f", totalAmount));
    } catch (Exception e) {
        TotalCostTextfield.setText("0.00");
    }
    }
    
    private void populateComboBoxes() {
        for (ItemData item : inventoryItems) {
            itemcodeComboBox.addItem(item.getItemId());
        }

        for (SupplierData supplier : SupplierInfo) {
            supplieridComboBox.addItem(supplier.getSupplierId());
        }

        for (PurchaseManagerData manager : PurchaseManagerInfo) {
            pmidComboBox.addItem(manager.getPurchaseManagerId());
        }
        
        for (PurchaseOrderData po : PurchaseOrderInfo) {
            poidComboBox.addItem(po.getPurchaseOrderId());
        }
    }
    
    private void processOrderDates() {
        String orderDate = orderDateTextfield.getText();
        String expectedDeliveryDate = eddTextfield.getText();

        if (!isValidDate(orderDate) || !isValidDate(expectedDeliveryDate)) {
            JOptionPane.showMessageDialog(this, "Please enter dates in the correct format: dd/MM/yyyy.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Order Date: " + orderDate + "\nExpected Delivery Date: " + expectedDeliveryDate);
        JOptionPane.showMessageDialog(this, "Dates saved successfully!");
    }

    private boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
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
        POlabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        welcomelabel = new javax.swing.JLabel();
        adminpageButton = new javax.swing.JButton();
        RequisitionIDlabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        itemcodelabel = new javax.swing.JLabel();
        quantitylabel = new javax.swing.JLabel();
        supplierlabel = new javax.swing.JLabel();
        statuslabel = new javax.swing.JLabel();
        TotalCostlabel = new javax.swing.JLabel();
        RequisitionidComboBox = new javax.swing.JComboBox<>();
        quantitySpinner = new javax.swing.JSpinner();
        supplieridComboBox = new javax.swing.JComboBox<>();
        StatusComboBox = new javax.swing.JComboBox<>();
        TotalCostTextfield = new javax.swing.JTextField();
        itemcodeComboBox = new javax.swing.JComboBox<>();
        addpo = new javax.swing.JButton();
        savepobutton = new javax.swing.JButton();
        editpoButton = new javax.swing.JButton();
        deletepoButton = new javax.swing.JButton();
        backtodashboardButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        statuslabel1 = new javax.swing.JLabel();
        statuslabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        orderDateTextfield = new javax.swing.JTextField();
        eddTextfield = new javax.swing.JTextField();
        pmidComboBox = new javax.swing.JComboBox<>();
        poidComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));

        POlabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        POlabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/purchase order.png"))); // NOI18N
        POlabel.setText("Purchase Order");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
                .addComponent(adminpageButton)
                .addGap(42, 42, 42))
        );

        RequisitionIDlabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        RequisitionIDlabel.setText("Requisition ID");

        itemcodelabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        itemcodelabel.setText("Item Code");

        quantitylabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        quantitylabel.setText("Quantity");

        supplierlabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        supplierlabel.setText("Supplier ID");

        statuslabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        statuslabel.setText("Status");

        TotalCostlabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TotalCostlabel.setText("Total Cost");

        RequisitionidComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RequisitionidComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        quantitySpinner.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        supplieridComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        supplieridComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        StatusComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        StatusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        TotalCostTextfield.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TotalCostTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalCostTextfieldActionPerformed(evt);
            }
        });

        itemcodeComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        itemcodeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        addpo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addpo.setText("Add Purchase Order");
        addpo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addpoActionPerformed(evt);
            }
        });

        savepobutton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        savepobutton.setText("Save");
        savepobutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savepobuttonActionPerformed(evt);
            }
        });

        editpoButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        editpoButton.setText("Edit");
        editpoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editpoButtonActionPerformed(evt);
            }
        });

        deletepoButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deletepoButton.setText("Delete");
        deletepoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletepoButtonActionPerformed(evt);
            }
        });

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

        statuslabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        statuslabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        statuslabel2.setText("Purchase Manager ID");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Purchase Order ID");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Order Date");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Expected Delivery Date");

        pmidComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pmidComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        poidComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        poidComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RequisitionidComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemcodelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RequisitionIDlabel)
                    .addComponent(itemcodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantitylabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(eddTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(supplierlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(StatusComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(supplieridComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(statuslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(statuslabel2)
                                    .addComponent(pmidComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(statuslabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(TotalCostTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TotalCostlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(orderDateTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(poidComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(savepobutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editpoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deletepoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(backtodashboardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(87, 87, 87))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(supplierlabel)
                                    .addComponent(RequisitionIDlabel)
                                    .addComponent(TotalCostlabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(supplieridComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(RequisitionidComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TotalCostTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(addpo)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(savepobutton)
                                        .addGap(27, 27, 27))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(itemcodelabel)
                                            .addComponent(statuslabel2)
                                            .addComponent(jLabel1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(editpoButton)
                                    .addComponent(poidComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(itemcodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pmidComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statuslabel1)
                                .addGap(83, 83, 83)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(deletepoButton)
                                .addGap(21, 21, 21)
                                .addComponent(backtodashboardButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(quantitylabel)
                                    .addComponent(statuslabel)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(StatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(orderDateTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(logoutButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(38, 38, 38))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eddTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1019, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TotalCostTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalCostTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalCostTextfieldActionPerformed

    private void adminpageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminpageButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminpageButtonActionPerformed

    private void addpoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addpoActionPerformed
    String itemCode = (String) itemcodeComboBox.getSelectedItem();
    String supplierId = (String) supplieridComboBox.getSelectedItem();
    String purchaseManagerId = (String) pmidComboBox.getSelectedItem();
    String requisitionId = (String) RequisitionidComboBox.getSelectedItem();
    String orderDate = orderDateTextfield.getText();
    String expectedDeliveryDate = eddTextfield.getText();
    String totalCost = TotalCostTextfield.getText();
    String status = (String) StatusComboBox.getSelectedItem();
    String quantity = quantitySpinner.getValue().toString();
    if (itemCode.isEmpty() || supplierId.isEmpty() || purchaseManagerId.isEmpty() || requisitionId.isEmpty() || 
        orderDate.isEmpty() || expectedDeliveryDate.isEmpty() || totalCost.isEmpty() || status.isEmpty() || quantity.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill in all the required fields.");
        return;
    }

    if (!isValidDate(orderDate) || !isValidDate(expectedDeliveryDate)) {
        JOptionPane.showMessageDialog(this, "Please enter valid dates in the correct format: dd/MM/yyyy.");
        return;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date parsedOrderDate = null;
    Date parsedEDD = null;
    try {
        parsedOrderDate = sdf.parse(orderDate);
        parsedEDD = sdf.parse(expectedDeliveryDate);
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(this, "Error parsing dates. Please use dd/MM/yyyy format.");
        return;
    }

    PurchaseOrderData poData = new PurchaseOrderData(generatePurchaseOrderId(), requisitionId, orderDate, expectedDeliveryDate, status);
    PurchaseOrderInfo.add(poData);
    savePurchaseOrderDataToFile();
    populateComboBoxes();

    JOptionPane.showMessageDialog(this, "Purchase Order Added Successfully!");
    }//GEN-LAST:event_addpoActionPerformed

    private void savepobuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savepobuttonActionPerformed
        savePurchaseOrderDataToFile();
        JOptionPane.showMessageDialog(this, "Purchase Orders Saved Successfully!");
    }//GEN-LAST:event_savepobuttonActionPerformed

    private void editpoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editpoButtonActionPerformed
        String selectedPurchaseOrderId = (String) poidComboBox.getSelectedItem();
        PurchaseOrderData selectedPoData = getPurchaseOrderById(selectedPurchaseOrderId);

        if (selectedPoData == null) {
            JOptionPane.showMessageDialog(this, "Purchase Order not found.");
            return;
        }

        // Populate fields with the selected PurchaseOrderData
        itemcodeComboBox.setSelectedItem(selectedPoData.getItemId());
        supplieridComboBox.setSelectedItem(selectedPoData.getSupplierId());
        pmidComboBox.setSelectedItem(selectedPoData.getPurchaseManagerId());
        RequisitionidComboBox.setSelectedItem(selectedPoData.getRequisitionId());
        orderDateTextfield.setText(selectedPoData.getOrderDate());
        eddTextfield.setText(selectedPoData.getExpectedDeliveryDate());
        StatusComboBox.setSelectedItem(selectedPoData.getStatus());
    }//GEN-LAST:event_editpoButtonActionPerformed

    private void deletepoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletepoButtonActionPerformed
        String selectedPurchaseOrderId = (String) poidComboBox.getSelectedItem();
        PurchaseOrderData selectedPoData = getPurchaseOrderById(selectedPurchaseOrderId);
        if (selectedPoData == null) {
            JOptionPane.showMessageDialog(this, "Purchase Order not found.");
            return;
        }

        PurchaseOrderInfo.remove(selectedPoData);
        savePurchaseOrderDataToFile();
        populateComboBoxes();
        JOptionPane.showMessageDialog(this, "Purchase Order Deleted Successfully!");
    }//GEN-LAST:event_deletepoButtonActionPerformed

    private void backtodashboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backtodashboardButtonActionPerformed
        new PurchaseManagerDashBoard(purchaseManager).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backtodashboardButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        new LoginUI().setVisible(true);
        this.dispose();
        userState.setLoggedInAdmin(null);
    }//GEN-LAST:event_logoutButtonActionPerformed

    private PurchaseOrderData getPurchaseOrderById(String purchaseOrderId) {
    for (PurchaseOrderData poData : PurchaseOrderInfo) {
        if (poData.getPurchaseOrderId().equals(purchaseOrderId)) {
            return poData;
        }
    }
    return null;
    }
    
    private void savePurchaseOrderDataToFile() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/PurchaseOrderData.txt"))) {
        for (PurchaseOrderData poData : PurchaseOrderInfo) {
            writer.write(poData.getPurchaseOrderId() + "|" + poData.getRequisitionId() + "|" + poData.getOrderDate() + 
                        "|" + poData.getExpectedDeliveryDate() + "|" + poData.getStatus());
            writer.newLine();
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error saving purchase order data: " + e.getMessage());
    }
    }

    private String generatePurchaseOrderId() {
        return "PO" + (PurchaseOrderInfo.size() + 1);
    }
    
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
            java.util.logging.Logger.getLogger(PurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel POlabel;
    private javax.swing.JLabel RequisitionIDlabel;
    private javax.swing.JComboBox<String> RequisitionidComboBox;
    private javax.swing.JComboBox<String> StatusComboBox;
    private javax.swing.JTextField TotalCostTextfield;
    private javax.swing.JLabel TotalCostlabel;
    private javax.swing.JButton addpo;
    private javax.swing.JButton adminpageButton;
    private javax.swing.JButton backtodashboardButton;
    private javax.swing.JButton deletepoButton;
    private javax.swing.JTextField eddTextfield;
    private javax.swing.JButton editpoButton;
    private javax.swing.JComboBox<String> itemcodeComboBox;
    private javax.swing.JLabel itemcodelabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTextField orderDateTextfield;
    private javax.swing.JComboBox<String> pmidComboBox;
    private javax.swing.JComboBox<String> poidComboBox;
    private javax.swing.JSpinner quantitySpinner;
    private javax.swing.JLabel quantitylabel;
    private javax.swing.JButton savepobutton;
    private javax.swing.JLabel statuslabel;
    private javax.swing.JLabel statuslabel1;
    private javax.swing.JLabel statuslabel2;
    private javax.swing.JComboBox<String> supplieridComboBox;
    private javax.swing.JLabel supplierlabel;
    private javax.swing.JLabel welcomelabel;
    // End of variables declaration//GEN-END:variables
}
