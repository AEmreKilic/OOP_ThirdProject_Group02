package edu.khas.cmpe343.group5; // <-- Bunu kendi grubuna göre güncelle

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.io.IOException;

public class CarrierController {

    @FXML
    private Label usernameLabel;

    @FXML
    private ListView<String> availableListView;

    @FXML
    private ListView<String> currentListView;

    @FXML
    private ListView<String> completedListView;

    // Şimdilik sabit kullanıcı adı (login'den parametre taşıyınca güncelleriz)
    private String currentCarrierUsername = "carr";


    // EKRAN AÇILDIĞINDA ÇALIŞAN METOT (otomatik)
    @FXML
    public void initialize() {
        System.out.println("Carrier Screen Opened");

        // Kullanıcı adı sol üstte gözüksün
        usernameLabel.setText(currentCarrierUsername);

        // Test verilerini listeye yükle
        loadDummyOrders();
    }


    // Şimdilik örnek sipariş verileri
    private void loadDummyOrders() {
        availableListView.getItems().addAll(
                "Order #101 - Ali Veli - 120₺ - 28/12/2025",
                "Order #102 - Ayşe Yılmaz - 85₺ - 28/12/2025",
                "Order #103 - Mehmet Demir - 55₺ - 29/12/2025"
        );
    }


    // 1️⃣ "Take Selected Order" butonu
    @FXML
    private void handleTakeOrder() {
        String selected = availableListView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showError("Please select an order from 'Available Orders'.");
            return;
        }

        // TODO: Backend - DB güncelleme yapılacak.
        availableListView.getItems().remove(selected);
        currentListView.getItems().add(selected);

        System.out.println("Order taken: " + selected);
    }


    // 2️⃣ "Complete Selected Order" butonu
    @FXML
    private void handleCompleteOrder() {
        String selected = currentListView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showError("Please select an order from 'Current Orders'.");
            return;
        }

        // TODO: DB'de isDelivered=true yapılacak.
        currentListView.getItems().remove(selected);
        completedListView.getItems().add(selected);

        System.out.println("Order delivered: " + selected);
    }


    // 3️⃣ Logout → Login ekranına dönüş
    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent loginRoot = loader.load();
            usernameLabel.getScene().setRoot(loginRoot);

        } catch (IOException e) {
            e.printStackTrace();
            showError("Error loading login screen.");
        }
    }


    // Hata mesajı göstermek için (tekrar tekrar yazmamak için)
    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

