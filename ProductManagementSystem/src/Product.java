public class Product {
    String productID;
    String name;
    String description;
    int quantity;
    double price;

    // Constructor with parameters to initialize Product object
    public Product(String productID, String name, String description, int quantity, double price) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and setters omitted for brevity

    // toString method to convert Product object to a string
    @Override
    public String toString() {
        return "Product{" +
                "productID='" + productID + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    // Getter method for productID
    public String getProductID() {
        return productID;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Getter method for description
    public String getDescription() {
        return description;
    }

    // Getter method for quantity
    public int getQuantity() {
        return quantity;
    }

    // Getter method for price
    public double getPrice() {
        return price;
    }
}
