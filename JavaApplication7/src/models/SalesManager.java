package models;

import java.io.IOException;
import java.util.List;

public class SalesManager extends User {

    private SalesAction salesAction = new SalesAction();
    private RequisitionAction requisitionAction = new RequisitionAction();
    private Inventory inventory = new Inventory();
    private PurchaseOrderAction purchaseOrderAction = new PurchaseOrderAction();

    public SalesManager(String userID, String username, String password) {
        super(userID, username, password, "salesManager");
    }

    // List of Items (View)
    public List<Item> getInventoryItems() throws IOException {
        return inventory.getInventoryItems();
    }

    // Sales part
    public double getSalesRevenue(Sales salesEntry) throws IOException {
        return salesAction.getSalesRevenue(salesEntry);
    }

    public List<Sales> getAllSalesEntries() throws IOException {
        return salesAction.getAllSalesEntries();
    }

    public Sales getSalesEntry(String salesId) throws IOException {
        return salesAction.getSalesEntry(salesId);
    }

    public Sales removeSalesEntry(String salesEntryID) throws IOException {
        return salesAction.removeSalesEntry(salesEntryID);
    }

    public Sales addSalesEntry(String itemID, int quantitySold, String dateSold, String notes) throws IOException {
        return salesAction.addSalesEntry(itemID, quantitySold, dateSold, notes);
    }

    public Sales updateSalesEntry(String salesID, String itemID, int quantitySold, String dateSold, String notes) throws IOException {
        return salesAction.updateSalesEntry(salesID, itemID, quantitySold, dateSold, notes);
    }

    // Create Requisition 
    public Requisition addRequisition(String newItemId, String newQuantity, String newRequiredDate, String newSalesManagerId, String newRequisitionDate) throws IOException {
        return requisitionAction.addRequisition(newItemId, newQuantity, newRequiredDate, newSalesManagerId, newRequisitionDate, "Pending");
    }

    // List of Purchaser Orders (View)
    public List<PurchaseOrder> getAllPurchaseOrders() throws IOException {
        return purchaseOrderAction.getAllPurchaseOrders();
    }
}
