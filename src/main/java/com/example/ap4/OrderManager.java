package com.example.ap4;

import java.io.Serializable;
import java.util.*;

public class OrderManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Map<String, MenuItem> menu = new HashMap<>();
    private List<Order> completedOrders = new ArrayList<>(); // Store completed or canceled orders
    private List<Order> deniedOrders = new ArrayList<>(); // Store denied orders separately
    private static PriorityQueue<Order> orders = new PriorityQueue<>(); // PriorityQueue
    private Map<String, List<Review>> reviews = new HashMap<>();


    // Get item from menu
    public MenuItem getMenuItem(String name) {
        return menu.get(name);
    }

    public static Map<String, MenuItem> getMenu() {
        return menu;
    }

    public static PriorityQueue<Order> getOrders() {
        return orders;
    }

    // Retrieve pending orders
    public List<Order> getPendingOrders() {
        List<Order> pendingOrders = new ArrayList<>();
        for (Order order : orders) {
            if ("Preparing".equals(order.getStatus())) {
                pendingOrders.add(order);
            }
        }
        return pendingOrders;
    }

    public List<Order> getCompletedOrders() {
        return completedOrders;
    }

    // Update the status of a specific order
    public boolean updateOrderStatus(int orderId, String newStatus) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                order.setStatus(newStatus);
                return true;
            }
        }
        return false;
    }

    // Process a refund for a specific order
    public void processRefund(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                if (order.processRefund()) {
                    completedOrders.add(order);
                    orders.remove(order);
                    System.out.println("Refund successful for Order ID " + orderId);
                } else {
                    System.out.println("Refund cannot be processed again for Order ID " + orderId);
                }
                return;
            }
        }
        System.out.println("Order ID " + orderId + " not found.");
    }

    // Generate daily sales report
    public String generateDailyReport() {
        double totalSales = 0;
        Map<String, Integer> itemCounts = new HashMap<>();
        int totalOrders = orders.size();

        for (Order order : completedOrders) {
            if ("Delivered".equals(order.getStatus())) {
                for (Map.Entry<MenuItem, Integer> entry : order.getItems().entrySet()) {
                    MenuItem item = entry.getKey();
                    int quantity = entry.getValue();
                    totalSales += item.getPrice() * quantity;
                    itemCounts.put(item.getName(), itemCounts.getOrDefault(item.getName(), 0) + quantity);
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Daily Sales Report:\n");
        report.append("Total Sales: Rs.").append(totalSales).append("\n");
        report.append("Total Orders: ").append(totalOrders).append("\n");
        report.append("Most Popular Items:\n");
        itemCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> report.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n"));

        return report.toString();
    }


    // Search for items containing a substring in their name and display details for each
    public void searchMenuByName(String searchString) {
        System.out.println("\nSearch Results for \"" + searchString + "\":");
        List<MenuItem> matchingItems = new ArrayList<>();
        for (MenuItem item : menu.values()) {
            if (item.getName().toLowerCase().contains(searchString.toLowerCase())) {
                System.out.println(item);
                matchingItems.add(item);
            }
        }
        if (matchingItems.isEmpty()) {
            System.out.println("No items match your search.");
        }
    }

    // Filter and display menu items by category chosen by number
    public void filterMenuByCategory(Scanner scanner) throws NonIntegerInputException {
        String category = selectCategory(scanner); // Get valid category choice as String

        System.out.println("\nItems in Category: " + category);
        menu.values().stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .forEach(System.out::println);
    }

    // Sort menu by price
    public void sortMenuByPrice(boolean ascending) {
        System.out.println("\nMenu Items Sorted by Price (" + (ascending ? "Ascending" : "Descending") + "):");

        List<MenuItem> sortedItems = new ArrayList<>(menu.values());
        if (ascending) {
            sortedItems.sort(Comparator.comparingDouble(MenuItem::getPrice));
        } else {
            sortedItems.sort(Comparator.comparingDouble(MenuItem::getPrice).reversed());
        }

        sortedItems.forEach(System.out::println);
    }

    // Display the items in the cart
    public void displayCart(Map<MenuItem, Integer> cart) {
        cart.forEach((item, quantity) -> System.out.println(item.getName() + " x" + quantity));
    }



    // Add a new item to the menu with a specified category selected by number
    public void addMenuItem(Scanner scanner) throws NonIntegerInputException {
        try {
            System.out.println("Enter item name:");
            String name = scanner.nextLine();
            System.out.println("Enter item price:");
            int price = ByteMe.getUserOption(scanner);

            // Select category by number
            String category = selectCategory(scanner);

            System.out.println("Enter availability (number of pieces):");
            int availability = ByteMe.getUserOption(scanner);

            MenuItem newItem = new MenuItem(name, price, category, availability);
            menu.put(newItem.getName(), newItem);
            System.out.println("New item added successfully!");
        }
        catch(NonIntegerInputException e){
            System.out.println(e.getMessage());
        }
    }



    public int calculateTotalWithDelivery(Map<MenuItem, Integer> cart, boolean isVIP) {
        int total = calculateCartTotal(cart);
        int deliveryCharge = isVIP ? 0 : 5; // No delivery charge for VIPs

        System.out.println("Total Price: Rs." + total);
        System.out.println("Delivery Charge: Rs." + (isVIP ? 0 : 5));
        System.out.println("Final Amount: Rs." + (total + deliveryCharge));
        return total + deliveryCharge;
    }

    public int calculateCartTotal(Map<MenuItem, Integer> cart) {
        return (int) cart.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
                .sum();
    }

    // Display the full menu
    public void displayMenu() {
        System.out.println("\nAvailable Menu Items:");
        menu.values().forEach(System.out::println);
    }

    // Add a new order to the priority queue
    public void addOrder(Order order) {
        orders.add(order);
        System.out.println("Order added. Total orders: " + orders.size());
    }

    // Display pending orders with priority: VIP orders first
    public void displayPendingOrders() {
        System.out.println("\nPending Orders:");
        for (Order order : orders) {
            if (!order.getStatus().equals("Delivered")) {
                System.out.println(order);
                order.displayItemsInOrder();
                System.out.println();
            }

        }
    }

    // Update the next order status in the queue based on priority
    public void updateNextOrderStatus() {
        if(orders.isEmpty()){
            System.out.println("All orders are up to date.");
        }
        while (!orders.isEmpty()) {
            Order nextOrder = orders.peek(); // Check the next order

            if (nextOrder.getStatus().equals("Denied")) {
                orders.poll();
                deniedOrders.add(nextOrder);
                continue;
            }
            if (nextOrder.getStatus().equals("Refunded")) {
                orders.poll(); //
                continue;
            }

            // Update status for orders that are not denied
            nextOrder.updateStatusToNext();
            System.out.println("Updated Order ID " + nextOrder.getOrderId() + " to status: " + nextOrder.getStatus());

            // Move order to completedOrders if fully delivered
            if (nextOrder.getStatus().equals("Delivered")) {
                orders.poll();
                completedOrders.add(nextOrder);
            }
            break; // Only update the next order in line
        }
    }
    // Method to display only items with more than 0 pieces available
    public void displayAvailableMenuItems() {
        System.out.println("\nAvailable Menu Items:");
        menu.values().stream()
                .filter(item -> item.getAvailability() > 0)
                .forEach(System.out::println);
    }

    // View status of all current orders for a customer
    public void viewOrderStatus() {
        System.out.println("\nCurrent Orders:");
        for (Order order : orders) {
            System.out.println(order);
            order.displayItemsInOrder();
            System.out.println();
        }
    }

    // Cancel a specific order
    public void cancelOrder(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId && order.getStatus().equals("Preparing")) {
                order.cancelOrder(this);
                processRefund(orderId);
                System.out.println("Order ID " + orderId + " has been canceled.");
                return;
            }
        }
        System.out.println("Order cannot be canceled, either it does not exist or is already processed.");
    }

    // Add completed orders to history after delivery
    public void markOrderAsCompleted(Order order) {
        orders.remove(order);
        completedOrders.add(order); // Move to history
    }



    // Reorder a past order by adding items to the customer's cart based on availability
    public void reorder(int orderId, Map<MenuItem, Integer> cart, Scanner scanner) {
        for (Order pastOrder : completedOrders) {
            if (pastOrder.getOrderId() == orderId) {
                System.out.println("Reordering items from Order ID " + orderId + "...");

                // Display original order details
                pastOrder.displayItemsInOrder();

                // Add items to cart based on availability
                for (Map.Entry<MenuItem, Integer> entry : pastOrder.getItems().entrySet()) {
                    MenuItem item = entry.getKey();
                    int quantityOrdered = entry.getValue();
                    int availableQuantity = item.getAvailability();

                    // Check if the item is available in sufficient quantity
                    if (availableQuantity > 0 ) {
                        if(quantityOrdered>availableQuantity){
                            System.out.println("Quantity ordered of the item: "+ item.getName()+" is not sufficiently available in the stock.");
                            continue;
                        }
                        cart.put(item, cart.getOrDefault(item, 0) + quantityOrdered);
                        item.decreaseAvailability(quantityOrdered);

                    } else {
                        System.out.println("Item " + item.getName() + " is currently out of stock.");
                    }
                }

                System.out.println("Items from the previous order have been added to your cart based on availability.");
                System.out.println("Now you need to checkout and place your order.");
                return;
            }
        }
        System.out.println("Order ID not found in history.");
    }


    // Method to remove item from menu and update orders containing that item
    public void removeMenuItem(Scanner scanner) {
        displayMenu();
        System.out.println("Enter the name of the item you want to remove:");
        String name = scanner.nextLine();
        MenuItem removedItem = menu.get(name);
        menu.remove(name);

        if (removedItem != null) {
            System.out.println("Item removed from menu. Updating all related active orders...");
            denyActiveOrdersContainingItem(removedItem);
        } else {
            System.out.println("Item not found in the menu.");
        }
    }

    // Helper method to select category with input validation
    private String selectCategory(Scanner scanner) throws NonIntegerInputException {
        int choice;
        while (true) {
            System.out.println("Select category:");
            System.out.println("1) Snacks");
            System.out.println("2) Beverages");
            System.out.println("3) Meals");
            try {
                choice = ByteMe.getUserOption(scanner);

            switch (choice) {
                case 1:
                    return "Snacks";
                case 2:
                    return "Beverages";
                case 3:
                    return "Meals";
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 3.");
            }
            }
            catch (NonIntegerInputException e){
                System.out.println(e.getMessage());
            }
        }
    }

    // Display completed, denied, and canceled orders with item details
    public void viewOrderHistory() {
        System.out.println("\nOrder History:");

        // Display completed orders
        System.out.println("\nCompleted Orders:");
        for (Order order : completedOrders) {
            if (order.getStatus().equals("Delivered")) {
                System.out.println(order);
                order.displayItemsInOrder();
            }
        }

        System.out.println("\nRefunded Orders:");
        for (Order order : completedOrders) {
            if (order.getStatus().equals("Refunded")) {
                System.out.println(order);
                order.displayItemsInOrder();
            }
        }
        

        // Display denied orders
        System.out.println("\nDenied Orders:");
        for (Order order : deniedOrders) {
            System.out.println(order);
            order.displayItemsInOrder();
        }
    }
    // Update only active orders containing the removed item to "Denied"
    private void denyActiveOrdersContainingItem(MenuItem removedItem) {
        System.out.println(orders.size());
        for(Order o:orders) {

            if (o.getItems().containsKey(removedItem) && o.getStatus().equals("Preparing")) {
                Map<MenuItem, Integer> itemsToRestore = new HashMap<>();

                for (Map.Entry<MenuItem, Integer> entry : o.getItems().entrySet()) {
                    MenuItem item = entry.getKey();
                    if (!item.equals(removedItem)) { // Only restore unaffected items
                        itemsToRestore.put(item, entry.getValue());
                    }
                }
                o.setStatus("Denied");
                restoreItemsToStock(itemsToRestore); // Restore unaffected items to stock
                deniedOrders.add(o); // Move the o to denied list
                orders.remove(o);
                System.out.println("Order ID " + o.getOrderId() + " denied due to unavailable item.");

            }
        }
    }

    // Restore items in an order back to the menu stock
    public void restoreItemsToStock(Map<MenuItem, Integer> items) {
        for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
            MenuItem item = entry.getKey();
            int quantityToAdd = entry.getValue();
            item.decreaseAvailability(-quantityToAdd); // Update availability in the menu
        }
    }
    // Method to add a review for a specific item
    public void addReview(String itemName, String reviewText, int rating) {
        if (!menu.containsKey(itemName)) {
            System.out.println("Item not found in the menu.");
            return;
        }

        // Create a new Review object and add it to the item's review list
        Review review = new Review(reviewText, rating);
        reviews.putIfAbsent(itemName, new ArrayList<>());
        reviews.get(itemName).add(review);
        System.out.println("Review added for item: " + itemName);
    }

    // Method to view all reviews for a specific item
    public void viewReviews(String itemName) {
        if (!menu.containsKey(itemName)) {
            System.out.println("Item not found in the menu.");
            return;
        }

        List<Review> itemReviews = reviews.get(itemName);
        if (itemReviews == null || itemReviews.isEmpty()) {
            System.out.println("No reviews available for this item.");
        } else {
            System.out.println("Reviews for " + itemName + ":");
            for (Review review : itemReviews) {
                System.out.println("- " + review);
            }
        }
    }
    // Add items to the menu directly (helper method)
    public static void addMenuItemDirect(String name, int price, String category, int availability) {
        MenuItem newItem = new MenuItem(name, price, category, availability);
        menu.put(name, newItem);
    }


}
