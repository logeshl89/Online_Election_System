package com.example.digitalelection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        root.setStyle("-fx-background-color: transparent;");
        stage.setTitle("Digital Election");
        root.setOnMousePressed(this::onMousePressed);
        root.setOnMouseDragged(this::onMouseDragged);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void onMousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    private void onMouseDragged(MouseEvent event) {
        Stage stage = (Stage) ((Parent) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    public static void main(String[] args) {
        launch();
    }
}
