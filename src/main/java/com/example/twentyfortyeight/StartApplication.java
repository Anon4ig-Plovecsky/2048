package com.example.twentyfortyeight;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import java.nio.file.Paths;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartApplication extends Application {
    public static String configuration = Paths.get("./src/main/java/com/example/twentyfortyeight/Configuration.css").toAbsolutePath().toUri().toString();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("main_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        scene.getStylesheets().add(StartApplication.configuration);
        stage.setResizable(false);
        stage.setTitle("2048");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}