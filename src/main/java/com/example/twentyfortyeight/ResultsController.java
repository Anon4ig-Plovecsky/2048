package com.example.twentyfortyeight;

import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.fxml.FXMLLoader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import java.util.Scanner;
import javafx.fxml.FXML;
import java.io.File;

public class ResultsController {
    public ArrayList<ArrayList<Label>> saveResults = new ArrayList<>();
    public TextField namePlayer;
    public Label resultPlayer;
    private int count = 0;
    @FXML
    private GridPane gridPane;
    @FXML
    private void initialize() {
        readResultFile();
        fillResultsField();
    }
    private void readResultFile() {
        File file = new File("./src/main/resources/results.txt");
        if(file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                while(scanner.hasNext()) {
                    saveResults.add(new ArrayList<>(2));
                    saveResults.get(saveResults.size() - 1).add(new Label(scanner.nextLine()));
                    saveResults.get(saveResults.size() - 1).add(new Label(scanner.nextLine()));
                    saveResults.get(saveResults.size() - 1).get(1).setTextAlignment(TextAlignment.RIGHT);
                    saveResults.get(saveResults.size() - 1).get(0).setFont(new Font("Noto Sans Regular", 14));
                    saveResults.get(saveResults.size() - 1).get(1).setFont(new Font("Noto Sans Regular", 14));
                    saveResults.get(saveResults.size() - 1).get(0).setPadding(new Insets(0, 0, 0, 10));
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void setScorePlayerInGrid(int rowIndex) {
        RowConstraints rowConstraintsPlayerScore = new RowConstraints(36, 36, 36);
        gridPane.getRowConstraints().add(rowConstraintsPlayerScore);
        gridPane.add(namePlayer, 0, rowIndex);
        gridPane.add(resultPlayer, 1, rowIndex);
        count = 1;
    }
    private void fillResultsField() {
        namePlayer = new TextField("Игрок");
        namePlayer.setFont(new Font("Noto Sans Bond", 14));
        namePlayer.setOnAction(event -> {
            if(!namePlayer.getText().equals(""))
                saveData();
        });
        resultPlayer = new Label(Integer.toString(GameProcess.score));
        resultPlayer.setFont(new Font("Noto Sans Bond", 14));
        if(saveResults.isEmpty())
            setScorePlayerInGrid(0);
        for(int rowIndex = 0; rowIndex < saveResults.size(); rowIndex++) {
            RowConstraints rowConstraints = new RowConstraints(36, 36, 36);
            if(Integer.parseInt(saveResults.get(rowIndex).get(1).getText()) < GameProcess.score && count == 0)
                setScorePlayerInGrid(rowIndex);
            gridPane.getRowConstraints().add(rowConstraints);
            gridPane.add(saveResults.get(rowIndex).get(0), 0, rowIndex + count);
            gridPane.add(saveResults.get(rowIndex).get(1), 1, rowIndex + count);
            if(rowIndex + count >= 10)
                break;
        }
        if(saveResults.size() < 9 && count == 0)
            setScorePlayerInGrid(saveResults.size());
        saveData();
    }
    public void saveData() {
        ArrayList<ArrayList<Label>> fullListResults = new ArrayList<>(saveResults);
        fullListResults.add(new ArrayList<>());
        fullListResults.get(fullListResults.size() - 1).add(new Label(namePlayer.getText()));
        fullListResults.get(fullListResults.size() - 1).add(new Label(resultPlayer.getText()));
        fullListResults.sort((array0, array1) -> Integer.parseInt(array1.get(1).getText()) - Integer.parseInt(array0.get(1).getText()));
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter("./src/main/resources/results.txt");
            for(int i = 0; i < fullListResults.size(); i++) {
                if(i >= 10)
                    break;
                printWriter.println(fullListResults.get(i).get(0).getText());
                printWriter.println(fullListResults.get(i).get(1).getText());
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void startAgain(ActionEvent e) throws IOException {
        Parent root = (new FXMLLoader(getClass().getResource("game_view.fxml"))).load();
        Stage stage = GameProcess.currentStage;
        Scene scene = new Scene(root, 500, 500);
        scene.getStylesheets().add(StartApplication.configuration);
        stage.setTitle("2048");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        Stage stageResult = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stageResult.close();
    }
}
