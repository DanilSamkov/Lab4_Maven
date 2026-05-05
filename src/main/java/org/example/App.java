package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlLocation = getClass().getResource("/gui.fxml");
        if (fxmlLocation == null) {
            throw new IllegalStateException("Файл gui.fxml не знайдено у resources!");
        }
        Parent root = FXMLLoader.load(fxmlLocation);
        primaryStage.setTitle("Управління магазином одягу (UUID)");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
