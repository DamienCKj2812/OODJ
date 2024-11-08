package javaapplication7;

import UI.Admin.UserManagementUI;
import UI.Authentication.LoginUI;
import UI.InventoryManager.InventoryManagerHomeUI;
import UI.PurchaseManager.PurchaseManagerHomeUI;
import java.io.IOException;
import models.Admin;
import models.User;
import utils.Authenticator;
import models.InventoryManager;
import models.SupplierAction;

public class JavaApplication7 {

    public static void main(String[] args) {

        // TODO code application logic here
        System.out.println("Hello harzixuan");
        System.out.println("wake up");

        Authenticator au = new Authenticator();
        SupplierAction sa = new SupplierAction();

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
                    new PurchaseManagerHomeUI().setVisible(true);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//       - Testing purpose
//        try {
//            User user = au.login("inventoryManagerUser", "inventoryManager123");
//
//            if (user instanceof Admin) {
//                Admin admin = (Admin) user; // Cast to Admin
////                admin.updateUser("tp1728738654707", "ben dover", "ben123", "salesManager");
////                admin.registerNewUser("Ben Dover", "ben123", "financeManager");
////                admin.deleteUser("tp1728757489896");
//                System.out.println((admin.getAllUsers()));
//            } else if (user instanceof InventoryManager) {
//                InventoryManager inventoryManager = (InventoryManager) user;
////                System.out.println(sa.getAllSupplier());
////                System.out.println(sa.removeSupplier("sp1729950430259"));
//                System.out.println(sa.addSupplier("asdasdasd", "asasdasdasdasddasd", "asdasdasdasd, Townsville, State, ZIP", "+1-555-234-56asd78", "Office asdasdasdSupplies"));
//
//            } else {
//                user.displayUserInfo();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
}
