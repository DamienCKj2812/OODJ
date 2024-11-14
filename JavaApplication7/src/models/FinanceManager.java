/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Aorus
 */
public class FinanceManager extends User {

    private PurchaseOrderAction purchaseOrderAction = new PurchaseOrderAction();
    private Inventory inventory = new Inventory();
    private PaymentAction paymentAction = new PaymentAction();

    public FinanceManager(String userID, String username, String password) {
        super(userID, username, password, "purchaseManager");
    }

    // Purchase orders part
    public List<PurchaseOrder> getAllPurchaseOrders() throws IOException {
        return purchaseOrderAction.getAllPurchaseOrders();
    }

    public PurchaseOrder updatePurchaseOrder(String purchaseOrderId, String newRequisitionID, String newItemId, String newOrderQuantity, String newOrderDate, String newExpectedDeliveryDate, String newStatus, String newPurchaseManagerID) throws IOException {
        return purchaseOrderAction.updatePurchaseOrder(purchaseOrderId, newRequisitionID, newItemId, newOrderQuantity, newOrderDate, newExpectedDeliveryDate, newStatus, newPurchaseManagerID);
    }

    public List<Item> getInventoryItems() throws IOException {
        return inventory.getInventoryItems();
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

    public Payment addPayment(String poId, String supplierId, String paymentDate, String paymentAmount, String paymentStatus) throws IOException {
        return paymentAction.addPayment(poId, supplierId, paymentDate, paymentAmount, paymentStatus
        );
    }

    public Payment updatePayment(String paymentId, String poId, String supplierId, String paymentDate, String paymentAmount, String paymentStatus) throws IOException {
        return paymentAction.updatePayment(paymentId, poId, supplierId, paymentDate, paymentAmount, paymentStatus);
    }
}
