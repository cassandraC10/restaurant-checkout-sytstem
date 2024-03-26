
package RestaurantCheckoutSystem;

import java.io.*;
import java.text.*;
import java.util.*;

public class BillingCheckout {
    private static final String MENU_FILE = "src/menu.txt";
    private static final String TRANSACTION_FILE = "transactions.txt";
    private static final double VAT_RATE = 0.20;
    private static List<MenuItem> menu;

    public BillingCheckout(List<MenuItem> menu) {
        this.menu = menu;
    }

    public static void main(String[] args) {
        loadMenu();
        displayMenu();
        Scanner scanner = new Scanner(System.in);
        generateBill(scanner);
    }

    private static void loadMenu() {
        menu = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(MENU_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split("\\s+", 4);
                    if (parts.length >= 4) {
                        int itemNo = Integer.parseInt(parts[0]);
                        String category = parts[1];
                        String descriptionAndPrice = parts[2] + " " + parts[3];
                        int lastSpaceIndex = descriptionAndPrice.lastIndexOf(' ');
                        String description = descriptionAndPrice.substring(0, lastSpaceIndex);
                        double price = Double.parseDouble(descriptionAndPrice.substring(lastSpaceIndex + 1));
                        MenuItem menuItem = new MenuItem(itemNo, category, description, price);
                        menu.add(menuItem);
                    } else {
                        System.err.println("Invalid menu line: " + line);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Menu file not found.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Error parsing item number or price from menu file.");
            e.printStackTrace();
        }
    }


    private static void displayMenu() {
        System.out.println("Menu:");
        for (MenuItem item : menu) {
            System.out.println(item);
        }
    }

    public static void generateBill(Scanner scanner) {
        if (menu == null) {
            throw new IllegalStateException("Menu is not initialized");
        }
        List<Order> orders = new ArrayList<>();
        double subtotal = 0;

        System.out.println("Enter item numbers and quantities (0 to finish):");
        int itemNo;
        while ((itemNo = scanner.nextInt()) != 0) {
            System.out.print("Quantity: ");
            int quantity = scanner.nextInt();
            MenuItem item = findItem(itemNo);
            if (item != null) {
                orders.add(new Order(item, quantity));
                subtotal += item.getPrice() * quantity;
            } else {
                System.out.println("Invalid item number.");
            }
        }

        double vat = subtotal * VAT_RATE;
        double grandTotal = subtotal + vat;

        // Printing the bill
        System.out.println("Bill:");
        for (Order order : orders) {
            System.out.println(order);
        }
        System.out.println("Subtotal: " + subtotal);
        System.out.println("VAT: " + vat);
        System.out.println("Grand Total: " + grandTotal);

        // Append the bill to transaction file
        try (PrintWriter writer = new PrintWriter(new FileWriter(TRANSACTION_FILE, true))) {
            for (Order order : orders) {
                writer.println(order);
            }
            writer.println("Subtotal: " + subtotal);
            writer.println("VAT: " + vat);
            writer.println("Grand Total: " + grandTotal);
            writer.println("Date and Time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            writer.println();
        } catch (IOException e) {
            System.err.println("Error appending to transaction file.");
        }
    }

    private static MenuItem findItem(int itemNo) {
        for (MenuItem item : menu) {
            if (item.getItemNo() == itemNo) {
                return item;
            }
        }
        return null;
    }
}

class Order {
    private MenuItem item;
    private int quantity;

    public Order(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return item.getDescription() + " x" + quantity + " - $" + item.getPrice() * quantity;
    }
}
