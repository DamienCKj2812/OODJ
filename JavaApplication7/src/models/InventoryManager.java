package models;

import java.io.IOException;
import java.util.List;

public class InventoryManager extends User {

    Inventory inventory = new Inventory();
    SupplierAction supplierAction = new SupplierAction();

    public InventoryManager(String userID, String username, String password) {
        super(userID, username, password, "inventoryManager");
    }

    public List<Item> getInventoryItems() throws IOException {
        return inventory.getInventoryItems();
    }

    public Item getItem(String itemId) throws IOException {
        return inventory.getItem(itemId);
    }

    public boolean updateItem(String itemId, String newName, String newDescription, String newPrice, String newQuantity, String newReoderPoint, String newSupplierID) throws IOException {
        return inventory.updateItem(itemId, newName, newDescription, newPrice, newQuantity, newReoderPoint, newSupplierID);
    }

    public Item addItem(String newName, String newDescription, String newPrice, String newQuantity, String newReoderPoint,
            String newSupplierID) throws IOException {
        return inventory.addItem(newName, newDescription, newPrice, newQuantity, newReoderPoint, newSupplierID);
    }

    public Item deleteItem(String itemId) throws IOException {
        return inventory.deleteItem(itemId);
    }

    public List<Supplier> getAllSupplier() throws IOException {
        return supplierAction.getAllSupplier();
    }

    public Supplier getSupplier(String supplierID) throws IOException {
        return supplierAction.getSupplier(supplierID);
    }

    public Supplier removeSupplier(String supplierID) throws IOException {
        return supplierAction.removeSupplier(supplierID);
    }

    public Supplier addSupplier(String newName, String newContactPerson, String newAddress, String newPhoneNumber, String newProductsSupplied) throws IOException {
        return supplierAction.addSupplier(newName, newContactPerson, newAddress, newPhoneNumber, newProductsSupplied);
    }

    public Supplier updateSupplier(String supplierID, String newName, String newContactPerson, String newAddress, String newPhoneNumber, String newProductsSupplied) throws IOException {
        return supplierAction.updateSupplier(supplierID, newName, newContactPerson, newAddress, newPhoneNumber, newProductsSupplied);
    }
}
