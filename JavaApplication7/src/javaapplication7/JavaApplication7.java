package javaapplication7;

import UI.Admin.AdminHomeUI;
import UI.Admin.UserManagementUI;
import UI.Authentication.LoginUI;
import UI.InventoryManager.InventoryManagerHomeUI;
import java.io.IOException;
import models.Admin;
import models.User;
import utils.Authenticator;
import models.InventoryManager;
import models.PurchaseManager;
import models.SalesManager;
import models.SupplierAction;
import utils.LogHandler;

public class JavaApplication7 {

    public static void main(String[] args) {

        // TODO code application logic here
        System.out.println("Hello harzixuan");
        System.out.println("wake up");

        Authenticator au = new Authenticator();
        SupplierAction sa = new SupplierAction();
//                try {
//                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                        if ("Nimbus".equals(info.getName())) {
//                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                            break;
//                        }
//                    }
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//        
//                try {
//        
//                    User user = au.login("adminUser", "admin123");
//                    Admin admin = (Admin) user;
//        
//                    // Create and display the LoginUI
//                    java.awt.EventQueue.invokeLater(new Runnable() {
//                        public void run() {
////                            new PurchaseManagerHomeUI().setVisible(true);
//                        }
//                    });
//        
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }


//       - Testing purpose
        try {
            User user = au.login("salesManagerUser", "salesManager123");
            LogHandler lh = new LogHandler(user);

            if (user instanceof Admin) {
                Admin admin = (Admin) user; // Cast to Admin
//                admin.updateUser("tp1728738654707", "ben dover", "ben123", "salesManager");
//                admin.registerNewUser("Ben Dover", "ben123", "financeManager");
//                admin.deleteUser("tp1728757489896");
                System.out.println((admin.getAllUsers()));
            } else if (user instanceof InventoryManager) {
                InventoryManager inventoryManager = (InventoryManager) user;
//                System.out.println(sa.getAllSupplier());
//                System.out.println(sa.removeSupplier("sp1729950430259"));
                System.out.println(sa.addSupplier("asdasdasd", "asasdasdasdasddasd", "asdasdasdasd, Townsville, State, ZIP", "+1-555-234-56asd78", "Office asdasdasdSupplies"));

            } else if (user instanceof PurchaseManager) {
                PurchaseManager purchaseManager = (PurchaseManager) user;
//                System.out.println(purchaseManager.addPurchaseOrder("req002", "item1730020612897", "4", "1697856000", "sm1010", "Success", "user123123"));
                System.out.println(purchaseManager.getAllPurchaseOrders());

            } else if (user instanceof SalesManager) {
                SalesManager salesManager = (SalesManager) user;
                System.out.println(salesManager.removeSalesEntry("se1731550956308"));

            } else {
                user.displayUserInfo();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
