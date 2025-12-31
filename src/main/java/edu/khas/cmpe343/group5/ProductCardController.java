package edu.khas.cmpe343.group5;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

public class ProductCardController {

    @FXML
    private ImageView productImage;  // Ürün görseli
    @FXML
    private Label productName;      // Ürün adı
    @FXML
    private Label productPrice;     // Ürün fiyatı
    @FXML
    private TextField kgTextField;  // Kullanıcının kilo girdiği alan
    @FXML
    private Button addToCartButton; // Sepete ekleme butonu

    private Product product;

    public void initialize() {
        // Başlangıçta yapılması gereken işlemler
        // Örneğin ürün verilerini yükleme
    }

    // Sepete ekleme işlevi
    @FXML
    private void handleAddToCart() {
        String kgText = kgTextField.getText();  // Kullanıcının girdiği kilo

        try {
            double kg = Double.parseDouble(kgText);
            if (kg <= 0) {
                showError("Weight cannot be less than or equal to 0!");
                return;
            }
            if (kg > 10) {
                showError("You can buy a maximum of 10 kilograms of one product.");
                return;
            }

            // Ürün sepete eklenebilir
            product.setQuantity(kg);
            // Sepete ürün ekleme işlemi yapılacak
            System.out.println(product.getName() + " added to cart.");
        } catch (NumberFormatException e) {
            showError("Please enter a valid number.");
        }
    }

    // Hata mesajı gösterme
    private void showError(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Product nesnesini set etmek için bir metod
    public void setProduct(Product product) {
        this.product = product;
        productName.setText(product.getName());
        productPrice.setText("₺" + product.getPrice() + " / kg");
        // Resmi de yüklemek için
        // productImage.setImage(new Image("file:" + product.getImagePath()));
    }
}
