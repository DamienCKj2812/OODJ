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
 * @author USER
 */
public class SalesManager extends User {

    private FileManager fileManager = new FileManager(Constants.SALES_ENTRY_DATA_PATH);
    private Inventory inventory = new Inventory();

    public SalesManager(String userID, String username, String password) {
        super(userID, username, password, "salesManager");
    }

    public List<Sales> getSalesEntries() throws IOException {
        String fileContent = fileManager.readFile();

        String[] itemLines = fileContent.split("\n");
        List<Sales> sales = new ArrayList<>();

        for (String line : itemLines) {
            String[] salesDetail = line.split("\\|");
            if (salesDetail.length >= 4) { // Check for the required number of details
                Sales sale = new Sales(salesDetail[0], salesDetail[1], salesDetail[2], salesDetail[3]);
                sales.add(sale);
            } else {
                System.err.println("Skipping line due to incorrect format: " + line);
            }
        }

        return sales;
    }

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

    public double getSalesRevenue(Sales salesEntry) throws IOException {
        Item itemSelected = inventory.getItem(salesEntry.getItemCode());
        return Double.parseDouble(itemSelected.getPrice()) * salesEntry.getQuantitySold();
    }

}
