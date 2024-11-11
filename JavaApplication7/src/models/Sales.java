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

    // Constructor
    public Sales(String itemCode, String quantitySold, String dateSold, String notes) {
        this.itemCode = itemCode;
        this.quantitySold = Integer.parseInt(quantitySold);
        this.dateSold = dateSold;
        this.notes = notes;
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

    @Override
    public String toString() {
        return itemCode + "|" + quantitySold + "|" + dateSold + "|" + notes;
    }
}
