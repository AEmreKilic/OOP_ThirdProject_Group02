package edu.khas.cmpe343.group5;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import java.io.IOException;

/**
 * Owner (manav sahibi) ekranı.
 * Sadece UI + dummy veriler var, DB işleri backend arkadaşa bırakıldı.
 */
public class OwnerController {

    @FXML private Label usernameLabel;

    // Products tab
    @FXML private ListView<String> productsListView;
    @FXML private TextField productNameField;
    @FXML private TextField productCategoryField;
    @FXML private TextField productPriceField;
    @FXML private TextField productStockField;
    @FXML private TextField productThresholdField;

    // Carriers tab
    @FXML private ListView<String> carriersListView;
    @FXML private TextField newCarrierField;

    // Orders tab
    @FXML private ListView<String> ordersListView;

    // Messages tab
    @FXML private ListView<String> messagesListView;
    @FXML private TextArea replyTextArea;

    @FXML
    public void initialize() {
        System.out.println("OwnerController initialize");

        usernameLabel.setText("owner");

        loadDummyProducts();
        loadDummyCarriers();
        loadDummyOrders();
        loadDummyMessages();
    }

    // ----- Dummy data yükleme -----

    private void loadDummyProducts() {
        productsListView.getItems().clear();
        productsListView.getItems().addAll(
                "Tomato | Vegetables | 12₺ | stock: 50 | thresh: 10",
                "Apple  | Fruits     | 15₺ | stock: 30 | thresh: 5",
                "Potato | Vegetables | 8₺  | stock: 80 | thresh: 20"
        );
    }

    private void loadDummyCarriers() {
        carriersListView.getItems().clear();
        carriersListView.getItems().addAll(
                "Carrier 1 - Ahmet Yılmaz",
                "Carrier 2 - Ayşe Demir"
        );
    }

    private void loadDummyOrders() {
        ordersListView.getItems().clear();
        ordersListView.getItems().addAll(
                "#101 | Ali Veli | 120₺ | 28/12/2025",
                "#102 | Ayşe Yılmaz | 85₺ | 28/12/2025",
                "#103 | Mehmet Demir | 55₺ | 29/12/2025"
        );
    }

    private void loadDummyMessages() {
        messagesListView.getItems().clear();
        messagesListView.getItems().addAll(
                "Ali: Delivery was late",
                "Ayşe: Tomatoes were very fresh, thanks!",
                "Mehmet: Please deliver after 6 pm next time."
        );
    }

    // ----- Products tab actions -----

    @FXML
    private void handleAddProduct() {
        String name = productNameField.getText();
        String category = productCategoryField.getText();
        String price = productPriceField.getText();
        String stock = productStockField.getText();
        String thresh = productThresholdField.getText();

        if (name.isBlank() || category.isBlank() || price.isBlank()) {
            showError("Name, category and price are required.");
            return;
        }

        String line = String.format(
                "%s | %s | %s₺ | stock: %s | thresh: %s",
                name, category, price, stock.isBlank() ? "-" : stock,
                thresh.isBlank() ? "-" : thresh
        );

        // TODO: Backend - DB'ye insert yapılacak.
        productsListView.getItems().add(line);
        clearProductFields();
    }

    @FXML
    private void handleUpdateSelectedProduct() {
        int index = productsListView.getSelectionModel().getSelectedIndex();
        if (index < 0) {
            showError("Please select a product to update.");
            return;
        }

        String name = productNameField.getText();
        String category = productCategoryField.getText();
        String price = productPriceField.getText();
        String stock = productStockField.getText();
        String thresh = productThresholdField.getText();

        if (name.isBlank() || category.isBlank() || price.isBlank()) {
            showError("Name, category and price are required.");
            return;
        }

        String line = String.format(
                "%s | %s | %s₺ | stock: %s | thresh: %s",
                name, category, price, stock.isBlank() ? "-" : stock,
                thresh.isBlank() ? "-" : thresh
        );

        // TODO: Backend - DB'de update yapılacak.
        productsListView.getItems().set(index, line);
        clearProductFields();
    }

    @FXML
    private void handleRemoveSelectedProduct() {
        String selected = productsListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Please select a product to remove.");
            return;
        }

        // TODO: Backend - DB'den silme yapılacak.
        productsListView.getItems().remove(selected);
    }

    @FXML
    private void handleFillFieldsFromSelection() {
        String selected = productsListView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        // Çok basit: ' | ' ile bölüp alanları dolduruyoruz.
        String[] parts = selected.split("\\|");
        if (parts.length >= 3) {
            productNameField.setText(parts[0].trim());
            productCategoryField.setText(parts[1].trim());
            productPriceField.setText(parts[2].replace("₺", "").trim());
        }
    }

    private void clearProductFields() {
        productNameField.clear();
        productCategoryField.clear();
        productPriceField.clear();
        productStockField.clear();
        productThresholdField.clear();
    }

    // ----- Carriers tab actions -----

    @FXML
    private void handleAddCarrier() {
        String name = newCarrierField.getText();
        if (name.isBlank()) {
            showError("Please enter carrier name.");
            return;
        }
        carriersListView.getItems().add(name);
        newCarrierField.clear();
        // TODO: Backend - DB'ye yeni carrier ekle.
    }

    @FXML
    private void handleRemoveSelectedCarrier() {
        String selected = carriersListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Please select a carrier to remove.");
            return;
        }
        carriersListView.getItems().remove(selected);
        // TODO: Backend - DB'den carrier sil.
    }

    // ----- Messages tab actions -----

    @FXML
    private void handleReplyMessage() {
        String selected = messagesListView.getSelectionModel().getSelectedItem();
        String reply = replyTextArea.getText();

        if (selected == null) {
            showError("Please select a message to reply.");
            return;
        }
        if (reply.isBlank()) {
            showError("Reply text cannot be empty.");
            return;
        }

        // TODO: Backend - reply mesajını DB'ye veya mail sistemine gönder.
        showInfo("Reply sent (simulation).");
        replyTextArea.clear();
    }

    // ----- Logout -----

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent loginRoot = loader.load();
            usernameLabel.getScene().setRoot(loginRoot);
        } catch (IOException e) {
            e.printStackTrace();
            showError("Cannot load login screen.");
        }
    }

    // ----- Alert helpers -----

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
