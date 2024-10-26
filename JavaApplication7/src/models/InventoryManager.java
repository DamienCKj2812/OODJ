package models;

import java.io.IOException;
import java.util.List;

public class InventoryManager extends User {

    Inventory inventory = new Inventory();

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
}
