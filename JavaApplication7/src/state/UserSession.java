package state;

import models.Admin;
import models.InventoryManager;

public class UserSession {

    private static UserSession instance;
    private Admin loggedInAdmin; // Hold reference to Admin instance
    private InventoryManager inventoryManager;

    // Private constructor to prevent instantiation
    private UserSession() {
    }

    // Static method to get the instance
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    // Method to set the logged-in Admin
    public void setLoggedInAdmin(Admin admin) {
        this.loggedInAdmin = admin;
    }

    // Method to get the logged-in Admin
    public Admin getLoggedInAdmin() {
        return loggedInAdmin;
    }

    // Method to set the logged-in InventoryManager
    public void setLoggedInInventoryManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    // Method to get the logged-in InventoryManager
    public InventoryManager getLoggedInInventoryManager() {
        return inventoryManager;
    }
}
