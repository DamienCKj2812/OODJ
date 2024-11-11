/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import constants.Constants;
import utils.FileManager;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PurchaseManager extends User {

    private RequisitionAction requisitionAction = new RequisitionAction();
    private PurchaseOrderAction purchaseOrderAction = new PurchaseOrderAction();

    public PurchaseManager(String userID, String username, String password) {
        super(userID, username, password, "purchaseManager");
    }

    public List<PurchaseOrder> getAllPurchaseOrders() throws IOException {
        return purchaseOrderAction.getAllPurchaseOrders();
    }

    public PurchaseOrder getPurchaseOrder(String requisitionId) throws IOException {
        return purchaseOrderAction.getPurchaseOrder(requisitionId);
    }

    public PurchaseOrder removePurchaseOrder(String requisitionId) throws IOException {
        return purchaseOrderAction.removePurchaseOrder(requisitionId);
    }

    public PurchaseOrder addPurchaseOrder(String newRequisitionID, String newItemId, String newOrderQuantity, String newOrderDate, String newExpectedDeliveryDate, String newStatus, String newPurchaseManagerID) throws IOException {
        return purchaseOrderAction.addPurchaseOrder(newRequisitionID, newItemId, newOrderQuantity, newOrderDate, newExpectedDeliveryDate, newStatus, newPurchaseManagerID);
    }

    public PurchaseOrder updatePurchaseOrder(String purchaseOrderId, String newRequisitionID, String newItemId, String newOrderQuantity, String newOrderDate, String newExpectedDeliveryDate, String newStatus, String newPurchaseManagerID) throws IOException {
        return purchaseOrderAction.updatePurchaseOrder(purchaseOrderId, newRequisitionID, newItemId, newOrderQuantity, newOrderDate, newExpectedDeliveryDate, newStatus, newPurchaseManagerID);
    }

    public List<Requisition> getAllRequisitions() throws IOException {
        return requisitionAction.getAllRequisitions();
    }

    public Requisition getRequisition(String requisitionId) throws IOException {
        return requisitionAction.getRequisition(requisitionId);
    }

    public Requisition removeRequisition(String requisitionId) throws IOException {
        return requisitionAction.removeRequisition(requisitionId);
    }

    public Requisition addRequisition(String newItemId, String newQuantity, String newRequiredDate, String newSalesManagerId, String newRequisitionDate, String newStatus) throws IOException {
        return requisitionAction.addRequisition(newItemId, newQuantity, newRequiredDate, newSalesManagerId, newRequisitionDate, newStatus);
    }

    public Requisition updateRequisition(String requisitionId, String newItemId, String newQuantity, String newRequiredDate, String newSalesManagerId, String newRequisitionDate, String newStatus) throws IOException {
        return requisitionAction.updateRequisition(requisitionId, newItemId, newQuantity, newRequiredDate, newSalesManagerId, newRequisitionDate, newStatus);
    }

}
