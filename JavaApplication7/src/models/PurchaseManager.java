/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import constants.Constants;
import utils.FileManager;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PurchaseManager extends User {

    private FileManager fileManager;

    public PurchaseManager(String userID, String username, String password) {
        super(userID, username, password, "purchaseManager");
        fileManager = new FileManager(Constants.PURCHASE_ORDER_DATA_PATH);
    }

    public List<PurchaseOrder> getPurchaseOrders() throws IOException {
        String fileContent = fileManager.readFile();
        String[] poLines = fileContent.split("\n");
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();

        for (String line : poLines) {
            String[] poDetails = line.split("\\|");
            if (poDetails.length >= 11) {
                PurchaseOrder purchaseOrder = new PurchaseOrder(poDetails[0], poDetails[1], poDetails[2], poDetails[3], poDetails[4], poDetails[5], poDetails[6], poDetails[7], poDetails[8], poDetails[9], poDetails[10]);
                purchaseOrders.add(purchaseOrder);
            } else {
                System.err.println("Skipping line due to incorrect format: " + line);
            }
        }
        return purchaseOrders;
    }

    public PurchaseOrder getPurchaseOrder(String poNumber) throws IOException {
        List<PurchaseOrder> purchaseOrders = getPurchaseOrders();
        for (PurchaseOrder po : purchaseOrders) {
            if (po.getPoNumber().equals(poNumber)) {
                return po;
            }
        }
        throw new NoSuchElementException("Purchase Order with PO Number " + poNumber + " not found.");
    }

    public boolean createPurchaseOrder(String requisitionID, String itemCode, String itemName, String quantity, String supplierID, String orderDate, String expectedDeliveryDate, String status, String totalCost) throws IOException {
        List<PurchaseOrder> purchaseOrders = getPurchaseOrders();
        StringBuilder updatedOrders = new StringBuilder();

        for (PurchaseOrder po : purchaseOrders) {
            updatedOrders.append(po.getPoNumber()).append("|")
                    .append(po.getRequisitionID()).append("|")
                    .append(po.getItemCode()).append("|")
                    .append(po.getItemName()).append("|")
                    .append(po.getQuantity()).append("|")
                    .append(po.getSupplierID()).append("|")
                    .append(po.getOrderDate()).append("|")
                    .append(po.getExpectedDeliveryDate()).append("|")
                    .append(po.getStatus()).append("|")
                    .append(po.getTotalCost()).append("|")
                    .append(po.getPurchaseManagerID()).append("\n");
        }

        // Generate a unique PO Number using the current time in milliseconds
        String newPONumber = "PO" + System.currentTimeMillis();

        String newPurchaseOrderData = String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s", 
                newPONumber, requisitionID, itemCode, itemName, quantity, supplierID, orderDate, expectedDeliveryDate, status, totalCost, "purchaseManagerID");

        updatedOrders.append(newPurchaseOrderData);

        fileManager.writeFile(updatedOrders.toString().trim());
        return true;
    }

    public boolean updatePurchaseOrder(String poNumber, String newStatus) throws IOException {
        List<PurchaseOrder> purchaseOrders = getPurchaseOrders();
        StringBuilder updatedOrders = new StringBuilder();
        boolean poFound = false;

        for (PurchaseOrder po : purchaseOrders) {
            if (po.getPoNumber().equals(poNumber)) {
                poFound = true;
                po.setStatus(newStatus);
            }
            updatedOrders.append(po.getPoNumber()).append("|")
                    .append(po.getRequisitionID()).append("|")
                    .append(po.getItemCode()).append("|")
                    .append(po.getItemName()).append("|")
                    .append(po.getQuantity()).append("|")
                    .append(po.getSupplierID()).append("|")
                    .append(po.getOrderDate()).append("|")
                    .append(po.getExpectedDeliveryDate()).append("|")
                    .append(po.getStatus()).append("|")
                    .append(po.getTotalCost()).append("|")
                    .append(po.getPurchaseManagerID()).append("\n");
        }

        if (!poFound) {
            throw new NoSuchElementException("Purchase Order with PO Number " + poNumber + " not found.");
        }

        fileManager.writeFile(updatedOrders.toString().trim());
        return true;
    }

    public boolean deletePurchaseOrder(String poNumber) throws IOException {
        List<PurchaseOrder> purchaseOrders = getPurchaseOrders();
        StringBuilder updatedOrders = new StringBuilder();
        boolean poFound = false;

        for (PurchaseOrder po : purchaseOrders) {
            if (!po.getPoNumber().equals(poNumber)) {
                updatedOrders.append(po.getPoNumber()).append("|")
                        .append(po.getRequisitionID()).append("|")
                        .append(po.getItemCode()).append("|")
                        .append(po.getItemName()).append("|")
                        .append(po.getQuantity()).append("|")
                        .append(po.getSupplierID()).append("|")
                        .append(po.getOrderDate()).append("|")
                        .append(po.getExpectedDeliveryDate()).append("|")
                        .append(po.getStatus()).append("|")
                        .append(po.getTotalCost()).append("|")
                        .append(po.getPurchaseManagerID()).append("\n");
            } else {
                poFound = true;
            }
        }

        if (!poFound) {
            throw new NoSuchElementException("Purchase Order with PO Number " + poNumber + " not found.");
        }

        fileManager.writeFile(updatedOrders.toString().trim());
        return true;
    }
}