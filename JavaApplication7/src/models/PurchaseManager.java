/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.IOException;
import java.util.List;

public class PurchaseManager extends User {

    private SupplierAction supplierAction = new SupplierAction();
    private RequisitionAction requisitionAction = new RequisitionAction();
    private PurchaseOrderAction purchaseOrderAction = new PurchaseOrderAction();
    private Inventory inventory = new Inventory();

    public PurchaseManager(String userID, String username, String password) {
        super(userID, username, password, "purchaseManager");
    }

    // List of Items (View)
    public List<Item> getInventoryItems() throws IOException {
        return inventory.getInventoryItems();
    }

    public Item getItem(String itemId) throws IOException {
        return inventory.getItem(itemId);
    }

    // List of Suppliers (View)
    public List<Supplier> getAllSupplier() throws IOException {
        return supplierAction.getAllSupplier();
    }

    // Requisitions part
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

    // Purchase orders part
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

}
