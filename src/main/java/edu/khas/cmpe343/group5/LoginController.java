package edu.khas.cmpe343.group5;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    private void handleLogin() {
        String u = usernameField.getText();
        String p = passwordField.getText();

        if (u.isBlank() || p.isBlank()) {
            errorLabel.setText("Username and password required");
            return;
        }

        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene scene;

            // 1) CUSTOMER
            if (u.equals("cust") && p.equals("cust")) {

                FXMLLoader loader =
                        new FXMLLoader(getClass().getResource("/fxml/customer.fxml"));
                scene = new Scene(loader.load(), 960, 540);
                stage.setScene(scene);
                stage.setTitle("Group5 GreenGrocer - Customer");

            // 2) CARRIER
            } else if (u.equals("carr") && p.equals("carr")) {

                FXMLLoader loader =
                        new FXMLLoader(getClass().getResource("/fxml/carrier.fxml"));
                scene = new Scene(loader.load(), 960, 540);
                stage.setScene(scene);
                stage.setTitle("Group5 GreenGrocer - Carrier");

            // 3) OWNER  (owner.fxml'i sonra yazacağız, şimdilik iskelet)
            } else if (u.equals("own") && p.equals("own")) {

                FXMLLoader loader =
                        new FXMLLoader(getClass().getResource("/fxml/owner.fxml"));
                scene = new Scene(loader.load(), 960, 540);
                stage.setScene(scene);
                stage.setTitle("Group5 GreenGrocer - Owner");

            } else {
                errorLabel.setText("Invalid credentials");
            }

        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Cannot load screen");
        }
    }
}
