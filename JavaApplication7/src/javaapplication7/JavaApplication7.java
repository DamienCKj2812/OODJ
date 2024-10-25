package javaapplication7;

import UI.Admin.UserManagementUI;
import UI.Authentication.LoginUI;
import UI.InventoryManager.InventoryManagerHomeUI;
import java.io.IOException;
import models.Admin;
import models.User;
import utils.Authenticator;
import models.InventoryManager;

public class JavaApplication7 {

    public static void main(String[] args) {
        Authenticator au = new Authenticator();

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {

            User user = au.login("adminUser", "admin123");
            Admin admin = (Admin) user;

            // Create and display the LoginUI
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new InventoryManagerHomeUI(admin).setVisible(true);
                }
            });

           admin.getAllUsersBesidesMe();
        } catch (IOException e) {
            e.printStackTrace();
        }

////       - Testing purpose
//        try {
//            User user = au.login("inventoryManagerUser", "inventoryManager123");
//
//            if (user instanceof Admin) {
//                Admin admin = (Admin) user; // Cast to Admin
//                admin.updateUser("tp1728738654707", "ben dover", "ben123", "salesManager");
//                admin.registerNewUser("Ben Dover", "ben123", "financeManager");
//                admin.deleteUser("tp1728757489896");
//                System.out.println((admin.getAllUsers()));
//            } else if (user instanceof InventoryManager) {
//                InventoryManager inventoryManager = (InventoryManager) user;
//                System.out.println((inventoryManager.getInventoryItems()));
//                System.out.println(inventoryManager.getItem("item5623405430000"));
//                inventoryManager.updateItem("item6542054300001", "Ligma Balls", "69.00", "420", "supplier0234054300001");
//                System.out.println(inventoryManager.addItem("Freezer", "150.00", "200", "supplier0234054300001"));
//                System.out.println(inventoryManager.deleteItem("item1728755935837"));
//            } else {
//                user.displayUserInfo();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        }
    }
