package com.example.ap4;

import java.io.Serializable;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Customer implements Customer_interface, Serializable {
    private static final long serialVersionUID = 1L;
    OrderManager orderManager;
    Map<MenuItem, Integer> cart = new HashMap<>();
    private boolean isVIP = false;
    private String email;
    private String name;
    private String password;

    public Customer(String name, String email, String password, OrderManager orderManager) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.orderManager=orderManager;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isVIP() {
        return isVIP;
    }

    @Override
    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }

    @Override
    public void customerInterface(Scanner scanner) throws NonIntegerInputException {
        boolean running = true;
        while (running) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1) Browse Menu");
            System.out.println("2) View Cart");
            System.out.println("3) Track Orders");
            System.out.println("4) Order History");
            System.out.println("5) Reviews");
            System.out.println("6) Become VIP");
            System.out.println("7) Exit Customer Interface");
            try {
                int choice = ByteMe.getUserOption(scanner);

                switch (choice) {
                    case 1:
                        browseMenu(scanner);
                        break;
                    case 2:
                        viewCart(scanner);
                        break;
                    case 3:
                        trackOrders(scanner);
                        break;
                    case 4:
                        viewOrderHistory(scanner);
                        break;
                    case 5:
                        manageReviews(scanner);
                        break;
                    case 6:
                        becomeVIP(scanner);
                        break;
                    case 7:
                        System.out.println("Exiting Customer Interface.");
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

    // 1. Browse Menu
    private void browseMenu(Scanner scanner) {
        boolean browsing = true;
        while (browsing) {
            System.out.println("\nBrowse Menu:");
            System.out.println("1) View All Items");
            System.out.println("2) Search by Name");
            System.out.println("3) Filter by Category");
            System.out.println("4) Sort by Price");
            System.out.println("5) Back to Customer Menu");
            try {
                int choice = ByteMe.getUserOption(scanner);

                switch (choice) {
                    case 1:
                        orderManager.displayMenu();
                        break;
                    case 2:
                        System.out.println("Enter item name to search:");
                        String name = scanner.nextLine();
                        orderManager.searchMenuByName(name);
                        break;
                    case 3:
                        orderManager.filterMenuByCategory(scanner); // Use category filter
                        break;
                    case 4:
                        System.out.println("1) Ascending 2) Descending");
                        int sortOrder = ByteMe.getUserOption(scanner);
                        orderManager.sortMenuByPrice(sortOrder == 1);
                        break;
                    case 5:
                        browsing = false;
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

    // 2. View Cart
    void viewCart(Scanner scanner) {
        boolean managingCart = true;
        while (managingCart) {
            System.out.println("\nYour Cart:");
            orderManager.displayCart(cart);

            System.out.println("1) Add Item");
            System.out.println("2) Modify Quantity");
            System.out.println("3) Remove Item");
            System.out.println("4) View Total Price");
            System.out.println("5) Checkout");
            System.out.println("6) Back to Customer Menu");
            try {
                int choice = ByteMe.getUserOption(scanner);

                switch (choice) {
                    case 1:
                        addItemToCart(scanner);
                        break;
                    case 2:
                        modifyItemQuantity(scanner);
                        break;
                    case 3:
                        removeItemFromCart(scanner);
                        break;
                    case 4:
                        orderManager.calculateTotalWithDelivery(cart, isVIP);
                        break;
                    case 5:
                        checkout(scanner);
                        break;
                    case 6:
                        managingCart = false;
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
    // Checkout Process: Collect payment and delivery details
    void checkout(Scanner scanner) throws NonIntegerInputException {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Add items before proceeding to checkout.");
            return;
        }
        try {
            int totalAmount = orderManager.calculateCartTotal(cart);
            int deliveryCharge = isVIP ? 0 : 5; // Fixed delivery charge for simplicity
            int finalAmount = totalAmount + deliveryCharge;

            System.out.println("Total Amount: Rs." + totalAmount);
            System.out.println("Delivery Charge: Rs." + deliveryCharge);
            System.out.println("Final Amount (including delivery): Rs." + finalAmount);

            System.out.println("Enter delivery address:");
            scanner.nextLine();
            String address = scanner.nextLine();

            System.out.println("Choose a payment method:");
            System.out.println("1) Credit Card");
            System.out.println("2) Debit Card");
            System.out.println("3) PayPal");
            int paymentChoice = ByteMe.getUserOption(scanner);

            String paymentMethod = "";
            String paymentDetails = "";
            switch (paymentChoice) {
                case 1:
                    paymentMethod = "Credit Card";
                    System.out.println("Enter Credit Card Number:");
                    scanner.nextLine();
                    paymentDetails = scanner.nextLine();
                    break;
                case 2:
                    paymentMethod = "Debit Card";
                    System.out.println("Enter Debit Card Number:");
                    scanner.nextLine();
                    paymentDetails = scanner.nextLine();
                    break;
                case 3:
                    paymentMethod = "PayPal";
                    System.out.println("Enter PayPal Email:");
                    scanner.nextLine();
                    paymentDetails = scanner.nextLine();
                    break;
                default:
                    System.out.println("Invalid payment method. Cancelling checkout.");
                    return;
            }

            System.out.println("Payment of Rs " +finalAmount+" successful via \n"+ paymentMethod);

            // Complete order only after successful checkout
            String customerType = isVIP ? "VIP" : "Regular";
            System.out.println("Enter any special requests (or press enter to skip):");
            String specialRequest = scanner.nextLine();

            // Create and add the order
            Order order = new Order(new HashMap<MenuItem, Integer>(cart), customerType, specialRequest, address, paymentMethod + ": " + paymentDetails,finalAmount);
            orderManager.addOrder(order);
            file.savePendOrder(OrderManager.getOrders());
        }
        catch (NonIntegerInputException e){
            System.out.println(e.getMessage());
        }

        cart.clear();
        System.out.println("Order placed successfully! You will receive your order at your address.");
    }

    void addItemToCart(Scanner scanner) throws NonIntegerInputException {
        orderManager.displayAvailableMenuItems();
        System.out.println("Enter item name to add:");
        String name = scanner.nextLine();

        addItem(name,scanner);
    }

    int addItem(String s,Scanner scanner) throws NonIntegerInputException {
        MenuItem item = orderManager.getMenuItem(s);

        if (item == null) {
            System.out.println("Item not found.");
            return -1;
        }

        System.out.println("Enter quantity:");

        int quantity = ByteMe.getUserOption(scanner);
        if (item.isAvailable(quantity)) {
            cart.put(item, cart.getOrDefault(item, 0) + quantity);

            item.decreaseAvailability(quantity);
            System.out.println("Item added to cart.");
            return 1;
        } else {
            System.out.println("Out of stock.");
            return 0;
        }

    }

    void modifyItemQuantity(Scanner scanner) throws NonIntegerInputException {
        displayCartItems();
        System.out.println("Enter item name to modify quantity:");
        String name = scanner.nextLine();
        MenuItem item = orderManager.getMenuItem(name);

        if (item != null && cart.containsKey(item)) {
            System.out.println("Enter new quantity:");
            int newQuantity = ByteMe.getUserOption(scanner);

            int currentQuantityInCart = cart.get(item);
            int additionalQuantityNeeded = newQuantity - currentQuantityInCart;

            if (item.isAvailable(additionalQuantityNeeded)) {
                cart.put(item, newQuantity);
                item.decreaseAvailability(additionalQuantityNeeded); // Update item stock
                System.out.println("Item quantity updated.");
            } else {
                System.out.println("Only " + item.getAvailability() + " pieces available.");
            }
        } else {
            System.out.println("Item not in cart.");
        }
    }

    void removeItemFromCart(Scanner scanner) {
        displayCartItems();
        System.out.println("Enter item name to remove:");
        String name = scanner.nextLine();
        MenuItem item = orderManager.getMenuItem(name);

        if (item != null && cart.containsKey(item)) {
            int quantityInCart = cart.remove(item);
            item.decreaseAvailability(-quantityInCart); // Re-stock the removed quantity
            System.out.println("Item removed from cart.");
        } else {
            System.out.println("Item not in cart.");
        }
    }
    // Helper method to display items in the cart
    private void displayCartItems() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Items in your cart:");
            cart.forEach((item, quantity) -> System.out.println(item.getName() + " x" + quantity + " (Available: " + item.getAvailability() + ")"));
        }
    }


    // 4. Track Orders
    private void trackOrders(Scanner scanner) throws NonIntegerInputException {
        boolean tracking = true;
        while (tracking) {
            System.out.println("\nOrder Tracking:");
            System.out.println("1) View Order Status");
            System.out.println("2) Cancel Order");
            System.out.println("3) Back to Customer Menu");
            try {
                int choice = ByteMe.getUserOption(scanner);

                switch (choice) {
                    case 1:
                        orderManager.viewOrderStatus();
                        break;
                    case 2:
                        List<Order> pendingOrders = orderManager.getPendingOrders();
                        for(Order o:pendingOrders){
                            System.out.println(o);
                        }
                        System.out.println("Enter Order ID to cancel:");
                        int orderId = ByteMe.getUserOption(scanner);
                        orderManager.cancelOrder(orderId);
                        break;
                    case 3:
                        tracking = false;
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

    // 5. View Order History
    private void viewOrderHistory(Scanner scanner) throws NonIntegerInputException {
        boolean historyView = true;
        while (historyView) {
            System.out.println("\nOrder History:");
            System.out.println("1) View Past Orders");
            System.out.println("2) Re-order from Past Order");
            System.out.println("3) Back to Customer Menu");
            try {
                int choice = ByteMe.getUserOption(scanner);

                switch (choice) {
                    case 1:
                        orderManager.viewOrderHistory();
                        break;
                    case 2:
                        List<Order> orders = orderManager.getCompletedOrders();
                        for(Order o:orders){
                            System.out.println(o);
                        }
                        System.out.println("Enter Order ID to reorder:");
                        int orderId = ByteMe.getUserOption(scanner);
                        orderManager.reorder(orderId, cart, scanner); // Add items to cart based on availability

                        // Show the original order details alongside the current cart
                        System.out.println("\nOriginal Order Details:");
                        orderManager.viewOrderHistory();

                        System.out.println("\nPlease review your cart and adjust items if necessary.");
                        viewCart(scanner); // Allow the customer to review and modify the cart
                        break;
                    case 3:
                        historyView = false;
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

    // 6. Reviews
    private void manageReviews(Scanner scanner) throws NonIntegerInputException {
        boolean managingReviews = true;
        while (managingReviews) {
            System.out.println("\nManage Reviews:");
            System.out.println("1) View Item Reviews");
            System.out.println("2) Leave a Review");
            System.out.println("3) Back to Customer Menu");
            try {
                int choice = ByteMe.getUserOption(scanner);

                switch (choice) {
                    case 1:
                        viewItemReviews(scanner);
                        break;
                    case 2:
                        leaveReview(scanner);
                    case 3:
                        managingReviews = false;
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
    void becomeVIP(Scanner scanner) {
        if(this.isVIP){
            System.out.println("You are already a VIP");
            return;
        }
        System.out.println("To become a VIP, you need to pay Rs.100.");
        System.out.println("Do you want to proceed? (yes/no)");
        String response = scanner.nextLine();

        if (response.equals("yes")) {
            System.out.println("Payment received. You are now a VIP customer!");
            this.isVIP = true;
        } else {
            System.out.println("Upgrade to VIP canceled.");
        }
    }
    // Method to leave a review for an item the customer has ordered
    void leaveReview(Scanner scanner) throws NonIntegerInputException {
        orderManager.displayMenu();
        System.out.println("Enter the name of the item you want to review:");
        String itemName = scanner.nextLine();

        // Verify that the customer has ordered this item in completed orders
        if (hasOrderedItem(itemName)) {
            System.out.println("Enter your review text for " + itemName + ":");
            String reviewText = scanner.nextLine();
            System.out.println("Enter a rating (1 to 10) for " + itemName + ":");
            int rating = ByteMe.getUserOption(scanner);

            // Add the review with customer's name and rating
            orderManager.addReview(itemName, reviewText, rating);
        } else {
            System.out.println("You can only review items you have previously ordered.");
        }
    }

    // Check if the customer has ordered an item before
    private boolean hasOrderedItem(String itemName) {
        for (Order order : orderManager.getCompletedOrders()) { // Assuming completedOrders is accessible
            for (MenuItem item : order.getItems().keySet()) { // Loop through each item in the order
                if (item.getName().equals(itemName)) { // Compare by item name
                    return true;
                }
            }
        }
        return false;
    }

    // Method to view reviews for a specific item
    void viewItemReviews(Scanner scanner) {
        orderManager.displayMenu();
        System.out.println("Enter the name of the item to view reviews:");
        String itemName = scanner.nextLine();
        orderManager.viewReviews(itemName); // View reviews for the specified item
    }

}
