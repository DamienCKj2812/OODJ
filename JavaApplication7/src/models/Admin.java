/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import constants.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.io.IOException;
import utils.FileManager;

/**
 *
 * @author Aorus
 */
public class Admin extends User {

    private FileManager fileManager = new FileManager(Constants.USER_DATA_PATH);

    public Admin(String userID, String username, String password) {
        super(userID, username, password, "admin");
    }

    public String[] getAllUsers() throws IOException {
        String fileContent = fileManager.readFile();
        return fileContent.split("\n");
    }

    public String[] getAllUsersBasidesMe() throws IOException {
        String fileContent = fileManager.readFile();
        String[] allUsers = fileContent.split("\n");
        String currentUserID = this.getUserID(); // Get the userID of the current Admin

        return Arrays.stream(allUsers) // Stream the array of users
                .filter(line -> {
                    String[] userData = line.split("\\|");
                    return userData.length > 0 && !userData[0].equals(currentUserID); // Filter out the current user
                })
                .toArray(String[]::new); // Collect the filtered results back into an array
    }

    public User registerNewUser(String username, String password, String role) throws IOException {
        // Read file content to check for existing username
        String[] users = getAllUsers();
        StringBuilder updatedUsers = new StringBuilder();

        // Check for existing username
        for (String line : users) {
            String[] userData = line.split("\\|");
            if (userData.length >= 2 && userData[1].equals(username)) {
                System.out.println("Username already exists.");
                return null; // Username already taken
            }
            updatedUsers.append(line).append("\n"); // Add existing users to updated list
        }

        // Generate a unique userID using the current time in milliseconds
        String userID = "tp" + System.currentTimeMillis();

        String userData = String.format("%s|%s|%s|%s", userID, username, password, role);
        updatedUsers.append(userData); // Add new user to the updated list

        // Write the updated user list back to the file
        fileManager.writeFile(updatedUsers.toString().trim()); // Ensures no trailing newline

        System.out.println("Registration successful!");
        return new User(userID, username, password, role); // Return the new User object
    }

    public boolean deleteUser(String userID) throws IOException {
        String[] users = getAllUsers();
        StringBuilder updatedUsers = new StringBuilder();

        boolean userFound = false;

        for (String line : users) {
            String[] userData = line.split("\\|");
            if (userData[0].equals(userID)) {
                userFound = true; // User to be deleted is found
                continue; // Skip this user
            }
            updatedUsers.append(line).append("\n"); // Keep other users
        }

        if (userFound) {
            System.out.println("Updated users list: \n" + updatedUsers.toString().trim());
            // Write the updated user list back to the file
            fileManager.writeFile(updatedUsers.toString().trim());
            System.out.println("User deleted successfully!");
            return true; // Deletion successful
        } else {
            // Inform the user and throw an exception
            System.out.println("User not found: " + userID);
            throw new IllegalArgumentException("User with ID " + userID + " not found.");
        }
    }

    public boolean updateUser(String userID, String newUsername, String newPassword, String newRole) throws IOException {
        String[] users = getAllUsers();
        StringBuilder updatedUsers = new StringBuilder();
        boolean userFound = false;

        for (String line : users) {
            String[] userData = line.split("\\|");
            if (userData[0].equals(userID)) {
                // Update the user's information
                line = String.format("%s|%s|%s|%s", userID, newUsername, newPassword, newRole);
                userFound = true; // User to be updated is found
            }
            updatedUsers.append(line).append("\n"); // Keep this user (updated or not)
        }

        if (userFound) {
            // Write the updated user list back to the file
            fileManager.writeFile(updatedUsers.toString().trim());
            System.out.println("User updated successfully!");
            return true; // Update successful
        } else {
            System.out.println("User not found.");
            return false; // User not found
        }
    }
}
