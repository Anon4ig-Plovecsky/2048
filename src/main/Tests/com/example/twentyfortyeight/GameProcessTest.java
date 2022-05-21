package com.example.twentyfortyeight;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameProcessTest extends GameProcess {
    @Test
    public void checkGameOverTest1() {
        grid = new int[][] {
                { 2, 4, 8, 16 },
                { 4, 8, 32, 128 },
                { 8, 2, 16, 4 },
                { 2, 4, 8, 2 }
        };
        boolean expected = false;
        boolean actual = addNumber(false);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void checkGameOverTest2() {
        grid = new int[][] {
                { 2, 4, 8, 0 },
                { 4, 8, 32, 128 },
                { 8, 2, 16, 4 },
                { 2, 4, 8, 2 }
        };
        boolean expected = true;
        boolean actual = addNumber(true);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void checkGameOverTest3() {
        grid = new int[][] {
                { 0, 4, 8, 16 },
                { 4, 0, 32, 128 },
                { 8, 2, 0, 4 },
                { 2, 4, 8, 0 }
        };
        boolean expected = true;
        boolean actual = addNumber(false);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void addNumberTest1() {
        grid = new int[][] {
                { 0, 4, 8, 16 },
                { 4, 0, 32, 128 },
                { 8, 2, 0, 4 },
                { 2, 4, 8, 0 }
        };
        int filledCells = 0;
        addNumber(true);
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                if(grid[i][j] != 0)
                    filledCells++;
        int expected = 13;
        int actual = filledCells;
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void addNumberTest2() {
        grid = new int[][] {
                { 8, 4, 8, 16 },
                { 4, 0, 32, 128 },
                { 8, 2, 16, 4 },
                { 2, 4, 8, 0 }
        };
        int filledCells = 0;
        addNumber(true);
        addNumber(false);
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                if(grid[i][j] != 0)
                    filledCells++;
        int expected = 15;
        int actual = filledCells;
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void addNumberTest3() {
        grid = new int[][] {
                { 8, 4, 8, 16 },
                { 4, 0, 32, 128 },
                { 8, 2, 16, 4 },
                { 2, 4, 8, 0 }
        };
        int filledCells = 0;
        addNumber(true);
        addNumber(true);
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                if(grid[i][j] != 0)
                    filledCells++;
        int expected = 16;
        int actual = filledCells;
        Assertions.assertEquals(expected, actual);
    }
}