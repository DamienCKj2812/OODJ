package models;

import constants.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import utils.FileManager;

public class SupplierAction {

    private FileManager fileManager = new FileManager(Constants.SUPPLIER_DATA_PATH);

    public List<Supplier> getAllSupplier() throws IOException {
        String fileContent = fileManager.readFile();

        String[] supplierLines = fileContent.split("\n");
        List<Supplier> suppliers = new ArrayList<>();

        for (String line : supplierLines) {
            String[] supplierDetails = line.split("\\|");
            if (supplierDetails.length >= 6) { // Check for the required number of details
                Supplier supplier = new Supplier(supplierDetails[0], supplierDetails[1], supplierDetails[2], supplierDetails[3], supplierDetails[4], supplierDetails[5]);
                suppliers.add(supplier);
            } else {
                System.err.println("Skipping line due to incorrect format: " + line);
            }
        }

        return suppliers;
    }

    public Supplier getSupplier(String supplierID) throws IOException {
        List<Supplier> suppliers = getAllSupplier();

        return suppliers.stream()
                .filter(s -> s.getId().equals(supplierID))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Supplier with ID " + supplierID + " not found"));
    }

    public Supplier removeSupplier(String supplierID) throws IOException {
        StringBuilder updatedSuppliers = new StringBuilder();
        List<Supplier> suppliers = getAllSupplier();
        Supplier selectedSupplier = getSupplier(supplierID);  // Get the supplier to remove

        if (selectedSupplier == null) {
            throw new IllegalArgumentException("Supplier with ID " + supplierID + " not found.");
        }

        for (Supplier supplier : suppliers) {
            if (supplier.getId().equals(supplierID)) {
                continue; // Skip adding this supplier to the updated list
            }
            updatedSuppliers.append(String.format(
                    "%s|%s|%s|%s|%s|%s%n",
                    supplier.getId(),
                    supplier.getName(),
                    supplier.getContactPerson(),
                    supplier.getAddress(),
                    supplier.getPhoneNumber(),
                    supplier.getProductsSupplied()
            ));  // Add each supplier except the one being removed
        }

        // Save the updated supplier data back to the file
        fileManager.writeFile(updatedSuppliers.toString());

        return selectedSupplier;
    }

    public Supplier addSupplier(String newName, String newContactPerson, String newAddress, String newPhoneNumber, String newProductsSupplied) throws IOException {
        List<Supplier> suppliers = getAllSupplier();
        StringBuilder updatedSuppliers = new StringBuilder();

        // Generate unique supplier ID
        String supplierID = "sp" + System.currentTimeMillis();

        // Create new Supplier object
        Supplier newSupplier = new Supplier(supplierID, newName, newContactPerson, newAddress, newPhoneNumber, newProductsSupplied);

        // Add new supplier to the list
        suppliers.add(newSupplier);

        // Append each supplier’s details to `updatedSuppliers`
        suppliers.forEach(supplier -> updatedSuppliers.append(String.format(
                "%s|%s|%s|%s|%s|%s%n",
                supplier.getId(),
                supplier.getName(),
                supplier.getContactPerson(),
                supplier.getAddress(),
                supplier.getPhoneNumber(),
                supplier.getProductsSupplied()
        )));

        // Save the updated supplier data back to the file
        fileManager.writeFile(updatedSuppliers.toString());

        return newSupplier;
    }

    public Supplier updateSupplier(String supplierID, String newName, String newContactPerson, String newAddress, String newPhoneNumber, String newProductsSupplied) throws IOException {
        List<Supplier> suppliers = getAllSupplier();

        Supplier supplierToUpdate = suppliers.stream()
                .filter(s -> s.getId().equals(supplierID))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Supplier with ID " + supplierID + " not found."));

        // Update the supplier's information
        supplierToUpdate.setName(newName);
        supplierToUpdate.setContactPerson(newContactPerson);
        supplierToUpdate.setAddress(newAddress);
        supplierToUpdate.setPhoneNumber(newPhoneNumber);
        supplierToUpdate.setProductsSupplied(newProductsSupplied);

        // Rebuild the file content with updated supplier data
        StringBuilder updatedSuppliers = new StringBuilder();
        suppliers.forEach(supplier -> updatedSuppliers.append(String.format(
                "%s|%s|%s|%s|%s|%s%n",
                supplier.getId(),
                supplier.getName(),
                supplier.getContactPerson(),
                supplier.getAddress(),
                supplier.getPhoneNumber(),
                supplier.getProductsSupplied()
        )));

        // Write the updated supplier list back to the file
        fileManager.writeFile(updatedSuppliers.toString());

        return supplierToUpdate;
    }

}