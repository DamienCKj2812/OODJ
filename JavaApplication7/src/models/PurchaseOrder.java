/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PurchaseOrder {

    private String purchaseOrderId;
    private String requisitionID;
    private String itemId;
    private String orderQuantity;
    private String orderDate;
    private String expectedDeliveryDate;
    private String status;
    private String purchaseManagerID;

    public PurchaseOrder(String purchaseOrderId, String requisitionID, String itemId, String orderQuantity, String orderDate, String expectedDeliveryDate, String status, String purchaseManagerID) {
        this.purchaseOrderId = purchaseOrderId;
        this.requisitionID = requisitionID;
        this.itemId = itemId;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.status = status;
        this.purchaseManagerID = purchaseManagerID;
    }

    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public String getRequisitionID() {
        return requisitionID;
    }

    public String getItemId() {
        return itemId;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public String getPurchaseManagerID() {
        return purchaseManagerID;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public void setRequisitionID(String requisitionID) {
        this.requisitionID = requisitionID;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setExpectedDeliveryDate(String expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPurchaseManagerID(String purchaseManagerID) {
        this.purchaseManagerID = purchaseManagerID;
    }

    @Override
    public String toString() {
        return purchaseOrderId + "|" + requisitionID + "|" + itemId + "|" + orderQuantity + "|" + orderDate + "|" + expectedDeliveryDate + "|" + status + "|" + purchaseManagerID;
    }

}
