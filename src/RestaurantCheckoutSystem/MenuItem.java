package RestaurantCheckoutSystem;

public class MenuItem {
    private int itemNo;
    private String category;
    private String description;
    private double price;

    public MenuItem(int itemNo, String category, String description, double price) {
        this.itemNo = itemNo;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public int getItemNo() {
        return itemNo;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return itemNo + ": " + description + " (" + category + ") - $" + price;
    }
}
