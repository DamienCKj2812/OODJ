/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import constants.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import utils.FileManager;

/**
 *
 * @author Aorus
 */
public class SalesAction {

    private FileManager fileManager = new FileManager(Constants.SALES_ENTRY_DATA_PATH);
    private Inventory inventory = new Inventory();

    // Get all sales entries
    public List<Sales> getAllSalesEntries() throws IOException {
        String fileContent = fileManager.readFile();

        String[] itemLines = fileContent.split("\n");
        List<Sales> sales = new ArrayList<>();

        for (String line : itemLines) {
            String[] salesDetail = line.split("\\|");
            if (salesDetail.length >= 5) {
                String salesID = salesDetail[0];
                String itemID = salesDetail[1];
                int quantitySold = Integer.parseInt(salesDetail[2]);
                String dateSold = salesDetail[3];
                String notes = salesDetail[4];

                // Creating a Sales object with all required fields
                Sales sale = new Sales(salesID, itemID, quantitySold, dateSold, notes);
                sales.add(sale);
            } else {
                System.err.println("Skipping line due to incorrect format: " + line);
            }
        }

        return sales;
    }

    // Modified getSales method to find sales by itemID
    public Sales getSalesEntry(String salesId) throws IOException {
        List<Sales> salesEntries = getAllSalesEntries();

        for (Sales sale : salesEntries) {
            if (sale.getSalesID().equals(salesId)) {
                return sale; // Return the matching sale object
            }
        }

        // Throw NoSuchElementException if the sales is not found
        throw new NoSuchElementException("Sales with ID " + salesId + " not found.");
    }

    // Get singlle sales reveneue
    public double getSalesRevenue(Sales salesEntry) throws IOException {
        Item itemSelected = inventory.getItem(salesEntry.getItemID());
        return Double.parseDouble(itemSelected.getPrice()) * salesEntry.getQuantitySold();
    }

    public Sales removeSalesEntry(String salesEntryID) throws IOException {
        StringBuilder updatedSalesEntries = new StringBuilder();
        List<Sales> salesEntries = getAllSalesEntries();
        Sales selectedSalesEntry = getSalesEntry(salesEntryID);  // Get the purchaseEntry to remove

        if (selectedSalesEntry == null) {
            throw new IllegalArgumentException("SalesEntry with ID " + salesEntryID + " not found.");
        }

        for (Sales salesEntry : salesEntries) {
            if (salesEntry.getSalesID().equals(salesEntryID)) {
                continue; // Skip adding this salesEntry to the updated list
            }
            updatedSalesEntries.append(String.format(
                    "%s|%s|%s|%s|%s%n",
                    salesEntry.getSalesID(),
                    salesEntry.getItemID(),
                    salesEntry.getQuantitySold(),
                    salesEntry.getDateSold(),
                    salesEntry.getNotes()
            ));
        }

        // Save the updated salesEntry data back to the file
        fileManager.writeFile(updatedSalesEntries.toString());

        return selectedSalesEntry;
    }

    public Sales addSalesEntry(String itemID, int quantitySold, String dateSold, String notes) throws IOException {
        Item item = inventory.getItem(itemID);

        // Check if the item exists; if not, throw an exception
        if (item == null) {
            throw new IllegalArgumentException("Item with ID " + itemID + " not found in the inventory.");
        }
        List<Sales> salesEntries = getAllSalesEntries();
        StringBuilder updatedSalesEntries = new StringBuilder();

        // Generate unique salesEntry ID
        String salesID = "se" + System.currentTimeMillis();

        // Create new SalesEntry object
        Sales newSalesEntry = new Sales(salesID, itemID, quantitySold, dateSold, notes);

        // Add new salesEntry to the list
        salesEntries.add(newSalesEntry);

        // Append each salesEntry’s details to `updatedSalesEntries`
        salesEntries.forEach(salesEntry -> updatedSalesEntries.append(String.format(
                "%s|%s|%s|%s|%s%n",
                salesEntry.getSalesID(),
                salesEntry.getItemID(),
                salesEntry.getQuantitySold(),
                salesEntry.getDateSold(),
                salesEntry.getNotes()
        )));

        // Save the updated sales data back to the file
        fileManager.writeFile(updatedSalesEntries.toString());

        return newSalesEntry;
    }

    public Sales updateSalesEntry(String salesID, String itemID, int quantitySold, String dateSold, String notes) throws IOException {
        Item item = inventory.getItem(itemID);

        if (item == null) {
            throw new IllegalArgumentException("Item with ID " + itemID + " not found in the inventory.");
        }

        List<Sales> salesEntries = getAllSalesEntries();
        Sales salesEntryToUpdate = salesEntries.stream()
                .filter(s -> s.getSalesID().equals(salesID))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Sales with ID " + salesID + " not found."));

        // Update the salesEntry's information
        salesEntryToUpdate.setItemID(itemID);
        salesEntryToUpdate.setQuantitySold(quantitySold);
        salesEntryToUpdate.setDateSold(dateSold);
        salesEntryToUpdate.setNotes(notes);

        // Rebuild the file content with updated salesEntry data
        StringBuilder updatedSaless = new StringBuilder();
        salesEntries.forEach(salesEntry -> updatedSaless.append(String.format(
                "%s|%s|%s|%s|%s%n",
                salesEntry.getSalesID(),
                salesEntry.getItemID(),
                salesEntry.getQuantitySold(),
                salesEntry.getDateSold(),
                salesEntry.getNotes()
        )));

        // Write the updated salesEntry list back to the file
        fileManager.writeFile(updatedSaless.toString());

        return salesEntryToUpdate;
    }
}
