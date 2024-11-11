/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Requisition {
    private String requisitionID;
    private String itemCode;
    private String itemName;
    private int quantity;
    private int reorderLevel;
    private LocalDate requiredDate;
    private String supplierID;
    private String salesManagerID;
    private LocalDate requisitionDate;
    private String status;

    // Constructor
    public Requisition(String requisitionID, String itemCode, String itemName, int quantity, int reorderLevel, LocalDate requiredDate, String supplierID, String salesManagerID, LocalDate requisitionDate, String status) {
        this.requisitionID = requisitionID;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
        this.requiredDate = requiredDate;
        this.supplierID = supplierID;
        this.salesManagerID = salesManagerID;
        this.requisitionDate = requisitionDate;
        this.status = status;
    }
    

    // Helper method to parse date from String to LocalDate
    private LocalDate parseDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format, using default date.");
            return LocalDate.now(); // Return current date if invalid format
        }
    }

    // Getters
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

    public int getReorderLevel() {
        return reorderLevel;
    }

    public LocalDate getRequiredDate() {
        return requiredDate;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public String getSalesManagerID() {
        return salesManagerID;
    }

    public LocalDate getRequisitionDate() {
        return requisitionDate;
    }

    public String getStatus() {
        return status;
    }

    // Setters with validation
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
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.quantity = quantity;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public void setRequiredDate(String requiredDate) {
        this.requiredDate = parseDate(requiredDate);
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public void setSalesManagerID(String salesManagerID) {
        this.salesManagerID = salesManagerID;
    }

    public void setRequisitionDate(String requisitionDate) {
        this.requisitionDate = parseDate(requisitionDate);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Method to display requisition details
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RequisitionID: ").append(requisitionID)
          .append(", ItemCode: ").append(itemCode)
          .append(", ItemName: ").append(itemName)
          .append(", Quantity: ").append(quantity)
          .append(", Reorder Level: ").append(reorderLevel)
          .append(", RequiredDate: ").append(requiredDate)
          .append(", SupplierID: ").append(supplierID)
          .append(", SalesManagerID: ").append(salesManagerID)
          .append(", RequisitionDate: ").append(requisitionDate)
          .append(", Status: ").append(status);
        return sb.toString();
    }
}