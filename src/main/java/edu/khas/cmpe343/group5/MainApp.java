package edu.khas.cmpe343.group5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader =
                new FXMLLoader(MainApp.class.getResource("/fxml/login.fxml"));

        Scene scene = new Scene(loader.load(), 960, 540);
        stage.setTitle("Group5 GreenGrocer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
