package edu.khas.cmpe343.group5;

import edu.khas.cmpe343.group5.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CustomerController {

    // TOP BAR
    @FXML
    private Label usernameLabel;

    @FXML
    private TextField kgTextField;  // Kullanıcının kilo girdiği TextField

    // LEFT SIDEBAR
    @FXML
    private TextField searchField;

    @FXML
    private RadioButton allRadio;

    @FXML
    private RadioButton fruitsRadio;

    @FXML
    private RadioButton vegetablesRadio;

    // CENTER GRID
    @FXML
    private GridPane productGrid;

    // CATEGORY TOGGLE GROUP (FXML’de yok, burada var)
    private final ToggleGroup categoryGroup = new ToggleGroup();

    // Ürünlerin listesi
    private final List<Product> allProducts = List.of(
            // Vegetables (15)
            new Product("Tomato", 12, Category.VEGETABLE, "/images/vegetables/tomato.png"),
            new Product("Cucumber", 10, Category.VEGETABLE, "/images/vegetables/cucumber.png"),
            new Product("Potato", 8, Category.VEGETABLE, "/images/vegetables/potato.png"),
            new Product("Onion", 7, Category.VEGETABLE, "/images/vegetables/onion.png"),
            new Product("Carrot", 9, Category.VEGETABLE, "/images/vegetables/carrot.png"),
            new Product("Pepper", 14, Category.VEGETABLE, "/images/vegetables/pepper.png"),
            new Product("Eggplant", 11, Category.VEGETABLE, "/images/vegetables/eggplant.png"),
            new Product("Zucchini", 10, Category.VEGETABLE, "/images/vegetables/zucchini.png"),
            new Product("Broccoli", 13, Category.VEGETABLE, "/images/vegetables/broccoli.png"),
            new Product("Lettuce", 6, Category.VEGETABLE, "/images/vegetables/lettuce.png"),
            new Product("Cabbage", 5, Category.VEGETABLE, "/images/vegetables/cabbage.png"),
            new Product("Spinach", 7, Category.VEGETABLE, "/images/vegetables/spinach.png"),
            new Product("Cauliflower", 9, Category.VEGETABLE, "/images/vegetables/cauliflower.png"),
            new Product("Asparagus", 15, Category.VEGETABLE, "/images/vegetables/asparagus.png"),
            new Product("Pumpkin", 12, Category.VEGETABLE, "/images/vegetables/pumpkin.png"),

            // Fruits (15)
            new Product("Apple", 15, Category.FRUIT, "/images/fruits/apple.png"),
            new Product("Banana", 18, Category.FRUIT, "/images/fruits/banana.png"),
            new Product("Blueberry", 22, Category.FRUIT, "/images/fruits/blueberry.png"),
            new Product("Cherry", 30, Category.FRUIT, "/images/fruits/cherry.png"),
            new Product("Grape", 16, Category.FRUIT, "/images/fruits/grape.png"),
            new Product("Kiwi", 20, Category.FRUIT, "/images/fruits/kiwi.png"),
            new Product("Lemon", 10, Category.FRUIT, "/images/fruits/lemon.png"),
            new Product("Mango", 28, Category.FRUIT, "/images/fruits/mango.png"),
            new Product("Orange", 16, Category.FRUIT, "/images/fruits/orange.png"),
            new Product("Peach", 17, Category.FRUIT, "/images/fruits/peach.png"),
            new Product("Pear", 14, Category.FRUIT, "/images/fruits/pear.png"),
            new Product("Pineapple", 25, Category.FRUIT, "/images/fruits/pineapple.png"),
            new Product("Pomegranate", 35, Category.FRUIT, "/images/fruits/pomegranate.png"),
            new Product("Strawberry", 25, Category.FRUIT, "/images/fruits/strawberry.png"),
            new Product("Watermelon", 20, Category.FRUIT, "/images/fruits/watermelon.png")
    );

    // Sepet listesi
    private List<Product> cart = new ArrayList<>();

    @FXML
    public void initialize() {
        System.out.println("CustomerController initialize");

        usernameLabel.setText("customer");

        // ToggleGroup bağlantıları
        allRadio.setToggleGroup(categoryGroup);
        fruitsRadio.setToggleGroup(categoryGroup);
        vegetablesRadio.setToggleGroup(categoryGroup);

        // Filtreyi tetikle
        categoryGroup.selectedToggleProperty().addListener((obs, old, selected) -> {
            final Category selectedCategory = (selected == allRadio) ? null :
                    (selected == fruitsRadio) ? Category.FRUIT : Category.VEGETABLE;
            filterAndDisplayProducts(selectedCategory, searchField.getText());
        });

        // Search özelliği ekleme
        searchField.textProperty().addListener((obs, oldText, newText) -> {
            filterAndDisplayProducts(getSelectedCategory(), newText);
        });

        // Şimdilik tüm ürünleri göster
        filterAndDisplayProducts(null, "");
    }

    private void filterAndDisplayProducts(Category selectedCategory, String searchQuery) {
        List<Product> filtered = allProducts.stream()
                .filter(p -> (selectedCategory == null || p.getCategory() == selectedCategory) &&
                        (p.getName().toLowerCase().contains(searchQuery.toLowerCase())))
                .collect(Collectors.toList());

        // Filtrelenen ürünleri göster
        displayProducts(filtered);
    }

    private Category getSelectedCategory() {
        if (allRadio.isSelected()) {
            return null;  // "All" seçildiğinde tüm ürünler gösterilsin
        } else if (fruitsRadio.isSelected()) {
            return Category.FRUIT;
        } else if (vegetablesRadio.isSelected()) {
            return Category.VEGETABLE;
        }
        return null;
    }

    private void displayProducts(List<Product> filtered) {
        productGrid.getChildren().clear(); // Eski ürünleri temizle
        int col = 0;
        int row = 0;

        // Filtrelenmiş ürünleri grid'e ekle
        for (Product p : filtered) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/product-card.fxml"));
            try {
                Node card = loader.load();
                ProductCardController controller = loader.getController();
                controller.setProduct(p);
                productGrid.add(card, col, row);
                col++;
                if (col == 4) { // 4 ürün yan yana
                    col = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleLogout() {
        System.out.println("Logout clicked");

        // Login ekranına geçiş yap
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent loginScreen = loader.load(); // Parent olarak yükle
            productGrid.getScene().setRoot(loginScreen);  // Scene'i login ekranına değiştir
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleViewCart() {
        System.out.println("View cart clicked");

        // Sepet içeriğini ekranda göstermek için bir VBox (veya başka bir container) kullanacağız.
        // VBox'a sepet içeriğini dinamik olarak ekleyeceğiz.

        javafx.scene.layout.VBox cartVBox = new javafx.scene.layout.VBox();
        cartVBox.setSpacing(10);  // Satır arası mesafe

        // Sepetteki tüm ürünleri ekle
        for (Product product : cart) {
            try {
                javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView();

                // Resmi yükle
                imageView.setImage(new javafx.scene.image.Image(getClass().getResourceAsStream(product.getImagePath())));

                // Sepet ürünü ve fiyatını gösteren label
                Label productLabel = new Label(product.getName() + " -> " + product.getPrice() + "₺");

                // Görseli ve label'ı VBox'a ekle
                javafx.scene.layout.HBox hBox = new javafx.scene.layout.HBox();
                hBox.setSpacing(10);
                hBox.getChildren().addAll(imageView, productLabel);
                cartVBox.getChildren().add(hBox);
            } catch (Exception e) {
                System.out.println("Error loading image for: " + product.getName());
                e.printStackTrace();  // Hata mesajlarını görmek için
            }
        }

        // Cart VBox'unu yeni bir Scene olarak göster
        javafx.scene.Scene cartScene = new javafx.scene.Scene(cartVBox, 400, 300);
        javafx.stage.Stage cartStage = new javafx.stage.Stage();
        cartStage.setScene(cartScene);
        cartStage.setTitle("Your Cart");
        cartStage.show();
    }



    @FXML
    private void handleAddToCart(Product product) {
        String kgText = kgTextField.getText();  // Kullanıcının kilo girdiği TextField

        // 1. Kullanıcı geçerli bir sayı giriyor mu?
        try {
            // Geçerli bir sayı girildi mi?
            if (kgText.isEmpty() || !kgText.matches("-?\\d+(\\.\\d+)?")) {
                showError("Please enter a valid number.");
                return;
            }

            double kg = Double.parseDouble(kgText);

            // 2. Negatif sayılar veya 10 kg'dan fazla kilo girilmesine izin verilmez
            if (kg <= 0) {
                showError("Weight cannot be less than or equal to 0!");
                return;
            }

            if (kg > 10) {
                showError("You can buy a maximum of 10 kilograms of one product.");
                return;
            }

            // 3. Kullanıcı aynı üründen 10 kg'dan fazla alamaz.
            double totalWeight = cart.stream()
                    .filter(p -> p.getName().equals(product.getName()))
                    .mapToDouble(Product::getQuantity)
                    .sum();

            if (totalWeight + kg > 10) {
                showError("The total weight of this product cannot exceed 10 kg.");
                return;
            }

            // 4. Ürün sepete eklenebilir
            product.setQuantity(kg);
            cart.add(product);

            System.out.println(product.getName() + " added to cart");
            System.out.println("Items in cart: " + cart.size());

        } catch (NumberFormatException e) {
            showError("Please enter a valid number.");
        }
    }

    private void showError(String message) {
        // Uyarı mesajı göster
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
