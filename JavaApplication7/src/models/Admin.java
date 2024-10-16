/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import constants.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

    public List<User> getAllUsers() throws IOException {
        String fileContent = fileManager.readFile();
        String[] userLines = fileContent.split("\n");
        List<User> users = new ArrayList<>();

        for (String line : userLines) {
            String[] userDetails = line.split("\\|");
            if (userDetails.length >= 5) { // Check for the required number of details
                User user = new User(userDetails[0], userDetails[1], userDetails[2], userDetails[3], userDetails[4]);
                users.add(user);
            } else {
                System.err.println("Skipping line due to incorrect format: " + line);
            }
        }

        return users;
    }

    public List<User> getAllUsersBesidesMe() throws IOException {
        List<User> users = getAllUsers();
        String currentUserID = this.getUserID(); // Get the userID of the current Admin

        // Use an iterator to remove the current user from the list
        users.removeIf(user -> user.getUserID().equals(currentUserID));

        return users;
    }

    public User findUser(String userID) throws IOException {
        List<User> users = getAllUsers(); // Retrieve all users

        // Check for existing user ID
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                System.out.println("User ID found: " + userID);
                return user; // User with the specified ID found
            }
        }

        // If no user is found, throw NoSuchElementException
        throw new NoSuchElementException("User ID not found: " + userID);
    }

    public User registerNewUser(String username, String password, String role) throws IOException {
        List<User> users = getAllUsers(); // Retrieve all users

        // Check for existing username
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists.");
                return null; // Username already taken
            }
        }

        // Generate a unique userID using the current time in milliseconds
        String userID = "tp" + System.currentTimeMillis();

        // Create a new User object
        User newUser = new User(userID, username, password, role);
        users.add(newUser); // Add the new user to the list

        // Write the updated user list back to the file
        StringBuilder updatedUsers = new StringBuilder();
        for (User user : users) {
            updatedUsers.append(user.getUserID()).append("|")
                    .append(user.getUsername()).append("|")
                    .append(user.getPassword()).append("|")
                    .append(user.getRole()).append("|")
                    .append(user.getStatus()).append("\n");
        }
        fileManager.writeFile(updatedUsers.toString().trim()); // Ensures no trailing newline

        System.out.println("Registration successful!");
        return newUser;
    }

    public boolean deleteUser(String userID) throws IOException {
        List<User> users = getAllUsers(); // Retrieve all users
        boolean userFound = false;

        // Iterate and remove user with matching userID
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                users.remove(user);
                userFound = true;
                break;
            }
        }

        if (userFound) {
            // Rebuild the user data for saving
            StringBuilder updatedUsers = new StringBuilder();
            for (User user : users) {
                updatedUsers.append(user.getUserID()).append("|")
                        .append(user.getUsername()).append("|")
                        .append(user.getPassword()).append("|")
                        .append(user.getRole()).append("|")
                        .append(user.getStatus()).append("\n");
            }
            fileManager.writeFile(updatedUsers.toString().trim()); // Write to file

            System.out.println("User deleted successfully!");
            return true;
        } else {
            System.out.println("User not found: " + userID);
            throw new IllegalArgumentException("User with ID " + userID + " not found.");
        }
    }

    public boolean updateUser(String userID, String newUsername, String newPassword, String newRole, String status) throws IOException {
        List<User> users = getAllUsers(); // Retrieve all users
        boolean userFound = false;

        // Iterate over users to find and update the user with the matching userID
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                // Update user details
                user.setUsername(newUsername);
                user.setPassword(newPassword);
                user.setRole(newRole);
                user.setStatus(status);
                userFound = true;
                break;
            }
        }

        if (userFound) {
            // Rebuild and save the updated user list to the file
            StringBuilder updatedUsers = new StringBuilder();
            for (User user : users) {
                updatedUsers.append(user.getUserID()).append("|")
                        .append(user.getUsername()).append("|")
                        .append(user.getPassword()).append("|")
                        .append(user.getRole()).append("|")
                        .append(user.getStatus()).append("\n");
            }
            fileManager.writeFile(updatedUsers.toString().trim());

            System.out.println("User updated successfully!");
            return true;
        } else {
            System.out.println("User not found: " + userID);
            return false; // User not found
        }
    }

}
