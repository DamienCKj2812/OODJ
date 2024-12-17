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
public class RequisitionAction {

    private FileManager fileManager = new FileManager(Constants.REQUISITION_DATA_PATH);

    public List<Requisition> getAllRequisitions() throws IOException {
        String fileContent = fileManager.readFile();

        String[] lines = fileContent.split("\n");
        List<Requisition> requisitions = new ArrayList<>();

        for (String line : lines) {
            String[] requisitionDetail = line.split("\\|");
            if (requisitionDetail.length >= 6) { // Check for the required number of details
                Requisition requisition = new Requisition(requisitionDetail[0], requisitionDetail[1], requisitionDetail[2], requisitionDetail[3], requisitionDetail[4], requisitionDetail[5]);
                requisitions.add(requisition);
            } else {
                System.err.println("Skipping line due to incorrect format: " + line);
            }
        }

        return requisitions;
    }

    public Requisition getRequisition(String requisitionId) throws IOException {
        List<Requisition> requisitions = getAllRequisitions();

        return requisitions.stream()
                .filter(s -> s.getRequisitionId().equals(requisitionId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Requisition with ID " + requisitionId + " not found"));
    }

    public Requisition removeRequisition(String requisitionId) throws IOException {
        StringBuilder updatedRequisitions = new StringBuilder();
        List<Requisition> requisitions = getAllRequisitions();
        Requisition selectedRequisition = getRequisition(requisitionId);  // Get the requisition to remove

        if (selectedRequisition == null) {
            throw new IllegalArgumentException("Requisition with ID " + requisitionId + " not found.");
        }

        for (Requisition requisition : requisitions) {
            if (requisition.getRequisitionId().equals(requisitionId)) {
                continue; // Skip adding this requisition to the updated list
            }
            updatedRequisitions.append(String.format(
                    "%s|%s|%s|%s|%s|%s%n",
                    requisition.getRequisitionId(),
                    requisition.getItemId(),
                    requisition.getQuantity(),
                    requisition.getRequiredDate(),
                    requisition.getSalesManagerId(),
                    requisition.getRequisitionDate()
            ));  // Add each requisition except the one being removed
        }

        // Save the updated requisition data back to the file
        fileManager.writeFile(updatedRequisitions.toString());

        return selectedRequisition;
    }

    public Requisition addRequisition(String newItemId, String newQuantity, String newRequiredDate, String newSalesManagerId, String newRequisitionDate) throws IOException {
        List<Requisition> requisitions = getAllRequisitions();
        StringBuilder updatedRequisitions = new StringBuilder();

        // Generate unique requisition ID
        String requisitionID = "req" + System.currentTimeMillis();

        // Create new Requisition object
        Requisition newRequisition = new Requisition(requisitionID, newItemId, newQuantity, newRequiredDate, newSalesManagerId, newRequisitionDate);

        // Add new requisition to the list
        requisitions.add(newRequisition);

        // Append each requisition’s details to `Requisitions`
        requisitions.forEach(requisition -> updatedRequisitions.append(String.format(
                "%s|%s|%s|%s|%s|%s%n",
                requisition.getRequisitionId(),
                requisition.getItemId(),
                requisition.getQuantity(),
                requisition.getRequiredDate(),
                requisition.getSalesManagerId(),
                requisition.getRequisitionDate()
        )));

        // Save the updated requisition data back to the file
        fileManager.writeFile(updatedRequisitions.toString());

        return newRequisition;
    }

    public Requisition updateRequisition(String requisitionId, String newItemId, String newQuantity, String newRequiredDate, String newSalesManagerId, String newRequisitionDate) throws IOException {
        List<Requisition> requisitions = getAllRequisitions();

        Requisition requisitionToUpdate = requisitions.stream()
                .filter(s -> s.getRequisitionId().equals(requisitionId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Requisition with ID " + requisitionId + " not found."));

        // Update the requisition's information
        requisitionToUpdate.setItemId(newItemId);
        requisitionToUpdate.setQuantity(newQuantity);
        requisitionToUpdate.setRequiredDate(newRequiredDate);
        requisitionToUpdate.setSalesManagerId(newSalesManagerId);
        requisitionToUpdate.setRequisitionDate(newRequisitionDate);

        // Rebuild the file content with updated requisition data
        StringBuilder updatedRequisitions = new StringBuilder();
        requisitions.forEach(requisition -> updatedRequisitions.append(String.format(
                "%s|%s|%s|%s|%s|%s%n",
                requisition.getRequisitionId(),
                requisition.getItemId(),
                requisition.getQuantity(),
                requisition.getRequiredDate(),
                requisition.getSalesManagerId(),
                requisition.getRequisitionDate()
        )));

        // Write the updated requisition list back to the file
        fileManager.writeFile(updatedRequisitions.toString());

        return requisitionToUpdate;
    }
}
