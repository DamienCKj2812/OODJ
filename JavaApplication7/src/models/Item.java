package models;

public class Item {

    private String itemID;
    private String name;
    private String price;
    private String quantity;
    private String supplierID;

    // Constructor
    public Item(String itemID, String name, String price, String quantity, String supplierID) {
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.supplierID = supplierID;

    }

    // Getters
    public String getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    @Override
    public String toString() {
        return itemID + "|" + name + "|" + price + "|" + quantity + "|" + supplierID;
    }
}
