# ByteMe Online Food ordering Application GUI Implementation

---

## Overview
This project is a JavaFX-based application that allows users to manage and view order details. It integrates features like order placement, order details viewing, and item management using a graphical user interface (GUI). The project introduces enhancements to the existing structure by adding new classes, implementing JavaFX-specific properties, and supporting `TableView` for visualizing order details.

---

## Changes Made for GUI

1. **Integration of JavaFX:**
   - Created GUI for order management using JavaFX components.
   - Added `TableView` to display order details such as item names and quantities.

2. **Added TableView for Order Details:**
   - Created a `TableView` to show the list of items in an order with two columns:
     - **Item Name**: Displays the name of the menu item.
     - **Quantity**: Displays the quantity ordered for each item.

3. **Use of JavaFX Properties:**
   - Replaced simple fields with `SimpleStringProperty` and `SimpleIntegerProperty` for better integration with JavaFX's binding mechanism.

4. **Scene Management:**
   - Designed a scene that displays the order details in a tabular format.
   - Proper layout handling using `VBox` for a clean and user-friendly interface.

---

## New Classes Added

1. **`OrderDetails` Class:**
   - Represents a single item in an order.
   - Uses `SimpleStringProperty` for the item name and `SimpleIntegerProperty` for the quantity.
   - Facilitates direct integration with the `TableView`.

---

## Key Features Implemented

1. **Order Management:**
   - Orders are stored as a map of items to quantities.
   - Displayed dynamically in the GUI using a `TableView`.

2. **Dynamic Table Population:**
   - Extracts order details from the `Order` object and populates the `TableView` with `OrderItem` instances.

3. **Order Status Update:**
   - Status of orders (`Preparing`, `Out for Delivery`, `Delivered`) can be updated programmatically based on priority of customer.
   - Implemented in the `Order` class.

4. **Order Cancellation and Refund:**
   - Added logic to cancel orders (`Preparing` status only).
   - Refund functionality updates order status to "Refunded."

---

## JUnit Testing

### Test Cases for `Customer` class:
1. **`CorrectLogin`:**
   - Validates that the login has been performed correctly by them."
   - Ensures they have entered correct email and password while logging in.

2. **`Incorrectlogin`:**
   - When either of the email or password is incorrect this test case activates.

### Test Cases for `Asdmin` class:
1. **`CorrectLogin`:**
   - Validates that the login has been performed correctly by the admin."
   - Ensures they have entered correct email and password while logging in.

2. **`Incorrectlogin`:**
   - When either of the email or password is incorrect this test case activates.

### Test Cases for `MenuItem` Class:
1. **`Out of Stock`:**
   - Confirms that the `MenuItem` quantity is not available.

2. **`inStock`:**
   - Ensures that the `quantity` is available.

3. **`ItemInvalid`:**
   - Tells that item inputted is not present in the menu. 

### Test Cases for GUI Components:
- Verified GUI components manually and programmatically (using TestFX where applicable), focusing on:
  - Correct data population in `TableView`.
  - Proper alignment of columns (Item Name, Quantity).

---

## How to Run the Application

1. **Setup:**
   - Install Java 11 or later.
   - Add JavaFX SDK to your project dependencies.

2. **Execution:**
   - Compile and run the `OrderDetailsView` class.
   - The main window will display a `TableView` with order details.

3. **Testing:**
   - Execute `JUnit` tests via an IDE like IntelliJ or Eclipse.
   - Ensure the `junit-jupiter` dependency is included for running tests.

---

## Future Enhancements

- Add a dedicated menu to place new orders.
- Implement search and filtering options for orders.
- Integrate a database to store persistent order data.
- Add styling to the GUI using CSS for better user experience.

---

## Conclusion
The project demonstrates a robust integration of JavaFX for a clean and dynamic GUI, leveraging `SimpleStringProperty` and `SimpleIntegerProperty` for seamless interaction with JavaFX components. The modular structure and test coverage ensure maintainability and scalability for future features.
