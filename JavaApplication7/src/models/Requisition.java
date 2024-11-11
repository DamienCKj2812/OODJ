/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date; 
public class Requisition {
     private String itemCode;
    private String itemName;
    private int quantityRequired;
    private Date requisitionDate;
    private Date requiredBy;
    private String notes;

    // Constructor
    public Requisition(String itemCode, String itemName, int quantityRequired, Date requisitionDate, Date requiredBy, String notes) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantityRequired = quantityRequired;
        this.requisitionDate = requisitionDate;
        this.requiredBy = requiredBy;
        this.notes = notes;
    }

    // Getters and Setters
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantityRequired() {
        return quantityRequired;
    }

    public void setQuantityRequired(int quantityRequired) {
        this.quantityRequired = quantityRequired;
    }

    public Date getRequisitionDate() {
        return requisitionDate;
    }

    public void setRequisitionDate(Date requisitionDate) {
        this.requisitionDate = requisitionDate;
    }

    public Date getRequiredBy() {
        return requiredBy;
    }

    public void setRequiredBy(Date requiredBy) {
        this.requiredBy = requiredBy;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Requisition{" +
               "itemCode='" + itemCode + '\'' +
               ", itemName='" + itemName + '\'' +
               ", quantityRequired=" + quantityRequired +
               ", requisitionDate=" + requisitionDate +
               ", requiredBy=" + requiredBy +
               ", notes='" + notes + '\'' +
               '}';
    }
}