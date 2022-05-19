package com.example.twentyfortyeight;

import static com.example.twentyfortyeight.Direction.*;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import java.util.ArrayList;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import java.util.HashSet;
import javafx.fxml.FXML;
import java.util.Random;
import java.util.Set;

public class GameProcess {
    private boolean gameOver = false;
    private Stage stageResults;
    public static int highestScore = 0;
    private double pressedCoordX, pressedCoordY, releasedCoordX, releasedCoordY;
    private final int SIZE = 16;
    private int[][] grid = new int[4][4];
    private ArrayList<Number> numbers = new ArrayList<>();
    @FXML
    private Pane pane;
    @FXML
    private void initialize() {
        addNumber();
        showGrid();
    }
    @FXML
    private void mouseUsed(MouseEvent e) throws IOException, InterruptedException {
        if(!gameOver) {
            if (e.getEventType().toString().equals("MOUSE_PRESSED")) {
                pressedCoordX = e.getX();
                pressedCoordY = e.getY();
            } else if (e.getEventType().toString().equals("MOUSE_RELEASED") && pressedCoordX != 0 && pressedCoordY != 0) {
                Direction direction = Direction.NULL;
                releasedCoordX = e.getX();
                releasedCoordY = e.getY();
                if (Math.abs(releasedCoordX - pressedCoordX) > Math.abs(releasedCoordY - pressedCoordY)) {
                    if (releasedCoordX - pressedCoordX > 0)
                        direction = RIGHT;
                    else if (releasedCoordX - pressedCoordX < 0)
                        direction = Direction.LEFT;
                } else {
                    if (releasedCoordY - pressedCoordY > 0)
                        direction = Direction.DOWN;
                    else if (releasedCoordY - pressedCoordY < 0)
                        direction = Direction.UP;
                }
                if (direction != Direction.NULL)
                    moveObjects(direction);
            }
        }
    }
    private void moveObjects(Direction direction) throws IOException, InterruptedException {
        switch(direction) {
            case RIGHT, LEFT -> {
                int change = (direction == RIGHT) ? (-1) : (1);
                for(int i = 0; i < 3; i++)
                    for (int line = 0; line < 4; line++)
                        for (int column = (direction == RIGHT) ? (2) : (1); (direction == RIGHT) ? (column >= 0) : (column < 4); column += change) {
                            if (grid[line][column - change] == 0) {
                                grid[line][column - change] = grid[line][column];
                                grid[line][column] = 0;
                            }
                            if (grid[line][column - change] == grid[line][column]) {
                                grid[line][column - change] = grid[line][column] * 2;
                                grid[line][column] = 0;
                            }
                        }
            }
            case DOWN, UP -> {
                int change = (direction == DOWN) ? (-1) : (1);
                for(int i = 0; i < 3; i++)
                    for (int column = 0; column < 4; column++)
                        for (int line = (direction == DOWN) ? (2) : (1); (direction == DOWN) ? (line >= 0) : (line < 4); line += change) {
                            if (grid[line - change][column] == 0) {
                                grid[line - change][column] = grid[line][column];
                                grid[line][column] = 0;
                            }
                            if (grid[line - change][column] == grid[line][column]) {
                                grid[line - change][column] = grid[line][column] * 2;
                                grid[line][column] = 0;
                            }
                        }
            }
        }
        if(!addNumber()) {
            gameOver = true;
            findScore();
            showResults();
        }
        showGrid();
    }
    private void showResults() throws IOException, InterruptedException {
        Parent root = (new FXMLLoader(getClass().getResource("results_view.fxml"))).load();
        stageResults = new Stage();
        Scene scene = new Scene(root, 300, 450);
        scene.getStylesheets().add(StartApplication.configuration);
        stageResults.setResizable(false);
        stageResults.setTitle("2048 / Results");
        stageResults.setScene(scene);
        stageResults.show();
    }
    private boolean addNumber() {
        boolean success = false;
        Random random = new Random();
        Set<ArrayList<Integer>> variants = new HashSet<>(new ArrayList<>());
        while(variants.size() < SIZE) {
            int line = random.nextInt(4);
            int column = random.nextInt(4);
            ArrayList<Integer> cell = new ArrayList<>();
            cell.add(line);
            cell.add(column);
            if(variants.contains(cell))
                continue;
            else variants.add(cell);
            if(grid[line][column] == 0) {
                grid[line][column] = 2;
                success = true;
                break;
            }
        }
        return success;
    }
    private void findScore() {
        int max = 0;
        for(int line = 0; line < 4; line++)
            for(int column = 0; column < 4; column++)
                if(grid[line][column] > max)
                    max = grid[line][column];
        GameProcess.highestScore = max;
    }
    private void showGrid() {
        for (Number number : numbers) number.removeNumber();
        if(!numbers.isEmpty())
            numbers.clear();
        numbers = new ArrayList<>();
        for(int line = 0; line < 4; line++) {
            for (int column = 0; column < 4; column++) {
                if(grid[line][column] != 0) {
                    numbers.add(new Number(pane, grid[line][column]));
                    numbers.get(numbers.size() - 1).setCoord(column * 112.5, line * 112.5);
                }
            }
        }
    }
    @FXML
    private void backToMenu(ActionEvent e) throws IOException {
        if(stageResults != null)
            stageResults.close();
        Parent root = (new FXMLLoader(getClass().getResource("main_view.fxml"))).load();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 500, 500);
        scene.getStylesheets().add(StartApplication.configuration);
        stage.setResizable(false);
        stage.setTitle("2048");
        stage.setScene(scene);
        stage.show();
    }
}
