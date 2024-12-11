/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Aorus
 */
public class FinanceManager extends User {

    private PurchaseOrderAction purchaseOrderAction = new PurchaseOrderAction();
    private Inventory inventory = new Inventory();
    private PaymentAction paymentAction = new PaymentAction();
    private SupplierAction supplierAction = new SupplierAction();

    public FinanceManager(String userID, String username, String password) {
        super(userID, username, password, "purchaseManager");
    }

    // Purchase orders part
    public List<PurchaseOrder> getAllPurchaseOrders() throws IOException {
        return purchaseOrderAction.getAllPurchaseOrders();
    }

    public PurchaseOrder getPurchaseOrder(String purchaseOrderId) throws IOException {
        return purchaseOrderAction.getPurchaseOrder(purchaseOrderId);
    }

    public PurchaseOrder updatePurchaseOrder(String purchaseOrderId, String newRequisitionID, String newItemId, String newOrderQuantity, String newOrderDate, String newExpectedDeliveryDate, String newStatus, String newPurchaseManagerID) throws IOException {
        return purchaseOrderAction.updatePurchaseOrder(purchaseOrderId, newRequisitionID, newItemId, newOrderQuantity, newOrderDate, newExpectedDeliveryDate, newStatus, newPurchaseManagerID);
    }

    public List<PurchaseOrder> getAllApprovedPurchaseOrders() throws IOException {
        List<PurchaseOrder> allPurchaseOrders = purchaseOrderAction.getAllPurchaseOrders();
        return allPurchaseOrders.stream()
                .filter(o -> o.getStatus().toLowerCase().equals("approved"))
                .collect(Collectors.toList()); // Collect the stream to a list
    }

    public List<Item> getInventoryItems() throws IOException {
        return inventory.getInventoryItems();
    }

    public Item getItems(String itemId) throws IOException {
        return inventory.getItem(itemId);
    }

    public List<Payment> getAllPayment() throws IOException {
        return paymentAction.getAllPayment();
    }

    public Payment getPayment(String paymentID) throws IOException {
        return paymentAction.getPayment(paymentID);
    }

    public Payment removePayment(String paymentID) throws IOException {
        return paymentAction.removePayment(paymentID);
    }

    public Payment addPayment(String poId, String supplierId, String paymentDate, String paymentAmount) throws IOException {
        return paymentAction.addPayment(poId, supplierId, paymentDate, paymentAmount);
    }

    public Payment updatePayment(String paymentId, String poId, String supplierId, String paymentDate, String paymentAmount) throws IOException {
        return paymentAction.updatePayment(paymentId, poId, supplierId, paymentDate, paymentAmount);
    }

    public List<Supplier> getAllSupplier() throws IOException {
        return supplierAction.getAllSupplier();
    }
}
