package models;

import constants.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import utils.FileManager;

public class Inventory {

    private FileManager fileManager = new FileManager(Constants.INVENTORY_DATA_PATH);

    public List<Item> getInventoryItems() throws IOException {
        String fileContent = fileManager.readFile();

        String[] itemLines = fileContent.split("\n");
        List<Item> items = new ArrayList<>();

        for (String line : itemLines) {
            String[] itemDetails = line.split("\\|");
            if (itemDetails.length >= 5) { // Check for the required number of details
                Item item = new Item(itemDetails[0], itemDetails[1], itemDetails[2], itemDetails[3], itemDetails[4]);
                items.add(item);
            } else {
                System.err.println("Skipping line due to incorrect format: " + line);
            }
        }

        return items;
    }

    public Item getItem(String itemId) throws IOException {
        List<Item> inventoryItems = getInventoryItems();

        for (Item item : inventoryItems) {
            if (item.getItemID().equals(itemId)) {
                return item; // Return the matching item object
            }
        }

        // Throw NoSuchElementException if the item is not found
        throw new NoSuchElementException("Item with ID " + itemId + " not found.");
    }

    public boolean updateItem(String itemId, String newName, String newPrice, String newQuantity, String newSupplierID) throws IOException {
        List<Item> items = getInventoryItems();
        StringBuilder updatedItems = new StringBuilder();
        boolean itemFound = false;

        // Iterate through the list of items
        for (Item item : items) {
            if (item.getItemID().equals(itemId)) {
                // If the item is found, update its details
                itemFound = true;
                item.setName(newName);
                item.setPrice(newPrice);
                item.setQuantity(newQuantity);
                item.setSupplierID(newSupplierID);
            }
            // Append the item details to the updatedItems StringBuilder
            updatedItems.append(item.getItemID())
                    .append("|")
                    .append(item.getName())
                    .append("|")
                    .append(item.getPrice())
                    .append("|")
                    .append(item.getQuantity())
                    .append("|")
                    .append(item.getSupplierID())
                    .append("\n");
        }

        // If the item was not found, throw an exception
        if (!itemFound) {
            throw new NoSuchElementException("Item with ID " + itemId + " not found.");
        }

        // Write the updated items back to the text file
        fileManager.writeFile(updatedItems.toString().trim()); // Assuming you have a writeFile method
        System.out.println("Item with ID " + itemId + " has been updated.");
        return true; // Return true indicating the update was successful
    }

    public Item addItem(String newName, String newPrice, String newQuantity, String newSupplierID) throws IOException {
        List<Item> items = getInventoryItems();
        StringBuilder updatedItem = new StringBuilder();

        // Check for existing username
        for (Item item : items) {
            if (item.getName().compareToIgnoreCase(newName) == 0) {
                System.out.println("Item name already exists.");
                return null; // Item name already taken
            }
            updatedItem.append(item).append("\n"); // Add existing users to updated list
        }

        // Generate a unique itemID using the current time in milliseconds
        String newItemID = "item" + System.currentTimeMillis();

        String itemData = String.format("%s|%s|%s|%s|%s", newItemID, newName, newPrice, newQuantity, newSupplierID);
        updatedItem.append(itemData); // Add new item to the updated list

        // Write the updated item list back to the file
        fileManager.writeFile(updatedItem.toString().trim()); // Ensures no trailing newline

        System.out.println("Item added successful!");
        return new Item(newItemID, newName, newPrice, newQuantity, newSupplierID); // Return the new Item object
    }

    public Item deleteItem(String itemID) throws IOException {
        List<Item> items = getInventoryItems();
        Item deletedItem = null;

        // Iterate over the list to find the item to delete
        for (Iterator<Item> iterator = items.iterator(); iterator.hasNext();) {
            Item item = iterator.next();

            // If the item matches the ID, delete it and save it to return later
            if (item.getItemID().equalsIgnoreCase(itemID)) {
                deletedItem = item;
                iterator.remove(); // Remove item from list
                break;
            }
        }

        if (deletedItem == null) {
            System.out.println("Item does not exist.");
            return null;
        }

        // Write updated list back to the file
        fileManager.writeFile(items.toString().trim());
        System.out.println("Item deleted successfully!");

        // Return the deleted item
        return deletedItem;
    }

}
