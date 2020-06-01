package fooditem;

public class FoodItem {
    private final String name;
    private final String price;
    private final String description;
    private final String calories;

    public FoodItem(String name, String price, String description, String calories) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
            "name='" + name + '\'' +
            ", price='" + price + '\'' +
            ", description='" + description + '\'' +
            ", calories='" + calories + '\'' +
            '}';
    }
}
