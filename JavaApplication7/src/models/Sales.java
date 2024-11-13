/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import constants.Constants;
import java.util.Date;
import utils.FileManager;

public class Sales {

    private String itemCode;
    private int quantitySold;
    private String dateSold;
    private String notes;
    private double unitPrice;
    private double totalAmount;

    // Constructor
    public Sales(String itemCode, int quantitySold, String dateSold, String notes, double unitPrice) {
        this.itemCode = itemCode;
        this.quantitySold = quantitySold; // directly take int
        this.dateSold = dateSold;
        this.notes = notes;
        this.unitPrice = unitPrice;
        this.totalAmount = this.quantitySold * unitPrice;
    }

    // Getters and Setters
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
        this.totalAmount = quantitySold * this.unitPrice; // Update totalAmount when quantity changes
    }

    public String getDateSold() {
        return dateSold;
    }

    public void setDateSold(String dateSold) {
        this.dateSold = dateSold;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        this.totalAmount = this.quantitySold * unitPrice; // Update totalAmount when unitPrice changes
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    // Override toString to include unitPrice and totalAmount
    @Override
    public String toString() {
        return itemCode + "|" + quantitySold + "|" + dateSold + "|" + notes + "|" + unitPrice + "|" + totalAmount;
    }
}
