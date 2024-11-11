/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import constants.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import utils.FileManager;

/**
 *
 * @author Aorus
 */
public class PurchaseOrderAction {

    private FileManager fileManager = new FileManager(Constants.PURCHASE_ORDER_DATA_PATH);

    public List<PurchaseOrder> getAllPurchaseOrders() throws IOException {
        String fileContent = fileManager.readFile();

        String[] purchaseOrderLines = fileContent.split("\n");
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();

        for (String line : purchaseOrderLines) {
            String[] purchaseOrderDetails = line.split("\\|");
            if (purchaseOrderDetails.length >= 7) { // Check for the required number of details
                PurchaseOrder purchaseOrder = new PurchaseOrder(purchaseOrderDetails[0], purchaseOrderDetails[1], purchaseOrderDetails[2], purchaseOrderDetails[3], purchaseOrderDetails[4], purchaseOrderDetails[5], purchaseOrderDetails[6], purchaseOrderDetails[7]);
                purchaseOrders.add(purchaseOrder);
            } else {
                System.err.println("Skipping line due to incorrect format: " + line);
            }
        }

        return purchaseOrders;
    }

    public PurchaseOrder getPurchaseOrder(String purchaseOrderID) throws IOException {
        List<PurchaseOrder> purchaseOrders = getAllPurchaseOrders();

        return purchaseOrders.stream()
                .filter(s -> s.getPurchaseOrderId().equals(purchaseOrderID))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("PurchaseOrder with ID " + purchaseOrderID + " not found"));
    }

    public PurchaseOrder removePurchaseOrder(String purchaseOrderID) throws IOException {
        StringBuilder updatedPurchaseOrders = new StringBuilder();
        List<PurchaseOrder> purchaseOrders = getAllPurchaseOrders();
        PurchaseOrder selectedPurchaseOrder = getPurchaseOrder(purchaseOrderID);  // Get the purchaseOrder to remove

        if (selectedPurchaseOrder == null) {
            throw new IllegalArgumentException("PurchaseOrder with ID " + purchaseOrderID + " not found.");
        }

        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            if (purchaseOrder.getPurchaseOrderId().equals(purchaseOrderID)) {
                continue; // Skip adding this purchaseOrder to the updated list
            }
            updatedPurchaseOrders.append(String.format(
                    "%s|%s|%s|%s|%s|%s|%s|%s%n",
                    purchaseOrder.getPurchaseOrderId(),
                    purchaseOrder.getRequisitionID(),
                    purchaseOrder.getItemId(),
                    purchaseOrder.getOrderQuantity(),
                    purchaseOrder.getOrderDate(),
                    purchaseOrder.getExpectedDeliveryDate(),
                    purchaseOrder.getStatus(),
                    purchaseOrder.getPurchaseManagerID()
            ));  // Add each purchaseOrder except the one being removed
        }

        // Save the updated purchaseOrder data back to the file
        fileManager.writeFile(updatedPurchaseOrders.toString());

        return selectedPurchaseOrder;
    }

    public PurchaseOrder addPurchaseOrder(String newRequisitionID, String newItemId, String newOrderQuantity, String newOrderDate, String newExpectedDeliveryDate, String newStatus, String newPurchaseManagerID) throws IOException {
        List<PurchaseOrder> purchaseOrders = getAllPurchaseOrders();
        StringBuilder updatedPurchaseOrders = new StringBuilder();

        // Generate unique purchaseOrder ID
        String purchaseOrderID = "po" + System.currentTimeMillis();

        // Create new PurchaseOrder object
        PurchaseOrder newPurchaseOrder = new PurchaseOrder(purchaseOrderID, newRequisitionID, newItemId, newOrderQuantity, newOrderDate, newExpectedDeliveryDate, newStatus, newPurchaseManagerID);

        // Add new purchaseOrder to the list
        purchaseOrders.add(newPurchaseOrder);

        // Append each purchaseOrder’s details to `updatedPurchaseOrders`
        purchaseOrders.forEach(purchaseOrder -> updatedPurchaseOrders.append(String.format(
                "%s|%s|%s|%s|%s|%s|%s|%s%n",
                purchaseOrder.getPurchaseOrderId(),
                purchaseOrder.getRequisitionID(),
                purchaseOrder.getItemId(),
                purchaseOrder.getOrderQuantity(),
                purchaseOrder.getOrderDate(),
                purchaseOrder.getExpectedDeliveryDate(),
                purchaseOrder.getStatus(),
                purchaseOrder.getPurchaseManagerID()
        )));

        // Save the updated purchaseOrder data back to the file
        fileManager.writeFile(updatedPurchaseOrders.toString());

        return newPurchaseOrder;
    }

    public PurchaseOrder updatePurchaseOrder(String purchaseOrderId, String newRequisitionID, String newItemId, String newOrderQuantity, String newOrderDate, String newExpectedDeliveryDate, String newStatus, String newPurchaseManagerID) throws IOException {
        List<PurchaseOrder> purchaseOrders = getAllPurchaseOrders();

        PurchaseOrder purchaseOrderToUpdate = purchaseOrders.stream()
                .filter(s -> s.getPurchaseOrderId().equals(purchaseOrderId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("PurchaseOrder with ID " + purchaseOrderId + " not found."));

        // Update the purchaseOrder's information
        purchaseOrderToUpdate.setRequisitionID(newRequisitionID);
        purchaseOrderToUpdate.setItemId(newItemId);
        purchaseOrderToUpdate.setOrderQuantity(newOrderQuantity);
        purchaseOrderToUpdate.setOrderDate(newOrderDate);
        purchaseOrderToUpdate.setExpectedDeliveryDate(newExpectedDeliveryDate);
        purchaseOrderToUpdate.setStatus(newStatus);
        purchaseOrderToUpdate.setPurchaseManagerID(newPurchaseManagerID);

        // Rebuild the file content with updated purchaseOrder data
        StringBuilder updatedPurchaseOrders = new StringBuilder();
        purchaseOrders.forEach(purchaseOrder -> updatedPurchaseOrders.append(String.format(
                "%s|%s|%s|%s|%s|%s|%s|%s%n",
                purchaseOrder.getPurchaseOrderId(),
                purchaseOrder.getRequisitionID(),
                purchaseOrder.getItemId(),
                purchaseOrder.getOrderQuantity(),
                purchaseOrder.getOrderDate(),
                purchaseOrder.getExpectedDeliveryDate(),
                purchaseOrder.getStatus(),
                purchaseOrder.getPurchaseManagerID()
        )));

        // Write the updated purchaseOrder list back to the file
        fileManager.writeFile(updatedPurchaseOrders.toString());

        return purchaseOrderToUpdate;
    }
}
