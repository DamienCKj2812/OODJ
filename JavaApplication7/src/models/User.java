package models;

public class User {

    private String userID;
    private String username;
    private String password;
    private String role;
    private String status;

    public User(String userID, String username, String password, String role) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String userID, String username, String password, String role, String status) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void displayUserInfo() {
        System.out.println("User ID: " + userID);
        System.out.println("Username: " + username);
        System.out.println("Role: " + role);
        System.out.println("Status: " + status);

    }

    @Override
    public String toString() {
        return userID + "|" + username + "|" + password + "|" + role + "|" + status;
    }
}
