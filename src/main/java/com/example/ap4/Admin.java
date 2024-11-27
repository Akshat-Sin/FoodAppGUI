package com.example.ap4;

import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Admin implements Admin_interface {
    private static OrderManager orderManager=new OrderManager();

    public Admin(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    @Override
    public void adminInterface(Scanner scanner) throws NonIntegerInputException {
        boolean running = true;
        while (running) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1) Manage Menu");
            System.out.println("2) View Orders");
            System.out.println("3) Update Next Order Status");
            System.out.println("4) Process Refunds");
            System.out.println("5) Generate Reports");
            System.out.println("6) Exit Admin Interface");
            try {
                int choice = ByteMe.getUserOption(scanner);

                switch (choice) {
                    case 1:
                        manageMenu(scanner);
                        break;
                    case 2:
                        orderManager.displayPendingOrders();
                        break;
                    case 3:
                        orderManager.updateNextOrderStatus();
                        break;
                    case 4:
                        processRefunds(scanner);
                        break;
                    case 5:
                        generateReports();
                        break;
                    case 6:
                        System.out.println("Exiting Admin Interface.");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }
            catch(NonIntegerInputException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void manageMenu(Scanner scanner) throws NonIntegerInputException {
        boolean menuRunning = true;
        while (menuRunning) {
            System.out.println("\nManage Menu:");
            System.out.println("1) Add New Item");
            System.out.println("2) Update Existing Item");
            System.out.println("3) Remove Item");
            System.out.println("4) Back to Admin Menu");
            try {
                int choice = ByteMe.getUserOption(scanner);

                switch (choice) {
                    case 1:
                        orderManager.addMenuItem(scanner);
                        break;
                    case 2:
                        updateMenuItem(scanner);
                        break;
                    case 3:
                        orderManager.removeMenuItem(scanner);
                        break;
                    case 4:
                        menuRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }
            catch (NonIntegerInputException e){
                System.out.println(e.getMessage());
            }
        }
    }



    static void updateMenuItem(Scanner scanner) throws NonIntegerInputException {
        orderManager.displayMenu();
        System.out.println("Enter item name to update:");
        String name = scanner.nextLine();
        MenuItem item = orderManager.getMenuItem(name);
        try {
            if (item != null) {
                System.out.println("Enter new price:");
                int price = ByteMe.getUserOption(scanner);
                System.out.println("Enter new category:");
                String category = scanner.nextLine();
                System.out.println("Enter quantity of item:");
                int available = ByteMe.getUserOption(scanner);

                item.setPrice(price);
                item.setCategory(category);
                item.setAvailability(available);
                System.out.println("Item updated successfully!");
            } else {
                System.out.println("Item not found.");
            }
        }
        catch(NonIntegerInputException e){
            System.out.println(e.getMessage());
        }
    }







    static void processRefunds(Scanner scanner) throws NonIntegerInputException {
        orderManager.displayPendingOrders();
        System.out.println("Enter order ID to process refund:");
        try {
            int orderId = ByteMe.getUserOption(scanner);

            orderManager.processRefund(orderId);

        }
        catch(NonIntegerInputException e){
            System.out.println(e.getMessage());
        }
    }

    public static void generateReports() {
        System.out.println("Generating daily report...");
        String report = orderManager.generateDailyReport();
        System.out.println(report);
    }
}
