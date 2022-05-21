package com.example.twentyfortyeight;

import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import javafx.scene.control.Label;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

class ResultsControllerTest extends Thread {
    static boolean start = false;
    ResultsController resultsController;
    @BeforeEach
    public void setUp() {
        resultsController = new ResultsController();
    }
    @Test
    public void runApplication() {
        if(!ResultsControllerTest.start) {
            ResultsControllerTest.start = true;
            start();
        }
    }
    @ParameterizedTest
    @MethodSource("resultsPlayers")
    public void saveDataTest1(String[][] saveResultsArg, TextField namePlayerArg, Label resultPlayerArg, String[] expected) {
        for(int i = 0; i < saveResultsArg.length; i++) {
            resultsController.saveResults.add(new ArrayList<>());
            resultsController.saveResults.get(i).add(new Label(saveResultsArg[i][0]));
            resultsController.saveResults.get(i).add(new Label(saveResultsArg[i][1]));
        }
        resultsController.namePlayer = namePlayerArg;
        resultsController.resultPlayer = resultPlayerArg;
        resultsController.saveData();
        String[] actual = new String[saveResultsArg.length + 1];
        try {
            File resultFile = new File("./src/main/resources/results.txt");
            Scanner scanner = new Scanner(resultFile);
            int count = 0;
            while (scanner.hasNext())
                actual[count++] = scanner.nextLine() + ": " + scanner.nextLine();
            boolean success = resultFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertArrayEquals(expected, actual);
    }
    public static Stream<Arguments> resultsPlayers() {
        return Stream.of(
            Arguments.of(new String[][] { { "Екатерина", "4028" } }, new TextField("Антон"), new Label("128"), new String[] { "Екатерина: 4028", "Антон: 128" }),
            Arguments.of(new String[][] { { "Екатерина", "4028" }, { "Карина", "260" } }, new TextField("Антон"), new Label("24"), new String[] { "Екатерина: 4028", "Карина: 260", "Антон: 24" }),
            Arguments.of(new String[][] { { "Екатерина", "4028" }, { "Карина", "260" } }, new TextField("Антон"), new Label("1260"), new String[] { "Екатерина: 4028", "Антон: 1260", "Карина: 260" })
        );
    }
    @Override
    public void run() {
        super.run();
        StartApplication.main(new String[] { "launchApplication" });
    }
}