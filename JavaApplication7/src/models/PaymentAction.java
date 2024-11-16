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
public class PaymentAction {

    private FileManager fileManager = new FileManager(Constants.PAYMENT_DATA_PATH);

    public List<Payment> getAllPayment() throws IOException {
        String fileContent = fileManager.readFile();

        String[] paymentLines = fileContent.split("\n");
        List<Payment> payments = new ArrayList<>();

        for (String line : paymentLines) {
            String[] paymentDetails = line.split("\\|");
            if (paymentDetails.length >= 5) { // Check for the required number of details
                Payment payment = new Payment(paymentDetails[0], paymentDetails[1], paymentDetails[2], paymentDetails[3], paymentDetails[4]);
                payments.add(payment);
            } else {
                System.err.println("Skipping line due to incorrect format: " + line);
            }
        }

        return payments;
    }

    public Payment getPayment(String paymentID) throws IOException {
        List<Payment> payments = getAllPayment();

        return payments.stream()
                .filter(s -> s.getPaymentId().equals(paymentID))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Payment with ID " + paymentID + " not found"));
    }

    public Payment removePayment(String paymentID) throws IOException {
        StringBuilder updatedPayments = new StringBuilder();
        List<Payment> payments = getAllPayment();
        Payment selectedPayment = getPayment(paymentID);  // Get the payment to remove

        if (selectedPayment == null) {
            throw new IllegalArgumentException("Payment with ID " + paymentID + " not found.");
        }

        for (Payment payment : payments) {
            if (payment.getPaymentId().equals(paymentID)) {
                continue; // Skip adding this payment to the updated list
            }
            updatedPayments.append(String.format(
                    "%s|%s|%s|%s|%s%n",
                    payment.getPaymentId(),
                    payment.getPoId(),
                    payment.getSupplierId(),
                    payment.getPaymentDate(),
                    payment.getPaymentAmount()
            ));  // Add each payment except the one being removed
        }

        // Save the updated payment data back to the file
        fileManager.writeFile(updatedPayments.toString());

        return selectedPayment;
    }

    public Payment addPayment(String poId, String supplierId, String paymentDate, String paymentAmount) throws IOException {
        List<Payment> payments = getAllPayment();
        StringBuilder updatedPayments = new StringBuilder();

        // Generate unique payment ID
        String paymentID = "py" + System.currentTimeMillis();

        // Create new Payment object
        Payment newPayment = new Payment(paymentID, poId, supplierId, paymentDate, paymentAmount);

        // Add new payment to the list
        payments.add(newPayment);

        // Append each payment’s details to `updatedPayments`
        payments.forEach(payment -> updatedPayments.append(String.format(
                "%s|%s|%s|%s|%s%n",
                payment.getPaymentId(),
                payment.getPoId(),
                payment.getSupplierId(),
                payment.getPaymentDate(),
                payment.getPaymentAmount()
        )));

        // Save the updated payment data back to the file
        fileManager.writeFile(updatedPayments.toString());

        return newPayment;
    }

    public Payment updatePayment(String paymentId, String poId, String supplierId, String paymentDate, String paymentAmount) throws IOException {
        List<Payment> payments = getAllPayment();

        Payment paymentToUpdate = payments.stream()
                .filter(s -> s.getPaymentId().equals(paymentId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Payment with ID " + paymentId + " not found."));

        // Update the payment's information
        paymentToUpdate.setPoId(poId);
        paymentToUpdate.setSupplierId(supplierId);
        paymentToUpdate.setPaymentDate(paymentDate);
        paymentToUpdate.setPaymentAmount(paymentAmount);

        // Rebuild the file content with updated payment data
        StringBuilder updatedPayments = new StringBuilder();
        payments.forEach(payment -> updatedPayments.append(String.format(
                "%s|%s|%s|%s|%s%n",
                payment.getPaymentId(),
                payment.getPoId(),
                payment.getSupplierId(),
                payment.getPaymentDate(),
                payment.getPaymentAmount()
        )));

        // Write the updated payment list back to the file
        fileManager.writeFile(updatedPayments.toString());

        return paymentToUpdate;
    }
}
