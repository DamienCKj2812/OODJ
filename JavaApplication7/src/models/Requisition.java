/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class Requisition {

    private String requisitionId;
    private String itemId;
    private String quantity;
    private String requiredDate;
    private String salesManagerId;
    private String requisitionDate;

    // Constructor
    public Requisition(String requisitionId, String itemId, String quantity, String requiredDate, String salesManagerId, String requisitionDate) {
        this.requisitionId = requisitionId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.requiredDate = requiredDate;
        this.salesManagerId = salesManagerId;
        this.requisitionDate = requisitionDate;
    }

    public String getRequisitionId() {
        return requisitionId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getRequiredDate() {
        return requiredDate;
    }

    public String getSalesManagerId() {
        return salesManagerId;
    }

    public String getRequisitionDate() {
        return requisitionDate;
    }

    public void setRequisitionId(String requisitionId) {
        this.requisitionId = requisitionId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setRequiredDate(String requiredDate) {
        this.requiredDate = requiredDate;
    }

    public void setSalesManagerId(String salesManagerId) {
        this.salesManagerId = salesManagerId;
    }

    public void setRequisitionDate(String requisitionDate) {
        this.requisitionDate = requisitionDate;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    // Method to display requisition details
    @Override
    public String toString() {
        return requisitionId + "|" + itemId + "|" + quantity + "|" + requiredDate + "|" + salesManagerId + "|" + requisitionDate ;
    }
}
