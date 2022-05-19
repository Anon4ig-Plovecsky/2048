package com.example.twentyfortyeight;

import javafx.scene.control.Button;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.fxml.FXML;

public class MainMenuController {
    @FXML
    private Button newGameButton, exitButton;
    @FXML
    private void initialize() {

    }
    @FXML
    private void buttonPressed(ActionEvent e) throws IOException {
        switch(((Button)e.getSource()).getId()) {
            case "newGameButton" -> {
                Parent root = (new FXMLLoader(getClass().getResource("game_view.fxml"))).load();
                Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 500, 500);
                scene.getStylesheets().add(StartApplication.configuration);
                stage.setResizable(false);
                stage.setTitle("2048");
                stage.setScene(scene);
                stage.show();
            }
            case "exitButton" -> Platform.exit();
        }
    }
}
