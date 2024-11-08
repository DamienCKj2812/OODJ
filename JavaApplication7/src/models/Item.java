package models;

public class Item {

    private String itemID;
    private String name;
    private String description;
    private String price;
    private String quantity;
    private String reorderPoint;
    private String supplierID;

    public Item(String itemID, String name, String description, String price, String quantity, String reorderPoint, String supplierID) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.reorderPoint = reorderPoint;
        this.supplierID = supplierID;
    }

    public String getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getReorderPoint() {
        return reorderPoint;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public String getStockStatus() {
        try {
            int quantity = Integer.parseInt(this.quantity); // Ensure this.quantity is a String
            int reorderPoint = Integer.parseInt(this.reorderPoint); // Ensure this.reorderPoint is a String

            if (quantity < reorderPoint) {
                return "Danger";
            }
            if (quantity - reorderPoint < 20) {
                return "Warning";
            } else {
                return "Sufficient";
            }
        } catch (NumberFormatException e) {
            return "Invalid input: Please ensure quantity and reorder point are valid numbers.";
        }
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setReorderPoint(String reorderPoint) {
        this.reorderPoint = reorderPoint;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    @Override
    public String toString() {
        return itemID + "|" + name + "|" + price + "|" + quantity + "|" + supplierID;
    }
}
