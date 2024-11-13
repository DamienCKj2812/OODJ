package models;

import constants.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import utils.FileManager;

public class SalesManager extends User {

    private FileManager fileManager = new FileManager(Constants.SALES_ENTRY_DATA_PATH);
    private Inventory inventory = new Inventory();

    public SalesManager(String userID, String username, String password) {
        super(userID, username, password, "salesManager");
    }

    // Modified getSalesEntries to handle the new Sales constructor with itemName, unitPrice, and totalAmount
    public List<Sales> getSalesEntries() throws IOException {
        String fileContent = fileManager.readFile();

        String[] itemLines = fileContent.split("\n");
        List<Sales> sales = new ArrayList<>();

        for (String line : itemLines) {
            String[] salesDetail = line.split("\\|");
            if (salesDetail.length >= 6) { // Check if there are enough fields (itemCode, quantitySold, dateSold, itemName, unitPrice, totalAmount)
                String itemCode = salesDetail[0];
                int quantitySold = Integer.parseInt(salesDetail[1]);
                String dateSold = salesDetail[2];
                String itemName = salesDetail[3]; // itemName (formerly notes)
                double unitPrice = Double.parseDouble(salesDetail[4]);
                double totalAmount = Double.parseDouble(salesDetail[5]);

                // Creating a Sales object with all required fields
                Sales sale = new Sales(itemCode, quantitySold, dateSold, itemName, unitPrice);
                sales.add(sale);
            } else {
                System.err.println("Skipping line due to incorrect format: " + line);
            }
        }

        return sales;
    }

    // Modified getSales method to find sales by itemCode
    public Sales getSales(String salesId) throws IOException {
        List<Sales> salesEntries = getSalesEntries();

        for (Sales sale : salesEntries) {
            if (sale.getItemCode().equals(salesId)) {
                return sale; // Return the matching sale object
            }
        }

        // Throw NoSuchElementException if the item is not found
        throw new NoSuchElementException("Sales with ID " + salesId + " not found.");
    }

    // Modified getSalesRevenue to use itemPrice and quantitySold from Sales
    public double getSalesRevenue(Sales salesEntry) throws IOException {
        Item itemSelected = inventory.getItem(salesEntry.getItemCode());
        return Double.parseDouble(itemSelected.getPrice()) * salesEntry.getQuantitySold();
    }
}


