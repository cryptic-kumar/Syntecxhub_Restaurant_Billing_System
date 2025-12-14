import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

// Class for Menu Items
class MenuItem {
    int id;
    String name;
    double price;

    public MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

// Class for Ordered Items
class OrderItem {
    MenuItem item;
    int quantity;

    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return item.price * quantity;
    }
}

public class RestaurantBillingSystem {

    static ArrayList<MenuItem> menuList = new ArrayList<>();
    static ArrayList<OrderItem> currentOrder = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeMenu(); // Set up the food items

        System.out.println("=========================================");
        System.out.println("   WELCOME TO SYNTECXHUB RESTAURANT");
        System.out.println("=========================================");

        boolean running = true;
        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. View Menu");
            System.out.println("2. Order Food (Add Items)");
            System.out.println("3. Adjust Order (Remove/Reduce Qty)");
            System.out.println("4. Generate Bill");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayMenu();
                    break;
                case 2:
                    addToOrder();
                    break;
                case 3:
                    removeFromOrder();
                    break;
                case 4:
                    generateBill();
                    // Reset after billing?
                    System.out.print("\nStart a new customer order? (y/n): ");
                    char newCust = scanner.next().charAt(0);
                    if (newCust == 'n' || newCust == 'N') {
                        running = false;
                    } else {
                        currentOrder.clear();
                    }
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void initializeMenu() {
        menuList.add(new MenuItem(1, "Veg Burger", 120.00));
        menuList.add(new MenuItem(2, "Cheese Pizza", 250.00));
        menuList.add(new MenuItem(3, "Red Sauce Pasta", 180.00));
        menuList.add(new MenuItem(4, "Coke (500ml)", 60.00));
        menuList.add(new MenuItem(5, "French Fries", 90.00));
        menuList.add(new MenuItem(6, "Paneer Tikka", 220.00));
    }

    public static void displayMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("ID\tName\t\t\tPrice");
        System.out.println("-------------------------------------");
        for (MenuItem item : menuList) {
            System.out.printf("%d\t%-20s\t%.2f\n", item.id, item.name, item.price);
        }
    }

    // UPDATED: Allows adding multiple items in one go
    public static void addToOrder() {
        boolean ordering = true;
        while (ordering) {
            displayMenu();
            System.out.print("\nEnter Food ID to add (or 0 to stop): ");
            int id = scanner.nextInt();

            if (id == 0) {
                ordering = false;
                break;
            }

            MenuItem selectedItem = null;
            for (MenuItem item : menuList) {
                if (item.id == id) {
                    selectedItem = item;
                    break;
                }
            }

            if (selectedItem != null) {
                System.out.print("Enter Quantity for " + selectedItem.name + ": ");
                int qty = scanner.nextInt();

                // Check if already in list, just update quantity
                boolean found = false;
                for (OrderItem order : currentOrder) {
                    if (order.item.id == selectedItem.id) {
                        order.quantity += qty;
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    currentOrder.add(new OrderItem(selectedItem, qty));
                }
                System.out.println("Added to cart!");
            } else {
                System.out.println("Invalid ID. Try again.");
            }

            System.out.print("Add another item? (y/n): ");
            char cont = scanner.next().charAt(0);
            if (cont == 'n' || cont == 'N') {
                ordering = false;
            }
        }
    }

    // UPDATED: Logic to reduce quantity instead of deleting the whole line
    public static void removeFromOrder() {
        if (currentOrder.isEmpty()) {
            System.out.println("Your order is empty.");
            return;
        }

        System.out.println("\n--- CURRENT ORDER ---");
        for (int i = 0; i < currentOrder.size(); i++) {
            OrderItem order = currentOrder.get(i);
            System.out.println((i + 1) + ". " + order.item.name + " [Qty: " + order.quantity + "]");
        }

        System.out.print("\nEnter serial number to modify (0 to cancel): ");
        int index = scanner.nextInt();

        if (index > 0 && index <= currentOrder.size()) {
            OrderItem selectedOrder = currentOrder.get(index - 1);
            
            System.out.println("Selected Item: " + selectedOrder.item.name);
            System.out.println("Current Quantity: " + selectedOrder.quantity);
            System.out.print("How many do you want to REMOVE? ");
            int removeQty = scanner.nextInt();

            if (removeQty >= selectedOrder.quantity) {
                // If removing equal or more than current qty, delete the item entirely
                currentOrder.remove(index - 1);
                System.out.println("Item removed completely.");
            } else if (removeQty > 0) {
                // Just reduce the quantity
                selectedOrder.quantity -= removeQty;
                System.out.println("Quantity updated. Remaining: " + selectedOrder.quantity);
            } else {
                System.out.println("No changes made.");
            }

        } else if (index != 0) {
            System.out.println("Invalid selection.");
        }
    }

    public static void generateBill() {
        if (currentOrder.isEmpty()) {
            System.out.println("No items to bill.");
            return;
        }

        double total = 0;
        System.out.println("\n\n*****************************************");
        System.out.println("             INVOICE RECEIPT");
        System.out.println("             " + new Date());
        System.out.println("*****************************************");
        System.out.printf("%-20s %-5s %-10s\n", "Item", "Qty", "Total");
        System.out.println("-----------------------------------------");

        for (OrderItem order : currentOrder) {
            double lineTotal = order.getTotalPrice();
            total += lineTotal;
            System.out.printf("%-20s %-5d %-10.2f\n", order.item.name, order.quantity, lineTotal);
        }

        double tax = total * 0.05; // 5% GST
        double grandTotal = total + tax;

        System.out.println("-----------------------------------------");
        System.out.printf("Sub Total: \t\t\t%.2f\n", total);
        System.out.printf("GST (5%%):  \t\t\t%.2f\n", tax);
        System.out.println("-----------------------------------------");
        System.out.printf("GRAND TOTAL: \t\t\t%.2f\n", grandTotal);
        System.out.println("*****************************************");
    }
}