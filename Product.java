package edu.khas.cmpe343.group5;

public class Product {

    private String name;          // Ürün adı
    private double price;         // Ürün fiyatı
    private Category category;    // Ürün kategorisi (Enum olarak: VEGETABLE veya FRUIT)
    private String imagePath;     // Ürün görselinin yolu
    private double quantity;      // Ürünün miktarı (Kg)

    // Constructor - Ürün oluşturulurken bu bilgiler alınır
    public Product(String name, double price, Category category, String imagePath) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.imagePath = imagePath;
    }

    // Getter ve Setter metodları

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    // Ürün bilgilerini yazdıran metod
    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", imagePath='" + imagePath + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
