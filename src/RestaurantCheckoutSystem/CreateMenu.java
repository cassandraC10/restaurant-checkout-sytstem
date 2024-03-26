package RestaurantCheckoutSystem;

import java.io.*;
import java.util.Vector;

public class CreateMenu {
    public static void main(String[] args) {
        Vector<String> menu = new Vector<String>();

        addMenuItem(menu, "Main Course", "Spaghetti", 10.99);
        addMenuItem(menu, "Main Course", "Burger", 8.99);
        addMenuItem(menu, "Dessert", "Cheesecake", 5.99);
        addMenuItem(menu, "Drinks", "Soda", 1.99);

        saveMenuToFile(menu);
    }

    public static void addMenuItem(Vector<String> menu, String category, String description, double price) {
        String item = String.format("Category: %s, Item Description: %s, Item Price: %.2f", category, description, price);
        menu.add(item);
    }

    public static void saveMenuToFile(Vector<String> menu) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("menu.txt"));
            for (String item : menu) {
                writer.write(item);
                writer.newLine();
            }
            writer.close();
            System.out.println("Menu saved successfully!");
        } catch (IOException e) {
            System.out.println("Error occurred while saving menu to file: " + e.getMessage());
        }
    }

    public static void loadMenuFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("menu.txt"));
            String line;
            while ((line = reader.readLine()) != null) {

                System.out.println(line);
            }
            reader.close();
            System.out.println("Menu loaded successfully!");
        } catch (IOException e) {
            System.out.println("Error occurred while reading menu from file: " + e.getMessage());
        }
    }
}
