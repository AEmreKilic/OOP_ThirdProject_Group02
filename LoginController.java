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

        if (u.equals("cust") && p.equals("cust")) {
            try {
                FXMLLoader loader =
                        new FXMLLoader(getClass().getResource("/fxml/customer.fxml"));

                Scene scene = new Scene(loader.load(), 960, 540);
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Group5 GreenGrocer - Customer");

            } catch (Exception e) {
                e.printStackTrace();
                errorLabel.setText("Cannot load customer screen");
            }
        } else {
            errorLabel.setText("Invalid credentials");
        }
    }
}
