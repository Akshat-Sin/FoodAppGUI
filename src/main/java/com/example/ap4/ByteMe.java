package com.example.ap4;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ByteMe {
    static HashMap<String, String> adminCredentials = new HashMap<>();
    static HashMap<String, Customer> customerCredentials = new HashMap<>();

    static OrderManager orderManager = new OrderManager();
    public static void main(String[] args) throws NonIntegerInputException{
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin(orderManager);
        setupDemo(orderManager);

        System.out.println("Welcome to Byte Me! Canteen Ordering System");

        while (true) {
            System.out.println("Select Role: 1) Admin 2) Customer 3) Exit");
            try {
                int choice = getUserOption(scanner);
                try {
                    switch (choice) {
                        case 1:
                            if (authenticateAdmin(scanner)) {
                                admin.adminInterface(scanner);
                            }
                            break;
                        case 2:
                            authenticateCustomer(scanner);

                            break;
                        case 3:
                            System.out.println("Exiting system. Have a good day!");
                            scanner.close();
                            return;
                        default:
                            System.out.println("Invalid option, please try again.");
                    }
                }
                catch(InvalidLoginException e){
                    System.out.println(e.getMessage());
                }
            }
            catch(NonIntegerInputException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void setupDemo(OrderManager orderManager) {
        // Simulate an admin login
        System.out.println("Setting up admin...");
        String adminUsername = "admin";
        String adminPassword = "admin123";
        adminCredentials.put(adminUsername,adminPassword);
        System.out.println("Admin logged in with username: " + adminUsername);

        // Add initial items to the menu
        System.out.println("Adding initial items to the menu...");

        OrderManager.addMenuItemDirect("Burger", 50, "Meals", 10);
        OrderManager.addMenuItemDirect("Pizza", 150, "Meals", 5);
        OrderManager.addMenuItemDirect("Cola", 60, "Beverages", 20);
        OrderManager.addMenuItemDirect("Fries",40, "Snacks", 15);

        System.out.println("Menu setup complete with initial items.");

        // Set up customers
        System.out.println("Setting up customers...");

        // VIP Customer
        Customer c1=new Customer("Akshat","aks@id.com","aks123",orderManager);
        customerCredentials.put("aks@id.com",c1);
        c1.setVIP(true);

        // Regular Customer 1
        Customer c2=new Customer("Jatin","jat@id.com","jat123",orderManager);
        customerCredentials.put("jat@id.com",c2);

        // Regular Customer 2
        Customer c3=new Customer("Ujjval","ud@id.com","ud123",orderManager);
        customerCredentials.put("ud@id.com",c3);

        System.out.println("Customer setup complete.");
        System.out.println("VIP Customer: Akshat");
        System.out.println("Regular Customer 1: Jatin");
        System.out.println("Regular Customer 2: Ujjval");
    }

    // Method to get user's option with exception handling
    public static int getUserOption(Scanner scanner) throws NonIntegerInputException {
        try {
            System.out.println("Choose an option (Enter an integer): ");
            return scanner.nextInt();  // Expect integer input from user
        } catch (InputMismatchException e) {
            scanner.next();  // Clear the invalid input
            throw new NonIntegerInputException("Invalid input! Please enter an integer.");
        }
    }

    private static boolean authenticateAdmin(Scanner scanner) throws InvalidLoginException, NonIntegerInputException {
        System.out.println("1) Login  2) Signup");
        int choice = getUserOption(scanner);

        if (choice == 1) {
            System.out.println("Enter Admin E-mail: ");
            scanner.nextLine();
            String email = scanner.nextLine();

            System.out.println("Enter Password: ");
            String password = scanner.nextLine();

            int status = admin_login(email, password);

            if (status == 1) {
                return true;
            }
            else {
                return false;
            }

        } else if (choice == 2) {
            System.out.println("Enter Admin E-mail: ");
            scanner.nextLine();
            String email = scanner.nextLine();
            System.out.println("Create Password: ");
            String password = scanner.nextLine();
            adminCredentials.put(email, password);
            System.out.println("Admin signup successful! You can now log in.");
            return false;
        }
        else {
            System.out.println("Invalid choice.");
            return false;
        }
    }

    public static int admin_login(String email, String password) {
        if (adminCredentials.containsKey(email) && adminCredentials.get(email).equals(password)) {
            System.out.println("Admin login successful!");
            return 1;
        } else {
            System.out.println("Invalid Credentials. Please try again.");
            return -1;
        }
    }

    private static void authenticateCustomer(Scanner scanner) throws NonIntegerInputException, InvalidLoginException {
        System.out.println("1) Login  2) Signup");
        int choice = getUserOption(scanner);

        if (choice == 1) {
            System.out.println("Enter Customer E-mail: ");
            scanner.nextLine();
            String email = scanner.nextLine();

            System.out.println("Enter Password: ");
            String password = scanner.nextLine();

            int status = customerLogin(email, password);

            if (status == 1) {
                customerCredentials.get(email).customerInterface(scanner);
            }


        } else if (choice == 2) {
            System.out.println("Enter Customer Name: ");
            scanner.nextLine();
            String name = scanner.nextLine();
            System.out.println("Enter Customer E-mail: ");
            String email = scanner.nextLine();
            System.out.println("Create Password: ");
            String password = scanner.nextLine();
            Customer customer = new Customer(name, email, password, orderManager);
            customerCredentials.put(email, customer);
            System.out.println("Customer signup successful! You can now log in.");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    static int customerLogin(String email, String password) {

        if (!customerCredentials.containsKey(email)) {
            System.out.println("You are not signed in. Please try again");
            return 0;
        }

        if (customerCredentials.get(email).getPassword().equals(password)) {
            System.out.println("Customer login successful!");
            return 1;
        } else {
            System.out.println("Invalid credentials. Please try again.");
            return -1;
        }
    }
}
