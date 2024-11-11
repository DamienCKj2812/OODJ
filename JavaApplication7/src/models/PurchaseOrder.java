/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PurchaseOrder {
    private String poNumber;
    private String requisitionID;
    private String itemCode;
    private String itemName;
    private int quantity;
    private String supplierID;
    private LocalDate orderDate;
    private LocalDate expectedDeliveryDate;
    private String status;
    private double totalCost;
    private String purchaseManagerID;

    // Constructor
    public PurchaseOrder(String poNumber, String requisitionID, String itemCode, String itemName, String quantity,
                         String supplierID, String orderDate, String expectedDeliveryDate, String status,
                         String totalCost, String purchaseManagerID) {
        this.poNumber = poNumber;
        this.requisitionID = requisitionID;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantity = parseQuantity(quantity);
        this.supplierID = supplierID;
        this.orderDate = parseDate(orderDate);
        this.expectedDeliveryDate = parseDate(expectedDeliveryDate);
        this.status = status;
        this.totalCost = parseTotalCost(totalCost);
        this.purchaseManagerID = purchaseManagerID;
    }

    // Helper method to parse quantity
    private int parseQuantity(String quantity) {
        try {
            return Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            return 0; // Default to 0 if invalid
        }
    }

    // Helper method to parse total cost
    private double parseTotalCost(String totalCost) {
        try {
            return Double.parseDouble(totalCost);
        } catch (NumberFormatException e) {
            return 0.0; // Default to 0 if invalid
        }
    }

    // Helper method to parse dates from String to LocalDate
    private LocalDate parseDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            return LocalDate.now(); // Default to the current date if invalid
        }
    }

    // Getters
    public String getPoNumber() {
        return poNumber;
    }

    public String getRequisitionID() {
        return requisitionID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getPurchaseManagerID() {
        return purchaseManagerID;
    }
    

    // Setters
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public void setRequisitionID(String requisitionID) {
        this.requisitionID = requisitionID;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = parseDate(orderDate);
    }

    public void setExpectedDeliveryDate(String expectedDeliveryDate) {
        this.expectedDeliveryDate = parseDate(expectedDeliveryDate);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setPurchaseManagerID(String purchaseManagerID) {
        this.purchaseManagerID = purchaseManagerID;
    }

    // Method to display purchase order details
    @Override
    public String toString() {
        return "PO Number: " + poNumber + ", Requisition ID: " + requisitionID + ", Item Code: " + itemCode +
               ", Item Name: " + itemName + ", Quantity: " + quantity + ", Supplier ID: " + supplierID +
               ", Order Date: " + orderDate + ", Expected Delivery Date: " + expectedDeliveryDate + 
               ", Status: " + status + ", Total Cost: " + totalCost + ", Purchase Manager ID: " + purchaseManagerID;
    }
}