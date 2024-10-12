package javaapplication7;

import java.io.IOException;
import models.Admin;
import models.User;
import utils.Authenticator;
import java.util.Arrays;

public class JavaApplication7 {

    public static void main(String[] args) {
        Authenticator au = new Authenticator();

        try {
            User user = au.login("adminUser", "admin123");

            if (user instanceof Admin) {
                Admin adminUser = (Admin) user; // Cast to Admin
//                adminUser.updateUser("tp1264557260195", "salesManagerUser", "salesManager123", "salesManager");
//                adminUser.registerNewUser("kj", "kj123", "admin");
//                adminUser.deleteUser("tp1728735210773");
//                System.out.println(Arrays.toString(adminUser.getAllUsers()));
            } else {
                user.displayUserInfo();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
