/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class Sales {

    private String salesID;
    private String itemID;
    private int quantitySold;
    private String dateSold;
    private String notes;

    // Constructor
    public Sales(String salesID, String itemID, int quantitySold, String dateSold, String notes) {
        this.salesID = salesID;
        this.itemID = itemID;
        this.quantitySold = quantitySold;
        this.dateSold = dateSold;
        this.notes = notes;
    }

    public String getSalesID() {
        return salesID;
    }

    public String getItemID() {
        return itemID;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public String getDateSold() {
        return dateSold;
    }

    public String getNotes() {
        return notes;
    }

    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public void setDateSold(String dateSold) {
        this.dateSold = dateSold;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Override toString to include unitPrice and totalAmount
    @Override
    public String toString() {
        return salesID + "|" + itemID + "|" + quantitySold + "|" + dateSold + "|" + notes;
    }
}
