package utils;

import java.io.IOException;
import models.User;
import constants.Constants; // Import the Constants class for the file path
import models.Admin;
import models.FinanceManager;
import models.InventoryManager;
import models.PurchaseManager;
import models.SalesManager;

public class Authenticator {

    private FileManager fileManager = new FileManager(Constants.USER_DATA_PATH);

    public User login(String inputUsername, String inputPassword) throws IOException {
        String fileContent = fileManager.readFile();
        String[] lines = fileContent.split("\n");

        for (String line : lines) {
            String[] userData = line.split("\\|");
            if (userData.length == 5) {
                String userID = userData[0];
                String username = userData[1];
                String password = userData[2];
                String role = userData[3];
                String status = userData[4].trim();

                if (username.equals(inputUsername) && password.equals(inputPassword)) {
                    System.out.println("Login successful! User role: " + role);

                    // Check the role and return the corresponding object
                    if (role.equalsIgnoreCase("Admin")) {
                        return new Admin(userID, username, password); // Return Admin object
                    } else if (role.equalsIgnoreCase("inventoryManager")) {
                        return new InventoryManager(userID, username, password); // Return InventoryManager object
                    } else if (role.equalsIgnoreCase("purchaseManager")) {
                        return new PurchaseManager(userID, username, password); // Return PurchaseManager object
                    } else if (role.equalsIgnoreCase("salesManager")) {
                        return new SalesManager(userID, username, password); // Return SalesManager object
                    } else if (role.equalsIgnoreCase("financeManager")) {
                        return new FinanceManager(userID, username, password); // Return SalesManager object
                    } else {
                        return new User(userID, username, password, role, status); // Return User object
                    }
                }
            }
        }

        System.out.println("User not found or password incorrect.");
        return null; // Or throw a custom exception for more detailed error handling
    }

}
