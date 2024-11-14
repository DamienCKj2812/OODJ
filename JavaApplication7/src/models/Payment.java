/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Aorus
 */
import java.util.Date;

public class Payment {

    private String paymentId;      // Unique identifier for each payment
    private String poId;           // Reference to the Purchase Order ID
    private String supplierId;     // Reference to the Supplier ID
    private String paymentDate;      // Date the payment was made
    private String paymentAmount;  // Amount paid
    private String paymentStatus;  // Status of the payment (e.g., pending, completed, rejected)

    // Constructor
    public Payment(String paymentId, String poId, String supplierId, String paymentDate, String paymentAmount, String paymentStatus) {
        this.paymentId = paymentId;
        this.poId = poId;
        this.supplierId = supplierId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPoId() {
        return poId;
    }

    public void setPoId(String poId) {
        this.poId = poId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // Optional: toString method for easy display
    @Override
    public String toString() {
        return paymentId + '|'
                + poId + '|'
                + supplierId + '|'
                + paymentDate + '|'
                + paymentAmount + '|'
                + paymentStatus;

    }
}
